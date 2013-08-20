package roujo.emily.plugins.basic.commands;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class PartCommand extends Command {

	public PartCommand() {
		super("part", "part (?<args>.*)", "Parts with the given channel", "part #channel reason", true);
	}

	@Override
	public boolean execute(CommandContext context) {
		String arguments = getArguments(context);
		if(arguments.equals("")) {
			sendUsageBack(context);
			return false;
		}
		
		String[] args = arguments.split(" ");
		PircBotX bot = context.getBot();
		for(Channel channel : bot.getChannels()) {
			if(channel.getName().equals(args[0])) {
				sendMessageBack(context, "Alright!", true);
				bot.sendMessage(args[0], "See you later!");
				if(args.length > 1)
					bot.partChannel(channel, args[1]);
				else
					bot.partChannel(channel, "Off and away...");
				return true;
			}
		}
		sendMessageBack(context, "I'm not in " + args[0] + " right now.");
		return false;
	}

	
}
