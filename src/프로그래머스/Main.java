package 프로그래머스;

public class Main {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        a = b = c;
        a.method();
    }

    public static class A {
        public void method() {
            System.out.println("method");
        }
    }

    public static class B extends A {
        @Override
        public void method() {
            System.out.println("goodbye");
        }
    }

    public static class C extends B {
        @Override
        public void method() {
            System.out.println("byebye");
        }
    }
}
