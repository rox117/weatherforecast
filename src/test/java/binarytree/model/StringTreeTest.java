package binarytree.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StringTreeTest {

    private StringTree populatedNumberTree;

    @BeforeEach
    public void setupTree(){
        populatedNumberTree = createStringTreeWithNodes();
    }

    public StringTree createStringTreeWithNodes(){
        StringTree stringTree = new StringTree();
        stringTree.insert("m");
        stringTree.insert("c");
        stringTree.insert("d");
        stringTree.insert("o");
        stringTree.insert("p");
        stringTree.insert("e");
        stringTree.insert("q");
        return stringTree;
    }


    @Test
    public void givenPopulatedTree_whenCheckedIfEmpty_thenReturnFalse() {
        assertFalse(populatedNumberTree.isEmpty());
    }

    @Test
    public void givenPopulatedTree_whenCheckedForElements_thenReturnTrue(){
        assertTrue(populatedNumberTree.contains("m"));
        assertTrue(populatedNumberTree.contains("c"));
        assertTrue(populatedNumberTree.contains("d"));
    }

    @Test
    public void givenPopulatedTree_whenNewElementsInserted_thenCheckIfNodeCountSame(){
        int expectedTreeSize = populatedNumberTree.count() + 2;

        populatedNumberTree.insert("a");
        populatedNumberTree.insert("b");

        assertEquals(expectedTreeSize, populatedNumberTree.count());
    }

    @Test
    public void givenEmptyTree_whenCheckedIfEmpty_thenReturnTrue(){
        populatedNumberTree = (StringTree) populatedNumberTree.clearTree();

        assertTrue(populatedNumberTree.isEmpty());
    }

    @Test
    public void givenEmptyTree_whenInsertingNewElement_thenCheckIfTreeContainsInsertedElement() {
        populatedNumberTree = (StringTree) populatedNumberTree.clearTree();

        populatedNumberTree.insert("a");
        populatedNumberTree.insert("b");
        populatedNumberTree.insert("f");

        assertTrue(populatedNumberTree.contains("a"));
        assertTrue(populatedNumberTree.contains("b"));
        assertTrue(populatedNumberTree.contains("f"));
    }

    @Test
    public void givenPopulatedTree_whenDuplicateElementInserted_thenCheckIfNodeCountSame() {
        int numberNodeCount = populatedNumberTree.count();

        populatedNumberTree.insert("m");

        assertTrue(populatedNumberTree.contains("m"));
        assertEquals(numberNodeCount, populatedNumberTree.count());
    }

    @Test
    public void givenPopulatedTree_whenElementRemoved_thenCheckIfElementInTree() {
        assertTrue(populatedNumberTree.contains("m"));

        populatedNumberTree.remove("m");

        assertFalse(populatedNumberTree.contains("m"));
    }

    @Test
    public void givenPopulatedTree_whenRemoveElementNotInTree_thenCheckIfNodeCountSame() {
        int numberNodeCount = populatedNumberTree.count();
        assertFalse(populatedNumberTree.contains("a"));

        populatedNumberTree.remove("a");
        assertFalse(populatedNumberTree.contains("a"));
        assertEquals(numberNodeCount, populatedNumberTree.count());
    }
}
