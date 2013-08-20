package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.State;
import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class QuitCommand extends Command {

	public QuitCommand() {
		super("quit", "quit", "Tells Emily to disconnect from the server.", "quit", true);
	}

	@Override
	public boolean execute(CommandContext context) {
		PircBotX bot = context.getBot();
		sendMessageBack(context, "Alright, off I go!", true);
		State.INSTANCE.setShouldQuit(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		bot.quitServer("Later!");
		return true;
	}

}
