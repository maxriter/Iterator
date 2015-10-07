package ua.skillsup.pickiterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.skillsup.pickiterator.exception.IteratorIsEmptyException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PickIterator<T> implements Iterator<T> {
    private Iterator<T> it;
    private T current;
    boolean isFirst = true;
    boolean isEmpty = false;

    public PickIterator(Iterator<T> iterator) {
        it = iterator;
        if (it.hasNext() == true) {
            current = it.next();
        } else isEmpty = true;
    }

    public boolean hasNext() {
        if ((isFirst == true) && (isEmpty == false)) {
            return true;
        }
        return it.hasNext();
    }

    public T next() {
        if ((isEmpty==true)&&(isFirst==true)) throw new NoSuchElementException();
        if (isFirst == true) {
            isFirst = false;
            return current;
        }
        isFirst = false;
        current = it.next();
        return current;
    }

    public T pick() throws IteratorIsEmptyException {
        if  ((isEmpty == true)||(it.hasNext()==false)) throw new IteratorIsEmptyException();
        return current;
    }

    public void remove() {
            throw new NotImplementedException();

    }

}
