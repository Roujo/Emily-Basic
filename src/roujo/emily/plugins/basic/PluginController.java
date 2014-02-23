package roujo.emily.plugins.basic;

import java.util.LinkedList;
import java.util.List;

import roujo.emily.core.contexts.CommandContext;
import roujo.emily.core.contexts.MessageContext;
import roujo.emily.core.extensibility.Plugin;
import roujo.emily.core.extensibility.util.Command;
import roujo.emily.core.extensibility.util.Reaction;
import roujo.emily.plugins.basic.commands.EchoCommand;
import roujo.emily.plugins.basic.commands.JoinCommand;
import roujo.emily.plugins.basic.commands.PartCommand;
import roujo.emily.plugins.basic.commands.PluginCommand;
import roujo.emily.plugins.basic.commands.QuitCommand;
import roujo.emily.plugins.basic.commands.RegexCommand;
import roujo.emily.plugins.basic.commands.RollCommand;
import roujo.emily.plugins.basic.commands.SlapCommand;
import roujo.emily.plugins.basic.commands.TellCommand;
import roujo.emily.plugins.basic.commands.TimeCommand;
import roujo.emily.plugins.basic.commands.VoiceCommand;
import roujo.emily.plugins.basic.reactions.HatsCommand;
import roujo.emily.plugins.basic.reactions.LionCommand;

public class PluginController extends Plugin {
	private static final String PLUGIN_NAME = "Basic";

	private List<Command> commands;
    private List<Reaction> reactions;

	public PluginController() {
		super(PLUGIN_NAME);
		commands = new LinkedList<Command>();
        reactions = new LinkedList<Reaction>();
	}

	@Override
	public void onMessage(MessageContext context) {
        for(Reaction reaction : reactions) {
            if(reaction.getPattern().matcher(context.getMessage()).matches()) {
                reaction.react(context);
            }
        }
	};

	@Override
	public void onCommand(CommandContext context) {
		for (Command command : commands) {
			if(command.getPattern().matcher(context.getCommandString()).matches()) {
				command.execute(context);
			}
		}
	}

	@Override
	public boolean load() {
		commands.add(new EchoCommand());
		commands.add(new JoinCommand());
		commands.add(new PartCommand());
		commands.add(new PluginCommand());
		commands.add(new QuitCommand());
		commands.add(new RegexCommand());
		commands.add(new RollCommand());
		commands.add(new SlapCommand());
		commands.add(new TellCommand());
		commands.add(new TimeCommand());
		commands.add(new VoiceCommand());

        reactions.add(new HatsCommand());
        reactions.add(new LionCommand());
		return true;
	}

	@Override
	public boolean unload() {
		commands.clear();
		return true;
	}
}
