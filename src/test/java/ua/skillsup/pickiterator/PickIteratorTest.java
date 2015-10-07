package ua.skillsup.pickiterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.skillsup.pickiterator.exception.IteratorIsEmptyException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class PickIteratorTest {

    PickIterator pickIterator;
    PickIterator pickIteratorEmpty;

    @Before
    public void initMock() {
        ArrayList<String> mockedList = new ArrayList<String>();
        mockedList.add("A");
        mockedList.add("B");
        mockedList.add("C");
        Iterator iterator = mockedList.iterator();
        pickIterator = new PickIterator(iterator);
        ArrayList<String> emptyList = new ArrayList<String>();
        Iterator it = emptyList.iterator();
        pickIteratorEmpty = new PickIterator(it);
    }

    @After
    public void clear() {
        pickIterator = null;
        pickIteratorEmpty = null;
    }

    @Test
    public void testConstructorIsSuccess() {
        assertEquals("A", pickIterator.next());
        assertEquals("B", pickIterator.next());
        assertEquals("C", pickIterator.next());
        assertFalse(pickIterator.hasNext());
        assertFalse(pickIteratorEmpty.hasNext());
    }

    @Test
    public void testHasNextMethodIfIteratorIsEmpty() {
        assertFalse(pickIteratorEmpty.hasNext());
    }

    @Test
    public void testHasNextMethodIfIteratorNotEmpty() {
        assertTrue(pickIterator.hasNext());
    }

    @Test
    public void testHasNextMethodIfIteratorIsEmptyAfterIterates() {
        while (pickIterator.hasNext()) {
            pickIterator.next();
        }
        assertFalse(pickIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextMethodIfIteratorIsEmpty() {
        pickIteratorEmpty.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextMethodIfIteratorIsEmptyAfterIterates() {
        while (pickIterator.hasNext()) {
            pickIterator.next();
        }
        pickIterator.next();
    }

    @Test
    public void testNextMethodIfIteratorIsNotEmpty() {
        assertEquals("A", pickIterator.next());
    }

    @Test(expected = IteratorIsEmptyException.class)
    public void testPickMethodThrowIteratorIsEmptyExceptionIfIteratorEmptyFromBeginning() throws IteratorIsEmptyException {
        pickIteratorEmpty.pick();
    }

    @Test(expected = IteratorIsEmptyException.class)
    public void testPickMethodThrowIteratorIsEmptyExceptionIfIteratorEmptyAfterIterates() throws IteratorIsEmptyException {
        while (pickIterator.hasNext()) {
            pickIterator.next();
        }
        pickIterator.pick();
    }

    @Test
    public void testPickMethodReturnFirstElementIfNextMethodIsNotInvoked() throws IteratorIsEmptyException {
        assertEquals("A", pickIterator.pick());
    }

    @Test
    public void testPickMethodNotMovedPointerOfIterator() throws IteratorIsEmptyException {
        assertEquals("A", pickIterator.pick());
        assertEquals("A", pickIterator.pick());
        assertEquals("A", pickIterator.next());
    }

    @Test(expected = NotImplementedException.class)
    public void testRemoveMethodThrowNotImplementedException() {
        pickIterator.remove();
    }

}

