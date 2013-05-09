package roujo.emily.plugins.basic.commands;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;

public class TimeCommand extends Command {

	public TimeCommand() {
		super("time", "time", "Gives the current time.", "time", false);
	}

	@Override
	public boolean execute(MessageContext context) {
		String time = new java.util.Date().toString();
		sendMessageBack(context, "The time is now " + time);
		return true;
	}

}
