package org.example.bmicalculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class ResourceBundleTest {

    ResourceBundle rb;
    Locale locale;


    @Test
    void englishTest(){
        locale = new Locale("en","US");
        rb = ResourceBundle.getBundle("MessagesBundle",locale);
        assertEquals("English", rb.getString("button1.text"));

    }

    @Test
    void frenchTest(){
        locale = new Locale("fr","FR");
        rb = ResourceBundle.getBundle("MessagesBundle",locale);
        // check button1.text for French locale
        assertEquals("Anglais", rb.getString("button1.text"));
    }

    @Test
    void urduTest(){
        locale = new Locale("ur","PK");
        rb = ResourceBundle.getBundle("MessagesBundle",locale);
        assertEquals("انگریزی", rb.getString("button1.text"));
    }

    @Test
    void vietnameseTest(){
        locale = new Locale("vi","VN");
        rb = ResourceBundle.getBundle("MessagesBundle",locale);
        assertEquals("Tiếng Anh", rb.getString("button1.text"));
    }


  
}