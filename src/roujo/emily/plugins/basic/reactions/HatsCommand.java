package roujo.emily.plugins.basic.reactions;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.contexts.MessageContext;
import roujo.emily.core.extensibility.util.Command;
import roujo.emily.core.extensibility.util.Reaction;

public class HatsCommand extends Reaction {

	public HatsCommand() {
		super("hats", ".*(?i)hats(?-i).*", "Who knows?");
	}

	@Override
	public boolean react(MessageContext context) {
		sendMessageBack(context, "no u");
		return true;
	}
	
	
}
