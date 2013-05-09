package roujo.emily.plugins.basic.commands;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class VoiceCommand extends Command {

	public VoiceCommand() {
		super("voice", "voice (?<args>.*)", "Voices a user",
				"voice user | voice #channel user", false);
	}

	@Override
	public boolean execute(MessageContext context) {
		String arguments = getArguments(context);
		if (context.isPrivateMessage() && arguments.matches("#[^ ,]+ [^# ,]+"))
			context.getBot().sendMessage("ChanServ", "voice " + arguments);
		else if (!context.isPrivateMessage() && arguments.matches("[^# ,]+"))
			context.getBot().sendMessage("ChanServ",
					"voice " + context.getChannel() + " " + arguments);
		else
			return false;
		return true;
	}

}
