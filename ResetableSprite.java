import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ResourceBundle;


public class ResetableSprite
{
    public static boolean makeOriginal;
    Object myOriginal;
    

    public ResetableSprite (Object ... o)
    {

        if (makeOriginal == true)
        {
            makeOriginal = false;

            Constructor[] c = this.getClass().getConstructors();

            try
            {

                for (Constructor constructor : c)
                {
                    if (constructor.getModifiers() == Modifier.PUBLIC)
                    {
                        if (o.length == constructor.getParameterTypes().length)
                        {
                            myOriginal = constructor.newInstance(o);
                            break;
                        }
                    }

                }
            }
            catch (Exception e)
            {}

        }
        else
        {
            makeOriginal = true;
        }
    }


    public void reset ()
    {

        Field[] myFields = this.getClass().getDeclaredFields();
        Field[] myOriginalFields = myOriginal.getClass().getDeclaredFields();
        for (int i = 0; i < myOriginalFields.length; i++)
        {
            try
            {

                myFields[i].setAccessible(true);
                myOriginalFields[i].setAccessible(true);
                myFields[i].set(this, myOriginalFields[i].get(myOriginal));

            }
            catch (Exception e)
            {}
        }
    }
}
