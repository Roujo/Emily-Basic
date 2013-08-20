package roujo.emily.plugins.basic.commands;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.extensibility.util.Command;

public class EchoCommand extends Command {

	public EchoCommand() {
		super("Echo", "echo (?<args>.*)", "Repeats after you.", "echo something or other", false);
	}

	@Override
	public boolean execute(CommandContext context) {
		String echoMessage = getArguments(context);
		context.getBot().sendMessage(context.getOrigin(), echoMessage);
		return true;
	}

}
