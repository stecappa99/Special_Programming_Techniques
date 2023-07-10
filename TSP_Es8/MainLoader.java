import java.util.ArrayList;

public class MainLoader {

    public static void main(String[] args) {
        String tString = "Ciao mondo!";

        StringBuilder tBuilder = new StringBuilder(tString);
        tBuilder.append(4);
        System.out.println(tBuilder);

        ArrayList<String> tStrings = new ArrayList<>();
        tStrings.add("Ciao");
        tStrings.add("mondo");
        tStrings.add("!");

        java.lang.Double tDouble = 2.0;
        java.lang.Thread tThread = new Thread();
        java.lang.Void tVoid;
        java.lang.Integer tInteger = 5;

        tDouble += 4;
        System.out.println("Classes loaded = " + MyClassLoader.getCounter());

    }
}
