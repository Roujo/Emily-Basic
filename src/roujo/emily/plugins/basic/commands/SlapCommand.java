package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class SlapCommand extends Command {	
	public SlapCommand() {
		super("slap", "slap (?<args>.*)", "Slaps the given user", "slap user", false);
	}
	
	@Override
	public boolean execute(CommandContext context) {
		// Validating arguments
		String targetUser = getArguments(context).split(" ")[0];
		
		PircBotX bot = context.getBot();
		if(context.isPrivateMessage()) {
			sendMessageBack(context, "It's just us two. Why would you want that?", true);
			return false;
		} else {
			bot.sendAction(context.getOrigin(), "slaps " + targetUser + " around with a large trout.");		
			return true;
		}
	}

}
