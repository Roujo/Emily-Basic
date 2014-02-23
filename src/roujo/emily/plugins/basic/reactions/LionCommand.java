package roujo.emily.plugins.basic.reactions;

import java.util.Calendar;

import org.pircbotx.PircBotX;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.contexts.MessageContext;
import roujo.emily.core.extensibility.util.Command;
import roujo.emily.core.extensibility.util.Reaction;

public class LionCommand extends Reaction {
	private Calendar nextLionAllowed;

	public LionCommand() {
		super(">:3", ".*>:3.*", "...is that a lion?");
	}

	@Override
	public boolean react(MessageContext context) {
		if (nextLionAllowed == null
				|| Calendar.getInstance().after(nextLionAllowed)) {
			PircBotX bot = context.getBot();
			bot.sendMessage(context.getOrigin(), "OMG IT'S A LION GET IN THE CAR!");
			bot.sendAction(context.getOrigin(), "gets in the car.");
			nextLionAllowed = Calendar.getInstance();
			nextLionAllowed.add(Calendar.MINUTE, 5);
			return true;
		} else {
			return false;
		}
	}
}
