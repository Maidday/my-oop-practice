package maiday.commands;

import java.io.IOException;
import java.util.*;
import maiday.game.*;
import maiday.records.*;
import maiday.text.*;


public abstract class commandListener {
    private commandListener() { }

    private static final commandListener INSTANCE = new commandListener(){}; //listener is a singleton. refer to it as listener.getInstance()...

    public static commandListener getInstance() {
        return INSTANCE;
    }
    
    public void listener(ArrayList<String> arguments, HashMap<String, String> options, Scanner scanner, Logger logger, String interfaceMode) throws IOException, Exception{
        logger.log("Awaiting for command input now\n");
        System.out.print(">");
        String[] command = scanner.nextLine().split(" ");
        parserResult parseCommand = parser.parse(command);
        ArrayList<String> commandArguments = parseCommand.arguments();
        HashMap<String, String> commandOptions = parseCommand.options();
        logger.log("Args: " + commandArguments.toString() + "\nOptions: " + commandOptions.toString());
        String iHateThatJavaDoesNotKnowWhatDeleteIs = commandArguments.get(0).toLowerCase();
        if(interfaceMode.equals("chars")){
        switch(iHateThatJavaDoesNotKnowWhatDeleteIs){
            case "create":
                createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "ls":
                listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "act":
                actCommand.act(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "add":
                addCommand.addCharsVer(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "save":
                saveCommand.save(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "load":
                loadCommand.load(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "help":
                logger.log("create - create a character, an item or an action.(actor/item/action as arguments and --name, --desc, --hp, --atk, --def when appliable.)" +
                        "\nls - list all items of category or details about specific item(accepts catgeory as an argument and an id or name of item as options)" +
                        "\nact - perform an act or ability on target(Accepts <action><actor><target> arguments.)" +
                        "\nadd - add an item or an action to an actor(accepts --actor and --item/--action options)" +
                        "\nexit - terminates program." +
                        "\nsave - writes target hero or item to json file." +
                        "\nload - loads target file as hero or item." +
                        "\npull - pulls character or weapon from web api, specified by name."
                );
                break;
            case "pull":
                pullCommand.pull(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown command for characters");

        }
        
    }
    else if(interfaceMode.equals("text")){
        switch(iHateThatJavaDoesNotKnowWhatDeleteIs){
            case "pwd":
                pwdCommand.pwd(logger);
                break;
            case "print":
                printCommand.print(commandOptions.containsKey("--whole"), commandOptions.containsKey("--id"), logger);
                break;
            case "add":
                addTextCommand.add(commandArguments, commandOptions, scanner, logger);
                break;
            case "rm":
                rmCommand.rm(commandArguments, scanner, logger);
                break;
            case "up":
                upCommand.up(logger);
                break;
            case "cd":
                cdCommand.cd(commandArguments, commandOptions, logger);
                break;
            case "help":
                logger.log("pwd - show current path\nprint [--whole] [--id] - print current element or whole document\nadd <container|leaf> <type> - add a text element\nrm [<elem_name>] - remove an element\nup - move to parent\ncd <path>|--id <container_id> - change current container\nexit - terminates program.");
                break;
            case "save":
                commandArguments.add(1, "document");
                saveCommand.save(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "load":
                commandArguments.add(1, "document");
                loadCommand.load(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            
            case "exit":
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown command for text document");
        }
    }
}


    
}
