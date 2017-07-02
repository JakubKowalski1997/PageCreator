import java.util.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */

//Enumeration list of attributes used to have easy access to Map of Form's data
enum HeadAttrib{
    NAME,
    SURNAME,
    TITLE,
    KEYWORDS,
    DESCRIPTION,
    CHARSET
}

public class Form {

    //Map for all the Form's data
    private Map<HeadAttrib, String> formData;

    //constructor
    public Form(/*FormWindow with textfields will be argument*/)
    {
        //init Map
        formData = new HashMap<>();

        //simple fast test if everything is correnct
        formData.put(HeadAttrib.NAME, "Wiktor");
        System.out.println(formData.get(HeadAttrib.NAME));
    }

    //map getter
    Map<HeadAttrib, String> returnFormData() {return formData;}
}
