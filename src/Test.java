class A {
    private String str = "default value";
    A(){}
    A(String str) {
        this.str = str;
    }
    public void setStr(String str){
        this.str = str;
    }
    public String toString(){
        return str;
    }
}
public class Test {
    private A a = new A();
    private A b = new A("new B");
    public A getA(){
        return a;
    }
    public A getB() { return b; }
    public void changeA(A b){
        System.out.println(b.hashCode());
        System.out.println(a.hashCode());
        b.setStr("new value");
    }
    public void print(){
        System.out.println(a);
        System.out.println(b);
    }
    public void swap(A a, A b){
        A temp = a;
        a = b;
        b = temp;
        b.setStr("nw");
    }
    public static void main(String[] args){
        Test test = new Test();
//        System.out.println(test.getA());
//        test.changeA(test.getA());
//        System.out.println(test.getA());
//        System.out.println(test.getClass().getName());
        test.print();
        test.swap(test.getA(), test.getB());
        test.print();
    }
}
