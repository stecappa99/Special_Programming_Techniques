import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.*;

public class RecognizeElements {

    public static Optional<Class> createClass(String parClassName)
    {
        try {
            return Optional.of(Class.forName(parClassName));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public static Optional<Member> existInClass(Member[] parMembers, String parMember)
    {
        return Arrays.stream(parMembers).filter(m -> m.getName().equals(parMember)).findFirst();
    }

    public static boolean declaredAll(Map.Entry<Class, Member[]> parEntry, String[] parMethodNames)
    {
        return parEntry.getValue().length == parMethodNames.length;
    }

    public static void main(String[] args) {
        String[] tClassNames;
        String[] tMembers;
        Class[] tClasses;
        tClassNames = new String[]{ "java.lang.Integer", "java.lang.String", "pippo" };
        tMembers = new String[]{ "toString", "equals", "length", "strip" };


        tClasses = Arrays.stream(tClassNames)
                            .map(RecognizeElements::createClass)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .toArray(Class[]::new);


        Map.Entry[] tEntries = Arrays.stream(tClasses)
                .map(c -> Map.entry(c,
                                Arrays.stream(tMembers)
                                            .map(s ->
                                            {
                                                Optional<Member> tMember;
                                                tMember = existInClass(c.getMethods(), s);
                                                if (!tMember.isPresent())
                                                    tMember = existInClass(c.getFields(), s);
                                                return tMember;
                                            })
                                        .filter(Optional::isPresent)
                                        .map(Optional::get)
                                        .toArray(Member[]::new)
                                            ))
                .toArray(Map.Entry[]::new);

        Map<Class, Member[]> tMap = Map.ofEntries(tEntries);



        System.out.println(tMap.entrySet().stream().anyMatch(e -> declaredAll(e, tMembers)));
        tMap.forEach((x, y)->
        {
            for (Member i : y)
            {
                System.out.println(i.getName() + " trovato nella classe " + x.toString());
                System.out.println(i.getName() + " è un " + (i instanceof Method ? "Metodo" : "Campo"));
                System.out.println(i);
            }
        });


     }
}
