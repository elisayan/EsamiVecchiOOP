package a02b.e1;

import java.util.*;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new Cursor<X>() {
            int i = 0;

            @Override
            public X getElement() {
                if (i >= list.size()) {
                    return list.get(--i);
                }
                return list.get(i);
            }

            @Override
            public boolean advance() {
                i++;
                return i < list.size();
            }

        };
    }

    @Override
    public Cursor<Integer> naturals() {
        return new Cursor<Integer>() {
            int i;
            @Override
            public Integer getElement() {
                return i;
            }

            @Override
            public boolean advance() {
                i++;
                return true;
            }
            
        };
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new Cursor<X>() {

            private int i = 0;

            @Override
            public X getElement() {
                return input.getElement();
            }

            @Override
            public boolean advance() {
                i++;
                if (i < max) {
                    return input.advance();
                }
                return false;
            }

        };
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        do {
            consumer.accept(input.getElement());
        } while (input.advance());
        
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> output = new LinkedList<>();
        while (output.size() < max) {
            output.add(input.getElement());
            if (input.advance() == false) {
                return output;
            }
        }
        return output;
    }

}
