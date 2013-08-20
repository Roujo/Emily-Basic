package roujo.emily.plugins.basic.commands;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class TimeCommand extends Command {

	public TimeCommand() {
		super("time", "time", "Gives the current time.", "time", false);
	}

	@Override
	public boolean execute(CommandContext context) {
		String time = new java.util.Date().toString();
		sendMessageBack(context, "The time is now " + time, true);
		return true;
	}

}
