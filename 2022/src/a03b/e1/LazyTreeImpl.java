package a03b.e1;

import java.util.*;
import java.util.function.Supplier;

public class LazyTreeImpl<X> implements LazyTree<X> {

    private final Optional<X> root;
    private final Supplier<LazyTree<X>> left;
    private final Supplier<LazyTree<X>> right;


     public LazyTreeImpl(Optional<X> root, Supplier<LazyTree<X>> left, Supplier<LazyTree<X>> right) {
		this.root = root;
		this.left = left;
		this.right = right;
	}

    @Override
    public boolean hasRoot() {
        return this.root.isPresent();
    }

    @Override
    public X root() {
        return this.root.get();
    }

    @Override
    public LazyTree<X> left() {
        if (!hasRoot()){
            throw new NoSuchElementException();
        }
        return left.get();
    }

    @Override
    public LazyTree<X> right() {
        if (!hasRoot()){
            throw new NoSuchElementException();
        }
        return right.get();
    }
            
}
