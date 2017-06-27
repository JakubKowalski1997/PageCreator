import java.util.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */
public class Form {
    //Enumeration list of attributes used to have easy access to Map of Form's data
    private enum HeadAttrib{
        NAME,
        SURNAME,
        TITLE,
        KEYWORDS,
        DESCRIPTION,
        CHARSET
    }

    //Map for all the Form's data
    private Map<HeadAttrib, String> formData;

    //constructor
    public Form(/*FormWindow with textfields will be argument*/)
    {
        //init Map
        formData = new HashMap<>();

        //simple fast test if everything is correnct
        formData.put(HeadAttrib.CHARSET, "SDSD");
        System.out.println(formData);
    }
}
