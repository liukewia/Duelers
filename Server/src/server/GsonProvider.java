package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GsonProvider {

    public static Gson provide() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();
    }

    static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            String date = element.getAsString();

            // Try 12 hour
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a", Locale.ENGLISH);
                Date result = formatter.parse(date);
                return result;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Try 24 hour
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss");
                Date result = formatter.parse(date);
                return result;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
