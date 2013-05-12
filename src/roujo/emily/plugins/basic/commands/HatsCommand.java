package roujo.emily.plugins.basic.commands;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class HatsCommand extends Command {

	public HatsCommand() {
		super("hats", ".*(?i)hats(?-i).*", "Who knows?", "hats", false);
	}

	@Override
	public boolean execute(MessageContext context) {
		sendMessageBack(context, "no u");
		return true;
	}
	
	
}
