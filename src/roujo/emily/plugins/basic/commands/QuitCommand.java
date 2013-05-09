package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class QuitCommand extends Command {

	public QuitCommand() {
		super("quit", "quit", "Tells Emily to disconnect from the server.", "quit", true);
	}

	@Override
	public boolean execute(MessageContext context) {
		PircBotX bot = context.getBot();
		sendMessageBack(context, "Alright, off I go!");
		context.getState().setShouldQuit(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		bot.quitServer("Later!");
		return true;
	}

}
