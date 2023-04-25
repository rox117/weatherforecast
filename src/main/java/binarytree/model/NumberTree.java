package binarytree.model;

public class NumberTree extends GenericTree<Integer>{

    @Override
    public boolean valueGreaterThanNode(Integer nodeData, Integer value) {
        return value > nodeData;
    }

    @Override
    public boolean valueEqualNode(Integer nodeData, Integer value) {
        return value == nodeData;
    }

    @Override
    public boolean valueLessThanNode(Integer nodeData, Integer value) {
        return value < nodeData;
    }
}