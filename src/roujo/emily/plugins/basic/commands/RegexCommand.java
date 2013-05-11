package roujo.emily.plugins.basic.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.pircbotx.User;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class RegexCommand extends Command {
	private final Map<User, Pattern> patterns;
	
	public RegexCommand() {
		super("regex", "regex (?<args>.*)", "Compiles and matches regex",
				"regex (compile <regex>|match <string>)", false);
		patterns = new TreeMap<User, Pattern>();
	}

	@Override
	public boolean execute(MessageContext context) {
		String arguments = getArguments(context);
		if(arguments.startsWith("compile ") && arguments.length() > "compile ".length()) {
			return compilePattern(context, arguments);
		} else if(arguments.startsWith("match ") && arguments.length() > "match ".length()) {
			return matchString(context, arguments);
		} else {
			sendUsageBack(context);
			return false;
		}
	}
	
	public boolean compilePattern(MessageContext context, String arguments) {
		try {
			String patternString = arguments.substring("compile ".length());
			patterns.put(context.getUser(), Pattern.compile(patternString));
			sendMessageBack(context, "Pattern compiled!");
			return true;
		} catch (PatternSyntaxException e) {
			sendMessageBack(context, "The pattern was invalid.");
			return false;
		}
		
	}
	
	public boolean matchString(MessageContext context, String arguments) {
		if(!patterns.containsKey(context.getUser())) {
			sendMessageBack(context, "You have to compile a pattern first!");
			return false;
		}
		
		String testedString = arguments.substring("match ".length());
		Matcher matcher = patterns.get(context.getUser()).matcher(testedString);
		if(matcher.matches()) {
			sendMessageBack(context, "Match!");
			for(int i = 1; i <= matcher.groupCount(); ++i) {
				String group = matcher.group(i);
				if(group != null)
					sendMessageBack(context, "Group #" + i + ": " + group);
			}
		} else {
			sendMessageBack(context, "No match!");
		}
		
		return true;
	}

}
