public class TestClass {

    public static void main(String[] parArgs){
        TestingFieldsBytecode tTestClass = new TestingFieldsBytecode(12, 9.2);

        tTestClass.setAnswer(321);

        System.out.println(tTestClass.message());
    }

}
