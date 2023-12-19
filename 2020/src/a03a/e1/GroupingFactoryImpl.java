package a03a.e1;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GroupingFactoryImpl implements GroupingFactory {

    @Override
    public <G, V> Grouping<G, V> fromPairs(Iterable<Pair<G, V>> values) {
        List<Pair<G,V>> list = new LinkedList<>();
        values.forEach(list::add);
        return fromFunction(list.stream().map(p -> p.getY()).collect(Collectors.toSet()),
                v -> list.stream().filter(k -> k.getY().equals(v)).map(m -> m.getX()).findAny().get());
    }

    @Override
    public <V> Grouping<V, V> singletons(Set<V> values) {
        return fromFunction(values, v->v);
    }

    @Override
    public <V> Grouping<V, V> withChampion(Set<V> values, BiPredicate<V, V> sameGroup, Predicate<V> champion) {
        Set<V> champions = values.stream().filter(champion).collect(Collectors.toSet());
        return fromFunction(values, v->{
            return champions.stream().filter(c->sameGroup.test(v, c)).findAny().get();
        });
    }

    @Override
    public <G, V> Grouping<G, V> fromFunction(Set<V> values, Function<V, G> mapper) {
        return new Grouping<G,V>() {

            @Override
            public Set<V> getValuesOfGroup(G group) {
                return asMap().get(group);
            }

            @Override
            public Set<G> getGroups() {
                //return values.stream().collect(Collectors.groupingBy(mapper)).keySet();
                return asMap().keySet();
            }

            @Override
            public Optional<G> getGroupOf(V data) {
                return values.stream().collect(Collectors.groupingBy(mapper)).entrySet().stream().filter(e->e.getValue().contains(data)).map(k->k.getKey()).findAny();
            }

            @Override
            public Map<G, Set<V>> asMap() {
                return values.stream().collect(Collectors.groupingBy(mapper,Collectors.toSet()));
            }

            @Override
            public Grouping<G, V> combineGroups(G initial1, G initial2, G result) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'combineGroups'");
            }
            
        };
    }

}
