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

public class FormModel {

    //Map for all the Form's data
    private Map<HeadAttrib, String> formData;

    //constructor
    public FormModel(ArrayList<String> data)
    {
        //init Map
        formData = new HashMap<>();

        //fill map
        formData.put(HeadAttrib.NAME, data.get(0));
        formData.put(HeadAttrib.SURNAME, data.get(1));
        formData.put(HeadAttrib.TITLE, data.get(2));
        formData.put(HeadAttrib.KEYWORDS, data.get(3));
        formData.put(HeadAttrib.DESCRIPTION, data.get(4));
        formData.put(HeadAttrib.CHARSET, data.get(5));
    }

    //map getter
    Map<HeadAttrib, String> returnFormData() {return formData;}
}
