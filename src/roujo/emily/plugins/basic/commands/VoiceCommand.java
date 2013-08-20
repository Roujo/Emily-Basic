package roujo.emily.plugins.basic.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.Channel;
import org.pircbotx.User;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class VoiceCommand extends Command {
	private Pattern privateArgsPattern;
	private Pattern publicArgsPattern;
	
	public VoiceCommand() {
		super("voice", "voice (?<args>.*)", "Voices a user",
				"voice user | voice #channel user", false);
		this.privateArgsPattern = Pattern.compile("(#[^ ,]+) ([^# ,]+)");
		this.publicArgsPattern = Pattern.compile("([^# ,]+)");
	}

	@Override
	public boolean execute(CommandContext context) {
		String arguments = getArguments(context);
		
		if(context.isPrivateMessage()) {
			Matcher matcher = privateArgsPattern.matcher(arguments);
			if(matcher.matches()) {
				voice(context, matcher.group(1), matcher.group(2));
				return true;
			}
		} else {
			Matcher matcher = publicArgsPattern.matcher(arguments);
			if(matcher.matches()) {
				voice(context, context.getOrigin(), matcher.group(1));
				return true;
			}
		}
		sendUsageBack(context);
		return false;
	}
	
	private void voice(CommandContext context, String channel, String user) {
		Channel c = context.getBot().getChannel(channel);
		User u = context.getBot().getUser(user);
		c.voice(u);
	}

}
