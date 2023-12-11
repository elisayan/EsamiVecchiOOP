package a02a.e1;

import static a02a.e1.Diet.Nutrient.CARBS;
import static a02a.e1.Diet.Nutrient.FAT;
import static a02a.e1.Diet.Nutrient.PROTEINS;

import java.util.*;

import a02a.e1.Diet.Nutrient;

public class DietFactoryImpl implements DietFactory {

    private Integer getCalories(Map<Nutrient,Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }
    @Override
    public Diet standard() {
        return new Diet() {

            private Map<String, Map<Nutrient, Integer>> foods = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foods.put(name, nutritionMap);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                Double sumCalories = 0.0;
                for (Map.Entry<String,Double> entry : dietMap.entrySet()) {
                    sumCalories += entry.getValue() * getCalories(foods.get(entry.getKey())) / 100;
                }
                return sumCalories >= 1500 && sumCalories <= 2000;
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        return new Diet() {

            private Map<String, Map<Nutrient, Integer>> foods = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                if (nutritionMap.get(CARBS) <= 300) {
                    foods.put(name, nutritionMap);
                }                
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {                
                Double sumCalories = 0.0;
                for (Map.Entry<String,Double> entry : dietMap.entrySet()) {
                    sumCalories += entry.getValue() * getCalories(foods.get(entry.getKey())) / 100;
                }
                return sumCalories >= 1000 && sumCalories <= 1500;
            }
            
        };
    }

    @Override
    public Diet highProtein() {        
        return new Diet() {

            private Map<String, Map<Nutrient, Integer>> foods = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foods.put(name, nutritionMap);             
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {                
                Double sumCalories = 0.0;
                Double sumCarbs = 0.0;
                Double sumProtein = 0.0;
                for (Map.Entry<String,Double> entry : dietMap.entrySet()) {
                    Double currentCalories = entry.getValue() * getCalories(foods.get(entry.getKey())) / 100;
                    Double currentCarbs = entry.getValue() * foods.get(entry.getKey()).get(Nutrient.CARBS) / 100;
                    Double currentProtein = entry.getValue() * foods.get(entry.getKey()).get(Nutrient.PROTEINS) / 100;
                    sumCalories += currentCalories;
                    sumCarbs += currentCarbs;
                    sumProtein += currentProtein;
                }
                return sumCalories >= 2000 && sumCalories <= 2500 && sumCalories <= 300 && sumProtein >= 1300;
            }
            
        };
    }

    @Override
    public Diet balanced() {
        
        return new Diet() {

            private Map<String, Map<Nutrient, Integer>> foods = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foods.put(name, nutritionMap);          
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {                
                Double sumCalories = 0.0;
                Double sumCarbs = 0.0;
                Double sumProtein = 0.0;
                Double sumFat = 0.0;
                for (Map.Entry<String,Double> entry : dietMap.entrySet()) {
                    sumCalories += entry.getValue()* getCalories(foods.get(entry.getKey())) / 100;
                    Double currentCalories = entry.getValue() * getCalories(foods.get(entry.getKey())) / 100;
                    Double currentCarbs = entry.getValue() * foods.get(entry.getKey()).get(Nutrient.CARBS) / 100;
                    Double currentProtein = entry.getValue() * foods.get(entry.getKey()).get(Nutrient.PROTEINS) / 100;
                    Double currentFat = entry.getValue() * foods.get(entry.getKey()).get(Nutrient.FAT) / 100;
                    sumCalories += currentCalories;
                    sumCarbs += currentCarbs;
                    sumProtein += currentProtein;
                    sumFat += currentFat;
                }
                return sumCalories >= 1600 && sumCalories <= 2000 && sumCarbs >= 600 && sumProtein >= 600 && sumFat >= 400 && sumFat+sumProtein <= 1100;
            }
            
        };
    }
}