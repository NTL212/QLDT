package Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class CustomGson {
	public Gson createGson() {
        return new GsonBuilder()
            .registerTypeAdapter(java.time.LocalDate.class, new CustomGson.LocalDateAdapter())
            .create();
    }
	
	public static class LocalDateAdapter extends TypeAdapter<LocalDate> { // Marked static
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter out, LocalDate date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                out.value(date.format(formatter));
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == null) {
                return null;
            }
            try {
                String dateStr = in.nextString();
                return LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                throw new JsonSyntaxException("Invalid date format", e);
            }
        }
    }
}
