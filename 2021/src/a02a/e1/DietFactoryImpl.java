package a02a.e1;

import java.util.*;

public class DietFactoryImpl implements DietFactory {

    @Override
    public Diet standard() {
        return new Diet() {

            private Map<String,Integer> foods = new HashMap<>();
            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foods.put(name, nutritionMap.get(name));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                // private Double calories = dietMap.values().stream().filter(e -> e.);
                // return calories <= 2000 && calories >= 1500;
                return false;
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lowCarb'");
    }

    @Override
    public Diet highProtein() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'highProtein'");
    }

    @Override
    public Diet balanced() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balanced'");
    }

}
