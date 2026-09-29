package commands;
import java.io.IOException;
import java.util.*;
import game.*;
import items.*;
import chara.hero;

public abstract class createCommand{
    
    public static void create(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException, Exception{
        if(commandArguments.get(1).toLowerCase().equals("char") 
            || commandArguments.get(1).toLowerCase().equals("character")) createCharacter(arguments, commandArguments, options, commandOptions, scanner, logger);
        else if (commandArguments.get(1).toLowerCase().equals("item")) createItem(arguments, commandArguments, options, commandOptions, scanner, logger);
        else if (commandArguments.get(1).toLowerCase().equals("ability")) createAction(arguments, commandArguments, options, commandOptions, scanner, logger);

    }

    private static void createCharacter(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException, Exception{
        String name = "";
        int hp = 0;
        int atk = 0;
        int def = 0;
        if(commandOptions.size()==1){
            for(String key : commandOptions.keySet()){
            if(key.toLowerCase().equals("-h")|| key.toLowerCase().equals("--help")){
                logger.log("viable parameters for create character command: \n -h or --help will print this message if no other options passed. \n -n or --name determines character's name(not class name) \n -a or --atk determines starting attack. \n -h or --hp determines starting health. \n -d or --def determines starting defence. \n \n at least 4 options required to successfully create a character.");
            }
            else throw new Exception("Unknown option for command create character: " + commandOptions.get(key));
        }
    }
        else if(commandOptions.size()<4){

        logger.log("Creating new character. \n Choose name for a character.");
        name = scanner.nextLine();
        logger.log("Enter starting health points for a character.\n");
        hp = scanner.nextInt();
        logger.log("Enter starting attack for a character.\n");
        atk = scanner.nextInt();
        logger.log("Enter starting defense for your character.\n");
        def = scanner.nextInt();
        
    }
    else{
        for (Map.Entry<String, String> entry : commandOptions.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();

            switch (key) {
                case "-n":
                case "--name":
                    name = value;
                    break;
                case "-h":
                case "--hp":
                    hp = Integer.parseInt(value);
                    break;
                case "-a":
                case "--atk":
                    atk = Integer.parseInt(value);
                    break;
                case "-d":
                case "--def":
                    def = Integer.parseInt(value);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Unknown option for command create character: " + entry.getKey());
                }
            }
        }
        hero customHero = new hero(name, hp, def, atk);
        game.getInstance().addActor(customHero);
    }
    
    private static void createItem(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException, Exception{
        String name = "";
        String desc = "";
        String type = "armor";
        int stat = 0;
        if(commandOptions.size()==1){
            for(String key : commandOptions.keySet()){
            if(key.toLowerCase().equals("-h")|| key.toLowerCase().equals("--help")){
                logger.log("viable parameters for create item command: \n -h or --help will print this message if no other options passed. \n -n or --name determines item name(not class name) \n -t or --type can be either armor or weapon. \n -s or --stat determines attack for weapons and defence for armor. \n -d or --desc description. \n \n at least 4 options required to successfully create an item with a command.");
                }
            else throw new Exception("Unknown option for command create item: " + commandOptions.get(key));
            }
        }
        else if(commandOptions.size()<4){
            logger.log("Creating new item. \n Choose name for an item.");
            name = scanner.nextLine();
            logger.log("Enter type of item(armor or weapon)\n");
            type = scanner.nextLine().toLowerCase().replace(" ", "");
            if(!(type.equals("armor")) && !(type.equals("weapon"))){
                        throw new IllegalArgumentException(
                        "Incorrect argument for option. type accepts only weapon or armor");
                    }
            logger.log("Enter parameters for item(atk for weapons and def for armor)\n");
            stat = scanner.nextInt();
            logger.log("Enter description for your item.\n");
            desc = scanner.nextLine();
        }
        else{
        for (Map.Entry<String, String> entry : commandOptions.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();

            switch (key){
                case "-n":
                case "--name":
                    name = value;
                    break;
                case "-d":
                case "--desc":
                    desc = value;
                    break;
                case "-t":
                case "--type":
                    type = value;
                    if(!(type.toLowerCase().equals("armor")) && !(type.toLowerCase().equals("weapon"))){
                        throw new IllegalArgumentException(
                        "Incorrect argument for option: " + entry.getKey() + " type accepts only weapon or armor");
                    }
                    break;
                case "-a":
                case "--atk":
                case "--def":
                case "-s":
                case "--stat":
                    stat = Integer.parseInt(value);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Unknown option for command create character: " + entry.getKey());
                }

            }
        }
        if(type.equals("armor")){
            armor customArmor = new armor(name, stat, desc);
            game.getInstance().addItem(customArmor);
        }
        else if(type.equals("weapon")){
            weapon customWeapon = new weapon(name, stat, desc);
            game.getInstance().addItem(customWeapon);
        }
    }   
    

    private static void createAction(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{

    }
}