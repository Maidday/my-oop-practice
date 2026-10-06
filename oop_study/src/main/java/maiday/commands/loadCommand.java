package maiday.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import maiday.game.Logger;
import maiday.game.game;
import maiday.infra.Data.reader;
import maiday.text.textDocument;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class loadCommand {
    public static void load(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        String target = commandArguments.size() > 1 ? commandArguments.get(1) : "";
        switch(target){
            case "actor":
            case "hero":
                loadActor(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "item":
                loadItem(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "doc":
            case "document":
                readDoc(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            default: throw new IllegalArgumentException("load only supports actors, items and documents");
        }
    }

    private static void loadActor(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        ArrayList<Path> targets = new ArrayList<>();
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey();
            String value = option.getValue();
            switch(key){
                case "-f":
                case "--file":
                    targets.add(Path.of(value));
                    break;
                case "-d":
                case "--dir":
                case "--directory":
                    Path dir = Paths.get(value);
                    targets.addAll(getPaths(dir));
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + key);
                
            }
        }
        for(Path target : targets) game.getInstance().addActor(reader.get_instance().readHero(target));
    }

    private static void loadItem(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        ArrayList<Path> targets = new ArrayList<>();
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey();
            String value = option.getValue();
            switch(key){
                case "-f":
                case "--file":
                    targets.add(Path.of(value));
                    break;
                case "-d":
                case "--dir":
                case "--directory":
                    Path dir = Paths.get(value);
                    targets.addAll(getPaths(dir));
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + key);
                
            }
        }
        for(Path target : targets) game.getInstance().addItem(reader.get_instance().readItem(target));
    }

    private static void readDoc(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        Path target = null;
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey();
            String value = option.getValue();
            switch(key){
                case "-f":
                case "--file":
                    target = Path.of(value);
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + key);
            }
        }
        textDocument.getInstance().setInstance(reader.get_instance().readDoc(target));
    }

    private static ArrayList<Path> getPaths(Path dir){
        ArrayList<Path> ret = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(Files::isRegularFile)
                    .filter(t -> t.toString().endsWith(".json"))
                    .forEach(ret::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
