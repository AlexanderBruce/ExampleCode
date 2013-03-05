import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectionWork
{
    @CoolTest(testDescription = "testA")
    public void testA ()
    {
        System.out.println("A");

    }


    @CoolTest(testDescription = "testB")
    public void testB ()
    {
        System.out.println("B");
    }


    @CoolTest(testDescription = "stestC")
    public void stestC ()
    {
        System.out.println("C");
    }


    public void testD (String s)
    {
        if (s.equals("Reflection is so cool"))
        {
            System.out.println("D");
        }
    }

    public void testE (String s)
    {
        System.out.println("F");
    }


    public void runAllTest (Object obj)
        throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException
    {
        Class c = obj.getClass();
        for (Method m : c.getMethods())
        {
            if (!m.getName().startsWith("test"))
            {
                continue;
            }
            if (m.getGenericParameterTypes().length != 1)
            {
                continue;
            }
            if (!m.getGenericParameterTypes()[0].equals(String.class))
            {
                continue;
            }
            m.invoke(obj, "Reflection is so cool");
        }
    }


    public void runAllAnnotatedTests ()
        throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException
    {
        Class c = getClass();

        for (Method m : c.getMethods())
        {

            if (m.getDeclaredAnnotations().length != 0)
            {
             
                System.out.println(m.getAnnotation(CoolTest.class)
                                    .testDescription());
                m.invoke(this);
            }

        }

    }


    public static void main (String[] args)
        throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException
    {
        ReflectionWork rw = new ReflectionWork();
        //rw.runAllTest(rw);
        rw.runAllAnnotatedTests();
    }

}
