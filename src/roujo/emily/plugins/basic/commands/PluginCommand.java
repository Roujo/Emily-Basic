package roujo.emily.plugins.basic.commands;

import java.io.File;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;
import roujo.emily.core.extensibility.PluginManager;

public class PluginCommand extends Command {	
	public PluginCommand() {
		super("plugin", "plugin (?<args>.*)", "Manages plugins", "plugin (load|unload|reload) pluginName", true);
	}
	
	@Override
	public boolean execute(MessageContext context) {
		// Validating arguments
		String rawArguments = getArguments(context);
		if(rawArguments == "") {
			sendUsageBack(context);
			return false;
		}
		
		String[] arguments = rawArguments.split(" ");
		if(arguments.length != 2) {
			sendUsageBack(context);
			return false;
		}
		
		switch(arguments[0].toLowerCase()) {
		case "load":
			return loadPlugin(context, arguments);
		case "unload":
			return unloadPlugin(context, arguments);
		case "reload":
			return reloadPlugin(context, arguments);
		default:
			sendUsageBack(context);
			return false;
		}
	}

	private boolean loadPlugin(MessageContext context, String[] arguments) {
		String pluginFileName = arguments[1];
		File pluginFile = new File("plugins/" + pluginFileName);
		if(!pluginFile.exists()) {
			sendMessageBack(context, "The file " + pluginFileName + " isn't in the \"plugins\" directory.");
			return false;
		}
		
		// So the file exists, let's try to load it
		sendMessageBack(context, "Loading plugin from " + pluginFileName + "...");
		String pluginName = PluginManager.getInstance().loadPlugin(pluginFile);
		if(pluginName != null) {
			sendMessageBack(context, "Plugin " + pluginName + " loaded!");
			return true;
		} else {
			sendMessageBack(context, "Failed to load a plugin from " + pluginFileName);
			return false;
		}
	}

	private boolean unloadPlugin(MessageContext context, String[] arguments) {
		String pluginName = arguments[1];
		sendMessageBack(context, "Unloading plugin " + pluginName + "...");
		if(PluginManager.getInstance().unloadPlugin(pluginName)) {
			sendMessageBack(context, "Plugin " + pluginName + " unloaded!");
			return true;
		} else {
			sendMessageBack(context, "Plugin " + pluginName + " failed to unload.");
			return false;
		}
	}
	
	private boolean reloadPlugin(MessageContext context, String[] arguments) {
		String pluginName = arguments[1];
		sendMessageBack(context, "Reloading plugin " + pluginName + "...");
		if(PluginManager.getInstance().reloadPlugin(pluginName)) {
			sendMessageBack(context, "Plugin " + pluginName + " reloaded!");
			return true;
		} else {
			sendMessageBack(context, "Plugin " + pluginName + " failed to reload.");
			return false;
		}
	}
}
