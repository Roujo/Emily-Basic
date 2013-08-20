package roujo.emily.plugins.basic.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.pircbotx.User;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class RegexCommand extends Command {
	private final Map<User, Pattern> patterns;
	
	public RegexCommand() {
		super("regex", "regex (?<args>.*)", "Compiles and matches regex",
				"regex (compile <regex>|match <string>)", false);
		patterns = new TreeMap<User, Pattern>();
	}

	@Override
	public boolean execute(CommandContext context) {
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
	
	public boolean compilePattern(CommandContext context, String arguments) {
		try {
			String patternString = arguments.substring("compile ".length());
			patterns.put(context.getSender(), Pattern.compile(patternString));
			sendMessageBack(context, "Pattern compiled!", true);
			return true;
		} catch (PatternSyntaxException e) {
			sendMessageBack(context, "The pattern was invalid.");
			return false;
		}
		
	}
	
	public boolean matchString(CommandContext context, String arguments) {
		if(!patterns.containsKey(context.getSender())) {
			sendMessageBack(context, "You have to compile a pattern first!", true);
			return false;
		}
		
		String testedString = arguments.substring("match ".length());
		Matcher matcher = patterns.get(context.getSender()).matcher(testedString);
		if(matcher.matches()) {
			sendMessageBack(context, "Match!", true);
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
