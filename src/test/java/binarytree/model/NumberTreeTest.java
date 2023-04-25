package binarytree.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberTreeTest {

    private NumberTree populatedNumberTree;

    @BeforeEach
    public void setupTree(){
        populatedNumberTree = createNumberTreeWithNodes();
    }

    public NumberTree createNumberTreeWithNodes(){
        NumberTree numberTree = new NumberTree();
        numberTree.insert(6);
        numberTree.insert(4);
        numberTree.insert(8);
        numberTree.insert(3);
        numberTree.insert(5);
        numberTree.insert(7);
        numberTree.insert(9);
        return numberTree;
    }


    @Test
    public void givenPopulatedTree_whenCheckedIfEmpty_thenReturnFalse() {
        assertFalse(populatedNumberTree.isEmpty());
    }

    @Test
    public void givenPopulatedTree_whenCheckedForElements_thenReturnTrue(){
        assertTrue(populatedNumberTree.contains(6));
        assertTrue(populatedNumberTree.contains(4));
        assertTrue(populatedNumberTree.contains(8));
    }

    @Test
    public void givenPopulatedTree_whenNewElementsInserted_thenCheckIfNodeCountSame(){
        int expectedTreeSize = populatedNumberTree.count() + 2;

        populatedNumberTree.insert(11);
        populatedNumberTree.insert(12);

        assertEquals(expectedTreeSize, populatedNumberTree.count());
    }

    @Test
    public void givenEmptyTree_whenCheckedIfEmpty_thenReturnTrue(){
        populatedNumberTree = (NumberTree) populatedNumberTree.clearTree();

        assertTrue(populatedNumberTree.isEmpty());
    }

    @Test
    public void givenEmptyTree_whenInsertingNewElement_thenCheckIfTreeContainsInsertedElement() {
        populatedNumberTree = (NumberTree) populatedNumberTree.clearTree();

        populatedNumberTree.insert(12);
        populatedNumberTree.insert(13);
        populatedNumberTree.insert(14);

        assertTrue(populatedNumberTree.contains(12));
        assertTrue(populatedNumberTree.contains(13));
        assertTrue(populatedNumberTree.contains(14));
    }

    @Test
    public void givenPopulatedTree_whenDuplicateElementInserted_thenCheckIfNodeCountSame() {
        int numberNodeCount = populatedNumberTree.count();

        populatedNumberTree.insert(3);

        assertTrue(populatedNumberTree.contains(3));
        assertEquals(numberNodeCount, populatedNumberTree.count());
    }

    @Test
    public void givenPopulatedTree_whenElementRemoved_thenCheckIfElementInTree() {
        assertTrue(populatedNumberTree.contains(6));

        populatedNumberTree.remove(6);

        assertFalse(populatedNumberTree.contains(6));
    }

    @Test
    public void givenPopulatedTree_whenRemoveElementNotInTree_thenCheckIfNodeCountSame() {
        int numberNodeCount = populatedNumberTree.count();
        assertFalse(populatedNumberTree.contains(11));

        populatedNumberTree.remove(11);
        assertFalse(populatedNumberTree.contains(11));
        assertEquals(numberNodeCount, populatedNumberTree.count());
    }
}
