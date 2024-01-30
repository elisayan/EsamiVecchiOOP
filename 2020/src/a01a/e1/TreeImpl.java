package a01a.e1;

import java.util.*;
import java.util.stream.*;

public class TreeImpl<E> implements Tree<E>{

    private E root;
    private List<Tree<E>> children;

    public TreeImpl(E root, List<Tree<E>> children) {
        this.root = root;
        this.children = children;
    }

    @Override
    public E getRoot() {
        return root;
    }

    @Override
    public List<Tree<E>> getChildren() {
        return children;
    }

    @Override
    public Set<E> getLeafs() {
        return children.stream().flatMap(l->l.getLeafs().stream()).collect(Collectors.toSet());
    }

    @Override
    public Set<E> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
