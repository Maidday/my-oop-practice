package maiday.infra.WebAPI;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import maiday.action.actions;
import maiday.chara.actor;
import maiday.chara.hero;
import maiday.game.game;
import maiday.infra.Data.reader;
import maiday.items.weapon;
import maiday.records.charRecord;
import maiday.records.skillRecord;
import maiday.records.weaponRecord;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public abstract class pull {
    

    public static void pullCharacter(String name){

        try{
            HttpClient httpClient = HttpClient.newHttpClient();
        
            HttpRequest getRequest = HttpRequest.newBuilder()
                                                    .uri(new URI("https://genshin.jmp.blue/characters/" + name.toLowerCase().replace(" ", "-")))
                                                    .GET()
                                                    .build();
        

            HttpResponse<Path> getResponse = httpClient.send(getRequest, BodyHandlers.ofFile(Path.of("tmp/" + name + ".json")));
            int responseCode = getResponse.statusCode();

            if(responseCode != 200){
                throw new RuntimeException("HTTP response code: " + responseCode);
            }
            else{
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(getResponse.body());
                charRecord actorRecord = new charRecord(node.get("name").asString(), node.get("description").asString(), new ArrayList<actions>(), node.get("rarity").asInt());

                hero pulledHero = new hero(actorRecord.name(), actorRecord.rarity()*30, actorRecord.rarity(), actorRecord.rarity()*3, actorRecord.description());

                JsonNode skillNode = node.get("skillTalents");
                JsonNode skill_0 = skillNode.get(0);
                JsonNode skill_1 = skillNode.get(1);
                JsonNode skill_2 = skillNode.get(2);
                skillRecord skill0 = new skillRecord(skill_0.get("name").asString(), skill_0.get("description").asString());
                skillRecord skill1 = new skillRecord(skill_1.get("name").asString(), skill_1.get("description").asString());
                skillRecord skill2 = new skillRecord(skill_2.get("name").asString(), skill_2.get("description").asString());

                actions act0 = new actions(pulledHero, skill0.name(), skill0.description());
                actions act1 = new actions(pulledHero, skill1.name(), skill1.description());
                actions act2 = new actions(pulledHero, skill2.name(), skill2.description());

                pulledHero.add(act0);
                pulledHero.add(act1);
                pulledHero.add(act2);

                game.getInstance().addActor(pulledHero);

            }



        }
        catch(URISyntaxException e){
            e.printStackTrace();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void pullWeapon(String name){
        try{
            HttpClient httpClient = HttpClient.newHttpClient();
        
            HttpRequest getRequest = HttpRequest.newBuilder()
                                                    .uri(new URI("https://genshin.jmp.blue/weapons/" + name.toLowerCase().replace(" ", "-")))
                                                    .GET()
                                                    .build();
        

            HttpResponse<Path> getResponse = httpClient.send(getRequest, BodyHandlers.ofFile(Path.of("tmp/" + name + ".json")));
            int responseCode = getResponse.statusCode();

            if(responseCode != 200){
                throw new RuntimeException("HTTP response code: " + responseCode);
            }
            else{
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(getResponse.body());
                weaponRecord record = new weaponRecord(node.get("name").asString(), node.get("passiveDesc").asString() + "(flavour only)", node.get("baseAttack").asInt());

                weapon pulledWeapon = new weapon(record.name(), record.atk(), record.desc());
                game.getInstance().addItem(pulledWeapon);
            }
        }
        catch(URISyntaxException e){
            e.printStackTrace();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
