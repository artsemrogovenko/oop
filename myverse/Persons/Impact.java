package Persons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
    

public class Impact {

    /**ближайшая цель  */
    public BaseHero nearestPoint(BaseHero current, ArrayList<BaseHero> team) throws NotAliveExeption   {

        int[] thispos= current.getPos();        
        LinkedHashMap<Double,Integer> result = new LinkedHashMap<>(team.size());
        for (BaseHero hero : team) {//добавление растояния
            if(hero.getStatus().equals("Жив") && (current.getId()!=hero.getId())){ // если персонаж жив и персонаж не является самим собой
            result.put(current.distance(thispos,hero.getPos()),hero.getId());}
        }

        result.entrySet().stream() //сортировка по растоянию
        .sorted(Map.Entry.<Double, Integer>comparingByKey());//.reversed())
        //.forEach(System.out::println); // вывод растояние = id

        Map.Entry<Double, Integer> minEntry = result.entrySet().stream()// поиск наименьшего ключа
        .min(Comparator.comparing(Map.Entry::getKey))
        .orElse(null);
        result.clear();

        int countAlive=0;
        for (BaseHero e : team) {
            if(e.getStatus().equals("Жив")){
                countAlive++;
            }
        }  
        if(countAlive==0){    
            throw new NotAliveExeption("Нет живых противников");
        }
        
        for (BaseHero i : team) {// выбор обьекта по id
            if(minEntry.getValue()==i.getId() & minEntry.getValue()!=null){
                //System.out.println("ближайший " + i.getId());
                return i;
            }
        }
  
        return null;
    }
}
