package a01a.e1;

import java.util.*;

public class TreeFactoryImpl implements TreeFactory {

    @Override
    public <E> Tree<E> singleValue(E root) {
        return new Tree<E>() {

            @Override
            public E getRoot() {
                return root;
            }

            @Override
            public List<Tree<E>> getChildren() {
                return new LinkedList<Tree<E>>(root);
            }

            @Override
            public Set<E> getLeafs() {
                return new HashSet<E>(root);
            }

            @Override
            public Set<E> getAll() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAll'");
            }
            
        };
    }

    @Override
    public <E> Tree<E> twoChildren(E root, Tree<E> child1, Tree<E> child2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'twoChildren'");
    }

    @Override
    public <E> Tree<E> oneLevel(E root, List<E> children) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oneLevel'");
    }

    @Override
    public <E> Tree<E> chain(E root, List<E> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chain'");
    }

}
