import chara.*;
import items.*;
import fight.fight;
import java.util.*;
import game.*;


public class Main{
    public static void main(String[] args){

        
        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger("./logs/logfile.txt");
        

    
        hero Vadim = new hero("Vadim", 8, 0, 1);
        rustySword Rustik = new rustySword("Rusty Sword");
        Vadim.add(Rustik);
        Vadim.equip(Rustik);
        System.out.println(Vadim.getAtk());
        clothArmor clothArmor = new clothArmor();
        Vadim.add(clothArmor);
        Vadim.equip(clothArmor);
        healthPotion HPotion = new healthPotion();
        Vadim.add(HPotion);
        heroSword Hsword = new heroSword();
        Vadim.add(Hsword);

        hero Yaroslave = new hero("Yarik", 5, 0, 3);
        Yaroslave.add(new rustySword("Very rusty sword"));
        Yaroslave.add(new healthPotion());

        ArrayList<actor> adv = new ArrayList<>();
        adv.add(Vadim);
        adv.add(Yaroslave);
        zhegan Zheka = new zhegan();
        adv.add(Zheka);
        bob BOB = new bob();
        adv.add(BOB);

        fight tutorial = new fight(adv, scanner);


        }
}