package roujo.emily.plugins.basic.reactions;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class HatsCommand extends Command {

	public HatsCommand() {
		super("hats", ".*(?i)hats(?-i).*", "Who knows?", "hats", false);
	}

	@Override
	public boolean execute(CommandContext context) {
		sendMessageBack(context, "no u");
		return true;
	}
	
	
}
