package server.clientPortal.models;

import com.google.gson.Gson;
import java.io.BufferedReader;
import server.GsonProvider;

public class JsonConverter {
    public static <T> String toJson(T object) {
        return new Gson().toJson(object)
                .replaceAll("\"\\w+\":(0|false),?", "")
                .replaceAll(",}", "}")
                .replaceAll(",]", "]");
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> T fromJson(BufferedReader reader, Class<T> classOfT) {
        return GsonProvider.provide().fromJson(reader, classOfT);
    }
}