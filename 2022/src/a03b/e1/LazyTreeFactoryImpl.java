package a03b.e1;

import java.util.*;
import java.util.function.*;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        // todo
        // return new LazyTreeImpl<X>(value, new Supplier<LazyTree<X>>() {

        // @Override
        // public LazyTree<X> get() {
        // return this.get();
        // }

        // }, new Supplier<LazyTree<X>>() {

        // @Override
        // public LazyTree<X> get() {
        // return this.get();
        // }

        // });

        // todoooo
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return value;
            }

            @Override
            public LazyTree<X> left() {
                return this;
            }

            @Override
            public LazyTree<X> right() {
                return this;
            }

        };
    }

    private <X> LazyTree<X> empty() {
        return new LazyTreeImpl<X>(Optional.empty(), null, null);
        // return new LazyTree<X>() {

        // @Override
        // public boolean hasRoot() {
        // return false;
        // }

        // @Override
        // public X root() {
        // return null;
        // }

        // @Override
        // public LazyTree<X> left() {
        // return null;
        // }

        // @Override
        // public LazyTree<X> right() {
        // return null;
        // }

        // };
    }

    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return new LazyTreeImpl<X>(Optional.of(root), new Supplier<LazyTree<X>>() {

            @Override
            public LazyTree<X> get() {
                var r = map.get(root);
                return r == null ? empty() : fromMap(r.getX(), map);
            }

        },
                () -> map.get(root) == null ? empty() : fromMap(map.get(root).getY(), map)

        );

        // return new LazyTree<X>() {

        // @Override
        // public boolean hasRoot() {
        // return root != null;
        // }

        // @Override
        // public X root() {
        // return root;
        // }

        // @Override
        // public LazyTree<X> left() {
        // if (!hasRoot()){
        // throw new NoSuchElementException();
        // }
        // var r = map.get(root);
        // return r == null ? empty() : fromMap(r.getX(), map);
        // }

        // @Override
        // public LazyTree<X> right() {
        // if (!hasRoot()){
        // throw new NoSuchElementException();
        // }
        // var r = map.get(root);
        // return r == null ? empty() : fromMap(r.getY(), map);
        // }

        // };
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
        return new LazyTreeImpl<X>(root, leftSupp, rightSupp);
    }

    @Override
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        return new LazyTreeImpl<X>(Optional.of(root), () -> fromTwoIterations(leftOp.apply(root), leftOp, rightOp),
                () -> fromTwoIterations(rightOp.apply(root), leftOp, rightOp));
    }

    @Override
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        if (bound == 0 || !tree.hasRoot()) {
            return this.empty();
        }
        return new LazyTreeImpl<>(Optional.of(tree.root()), () -> fromTreeWithBound(tree.left(), bound - 1),
                () -> fromTreeWithBound(tree.right(), bound - 1));
    }

}
