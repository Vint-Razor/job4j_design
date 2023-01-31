package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;
    InnerObject innerObject;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        final TestObject testObject = (TestObject) super.clone();
        testObject.innerObject = innerObject.clone();
        return testObject;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject testObject1 = new TestObject();
        testObject1.num = 5;
        InnerObject innerObject = new InnerObject();
        innerObject.num = 15;
        testObject1.innerObject = innerObject;
        TestObject testObject2 = testObject1.clone();
        testObject2.num = 25;
        testObject2.innerObject.num = 35;
        System.out.println("Исходный класс: " + testObject1.num);
        System.out.println("Исходный класс: " + testObject1.innerObject.num);
        System.out.println("Скопированный класс: " + testObject2.num);
        System.out.println("Скопированный класс: " + testObject2.innerObject.num);
    }
}
