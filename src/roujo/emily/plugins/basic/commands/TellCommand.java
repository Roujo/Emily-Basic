package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class TellCommand extends Command {
	public TellCommand() {
		super("tell", "tell (?<args>.*)",
				"Tells something to a certain channel or user.",
				"tell [#channel|user] something", true);
	}

	@Override
	public boolean execute(MessageContext context) {
		String arguments = getArguments(context);
		int firstSpace = arguments.indexOf(' ');
		if (firstSpace == -1) {
			sendUsageBack(context);
		}

		sendMessageBack(context, "Alright!");

		String target = arguments.substring(0, firstSpace);
		String message = arguments.substring(firstSpace + 1).trim();

		PircBotX bot = context.getBot();
		bot.sendMessage(target, message);

		return true;
	}

}
