interface IMessage
{
    int i = 0;
    void print();
//    public void f();
    default void printf()
    {
        System.out.println(i + "ssss");
    }

}

class ImessageImp implements IMessage
{
    @Override
    public void print() {
        System.out.println("hello");
    }
}

public class TestDemo
{
    public static void main(String[] args)
    {
//        IMessage im = new ImessageImp();
//        im.print();
//        im.printf();
        System.out.println("hello world 2");
    }
    static {
        System.out.println("hello world 1");
    }
}
