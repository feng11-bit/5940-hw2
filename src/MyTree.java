public class MyTree<T extends Comparable<T>> {
    private MyNode<T> root;
    private int size;

    public MyTree() {
        this.root = null;
        this.size = 0;
    }

    //inset method
    public MyNode insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        //if tree is empty
        if (root == null) {
            root = new MyNode<>(item);
            size++;
            return root;
        }
        MyNode<T> node = root;
        MyNode<T> parent = null;
        while (node != null) {
            if (item.equals(node.getItem())) {
                return node; //if already present
            }
            parent = node;
            int compare = item.compareTo(node.getItem());
            if (compare < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        //inset new node
        MyNode<T> newNode = new MyNode<>(item);
        if (item.compareTo(parent.getItem()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        newNode.setParent(parent);
        size++;
        return newNode;
    }

    //contain method
    public MyNode contains(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        MyNode<T> node = root;
        while (node != null) {
            if (item.equals(node.getItem())) {
                return node;
            }
            int compare = item.compareTo(node.getItem());
            if (compare < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    public boolean remove(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        MyNode<T> node = root;
        // find item in the tree and delete
        while (node != null) {
            if (item.equals(node.getItem())) {
                break;//find the item
            }
            int compare = item.compareTo(node.getItem());
            if (compare < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        // if didn't find the item
        if (node == null) {
            return false;
        }
        // node has no left child
        if (node.getLeft() == null && node.getRight() == null) {
            MyNode<T> newNode = node.getRight();
            MyNode<T> parent = node.getParent();
            if (parent == null) {
                root = newNode;
            } else if (node == parent.getLeft()) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
            if (newNode != null) {
                newNode.setParent(parent);
            }
            size--;
            return true;
        }
        //no right child
        if (node.getRight() == null) {
            MyNode<T> newNode = node.getLeft();
            MyNode<T> parent = node.getParent();
            if (parent == null) {
                root = newNode;
            } else if (node == parent.getLeft()) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
            if (newNode != null) {
                newNode.setParent(parent);
            }
            size--;
            return true;
        }
        //two children
        MyNode<T> newNode = node.getRight();
        while (newNode.getLeft() != null) {
            newNode = newNode.getLeft();
        }
        if (newNode.getParent() != node) {

            MyNode<T> parent = newNode.getParent();
            MyNode<T> rightChild = newNode.getRight();

            parent.setLeft(rightChild);

            if (rightChild != null) {
                rightChild.setParent(parent);
            }

            newNode.setRight(node.getRight());
            newNode.getRight().setParent(newNode);
        }

        MyNode<T> parent = node.getParent();

        if (parent == null) {
            root = newNode;
        } else if (node == parent.getLeft()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        newNode.setParent(parent);

        newNode.setLeft(node.getLeft());
        newNode.getLeft().setParent(newNode);

        size--;
        return true;


    }

    public String toString() {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // in order traversal
        MyNode<T> node = root;
        while (node != null) {
            if (node.getLeft() == null) {
                sb.append(node.getItem()).append(", ");
                node = node.getRight();
            } else {
                MyNode<T> newNode = node.getLeft();
                while (newNode.getRight() != null && newNode.getRight() != node) {
                    newNode = newNode.getRight();
                }

                if (newNode.getRight() == null) {
                    newNode.setRight(node);
                    node = node.getLeft();
                } else {
                    newNode.setRight(null);
                    sb.append(node.getItem()).append(", ");
                    node = node.getRight();
                }
            }
        }

        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public MyNode getRoot() {
        return root;
    }
}

