public class MyNode<T> {
    private T item;
    private MyNode<T> left;
    private MyNode<T> right;
    private MyNode<T> parent;

    //constructor
    public MyNode(T item){
        this.item = item;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    //getter
    public T getItem() {
        return item;
    }
    public MyNode<T> getLeft() {
        return left;
    }
    public MyNode<T> getRight() {
        return right;
    }
    public MyNode<T> getParent() {
        return parent;
    }
    //setter
    public void setLeft(MyNode<T> left) {
        this.left = left;
    }
    public void setRight(MyNode<T> right) {
        this.right = right;
    }
    public void setParent(MyNode<T> parent) {
        this.parent = parent;
    }
}
