package Persons.Mercenary;

import java.util.ArrayList;

import Persons.BaseHero;
import Persons.Impact;
import Persons.NotAliveExeption;
import Persons.Peasant;

public class Mercenary extends Peasant {

    private static BaseHero hero2;
    private Impact targ = new Impact();
   
    public Mercenary(String name, int speed, String ammo, int x, int y) {
        super(name, speed, ammo, x, y);  
        super.hp=10;
        super.maxHp=10;
        super.patrons=20;
    }

    @Override
    public void step(ArrayList<BaseHero> enemy, ArrayList<BaseHero> myTeam)throws NotAliveExeption  { // стрелять если жив и есть ли патроны
        if (super.status.equals("Жив") & this.patrons>0) {

            hero2 = targ.nearestPoint(this, enemy);
 
            System.out.printf("%s id:%d с %d %s нашел цель %s \n",
                   this.getinfo()+" "+this.getName(),this.getId(), super.patrons,this.showAmmo(), hero2.Info());

            if (distance(this.getPos(), hero2.getPos()) < 2 ) {
                this.setIcon("⚔-", 1);
                attack(hero2);
            }            
            else{
                move(this, hero2, myTeam);
            }      
            return;     
        } 

    }

 


   
}