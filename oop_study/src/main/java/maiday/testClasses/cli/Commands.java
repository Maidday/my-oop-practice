package maiday.testClasses.cli;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import maiday.action.actions;
import maiday.chara.hero;
import maiday.chara.zhegan;
import maiday.commands.*;
import maiday.game.Logger;
import maiday.game.game;
import maiday.items.item;
import maiday.items.weapon;

public class Commands {

    private Scanner scanner;
    private Logger logger;
    private ArrayList<String> arguments;
    private String interfaceMode;
    private ArrayList<String> commandArguments;
    private HashMap<String, String> options;
    private HashMap<String, String> commandOptions;

    @Before 
    public void setUp(){
        try{
        scanner = new Scanner(System.in);
        logger = new Logger();
        arguments = new ArrayList<>();
        arguments.add("chars");
        interfaceMode = intefaceMode.getInterfaceMode(arguments, logger, scanner);
        commandArguments = new ArrayList<>();
        options = new HashMap<>();
        commandOptions = new HashMap<>();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createCommandTest_1() throws IOException{
        commandArguments.add("create");
        commandArguments.add("actor");
        commandOptions.put("--name", "Vadim");
        commandOptions.put("--atk", "1");
        commandOptions.put("--def", "2");
        commandOptions.put("--hp", "3");
        createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);
        assertTrue(game.getInstance().getKnownActors().toString().toLowerCase().contains("vadim"));
    }

    @Test
    public void createCommandTest_2() throws IOException{
        commandArguments.add("create");
        commandArguments.add("item");
        commandOptions.put("--name", "letuce");
        commandOptions.put("--desc", "green");
        commandOptions.put("--slot", "weapon");
        commandOptions.put("--stat", "5");
        createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);
        assertTrue(game.getInstance().getKnownItems().toString().toLowerCase().contains("letuce"));
    }

    @Test 
    public void createCommandTest_3() throws IOException{
        commandArguments.add("create");
        commandArguments.add("action");
        commandOptions.put("--name", "test");
        commandOptions.put("--desc", "test");
        createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);
        
        assertTrue(game.getInstance().getKnownActions().toString().toLowerCase().contains("test"));
    }

    @Test //if this test fired correctly, that means logic is also intact
    public void actCommandTest() throws IOException{
        hero Vadim = new hero("vadim", 2, 3, 4);
        zhegan enemy = new zhegan();
        game.getInstance().addActor(Vadim);
        game.getInstance().addActor(enemy);
        commandArguments.add("act");
        commandArguments.add(Vadim.getDefaultAct().getId().toString());
        commandArguments.add(Vadim.getId().toString());
        commandArguments.add(enemy.getId().toString());

        actCommand.act(arguments, commandArguments, options, commandOptions, scanner, logger);
    }

    @Test
    public void addCommandTest() throws IOException{
        hero Vadim = new hero("vadim", 1, 2, 3);
        game.getInstance().addActor(Vadim);
        commandArguments.add("add");
        commandOptions.put("--target", Vadim.getId().toString());
        weapon letuce = new weapon("letuce", 0);
        game.getInstance().addItem(letuce);
        commandOptions.put("--item", letuce.getId().toString());
        addCommand.addCharsVer(arguments, commandArguments, options, commandOptions, scanner, logger);
        assertTrue(Vadim.getInventory().values().contains(letuce));
    }



    @Test 
    public void listCommandTest_1() throws IOException{
        game.getInstance().addAction(new actions(null, "test", "test"));
       
        commandArguments.add("ls");
        commandArguments.add("actions");
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);
    }

    @Test 
    public void listCommandTest_2() throws IOException{
        game.getInstance().addActor(new zhegan());
        
        commandArguments.add("ls");
        commandArguments.add("actors");
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);

    }

    @Test 
    public void listCommandTest_3() throws IOException{
        game.getInstance().addItem(new item("letuce"));

        commandArguments.add("ls");
        commandArguments.add("items");
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);

    }


    @Test 
    public void listCommandTest_4() throws IOException{
        item letuce = new item("letuce");
        game.getInstance().addItem(letuce);
        commandArguments.add("ls");
        commandArguments.add("items");
        commandOptions.put("--id", letuce.getId().toString());
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);
    }
    @Test 
    public void listCommandTest_5() throws IOException{
        hero Vadim = new hero("Vadim", 2, 0, 0);
        game.getInstance().addActor(Vadim);
        commandArguments.add("ls");
        commandArguments.add("actors");
        commandOptions.put("--id", Vadim.getId().toString());
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);

    }
    @Test 
    public void listCommandTest_6() throws IOException{

        actions test = new actions(null, "test", "test");
        game.getInstance().addAction(test);
        commandArguments.add("ls");
        commandArguments.add("actions");
        commandOptions.put("--id", test.getId().toString());
        listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);
    }

    @Test //if this test fires correclty, this means infra test will too.
    public void saveLoadTest() throws IOException{
        hero Vadim = new hero("Vadim", 1, 2, 3);
        game.getInstance().addActor(Vadim);
        commandArguments.add("save");
        commandArguments.add("actor");
        commandOptions.put("--target", Vadim.getId().toString());
        saveCommand.save(arguments, commandArguments, options, commandOptions, scanner, logger);

        game.getInstance().removeActor(Vadim.getId());
        Path load = Path.of("data/actors/Vadim.json");
        commandArguments.clear();
        commandOptions.clear();
        commandArguments.add("load");
        commandArguments.add("actor");
        commandOptions.put("--file", load.toString());
        loadCommand.load(arguments, commandArguments, options, commandOptions, scanner, logger);

        assertTrue(game.getInstance().getKnownActors().containsKey(Vadim.getId()));
    }

    
    
}
