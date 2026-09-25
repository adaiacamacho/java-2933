package bibliotecas.json;

import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import bibliotecas.json.JsonHelper;

public class GsonJsonHelper implements JsonHelper {
	// @formatter:off
	private static final JsonSerializer<LocalDate> SER_LOCAL_DATE = 
			(src, type, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));

	private static final JsonDeserializer<LocalDate> DESER_LOCAL_DATE = 
			(json, type, ctx) -> LocalDate.parse(json.getAsString());

	private static final JsonSerializer<LocalDateTime> SER_LOCAL_DATE_TIME = 
			(src, type, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

	private static final JsonDeserializer<LocalDateTime> DESER_LOCAL_DATE_TIME = 
			(json, type, ctx) -> LocalDateTime.parse(json.getAsString());
	
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, SER_LOCAL_DATE)
			.registerTypeAdapter(LocalDate.class, DESER_LOCAL_DATE)
			.registerTypeAdapter(LocalDateTime.class, SER_LOCAL_DATE_TIME)
			.registerTypeAdapter(LocalDateTime.class, DESER_LOCAL_DATE_TIME)
		.create();
	// @formatter:on

	@Override
	public String objetoAJson(Object o) {
		return GSON.toJson(o);
	}

	@Override
	public <T> T objetoDesdeJson(String json, Class<T> clase) {
		return GSON.fromJson(json, clase);
	}

	@Override
	public <T> T objetoDesdeJson(Reader reader, Class<T> clase) {
		return GSON.fromJson(reader, clase);
	}

}
