package binarytree.model;

public class StringTree extends GenericTree<String>{

    @Override
    public boolean valueGreaterThanNode(String nodeData, String value) {
        return nodeData.compareTo(value) < 0 ;
    }

    @Override
    public boolean valueEqualNode(String nodeData, String value) {
        return nodeData.equals(value);
    }

    @Override
    public boolean valueLessThanNode(String nodeData, String value) {
        return nodeData.compareTo(value) > 0;
    }


}
