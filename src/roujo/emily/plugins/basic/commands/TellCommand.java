package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class TellCommand extends Command {
	public TellCommand() {
		super("tell", "tell (?<args>.*)",
				"Tells something to a certain channel or user.",
				"tell (#channel|user) something", true);
	}

	@Override
	public boolean execute(CommandContext context) {
		String arguments = getArguments(context);
		int firstSpace = arguments.indexOf(' ');
		if (firstSpace == -1) {
			sendUsageBack(context);
		}

		sendMessageBack(context, "Alright!", true);

		String target = arguments.substring(0, firstSpace);
		String message = arguments.substring(firstSpace + 1).trim();

		PircBotX bot = context.getBot();
		bot.sendMessage(target, message);

		return true;
	}

}
