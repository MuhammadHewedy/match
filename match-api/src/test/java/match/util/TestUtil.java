package match.util;

import java.io.IOException;

import com.google.gson.Gson;

/**
 * Utility class for testing REST controllers.
 */
public class TestUtil {
	
	private static final Gson gson = new Gson();

    /**
     * Convert an object to JSON byte array.
     *
     * @param object
     *            the object to convert
     * @return the JSON byte array
     * @throws IOException
     */
	// I use gson here and not Jackson, as I use jackson for the src/main/java, and then I use some jackson annotation to skip some fields,
	// but in testing I don't want to create a complete object as the user will send from the api console
    public static byte[] toJson(Object object)
            throws IOException {
       String json = gson.toJson(object);
       return json.getBytes("utf8");
    }

    /**
     * Create a byte array with a specific size filled with specified data.
     *
     * @param size the size of the byte array
     * @param data the data to put in the byte array
     * @return the JSON byte array
     */
    public static byte[] createByteArray(int size, String data) {
        byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            byteArray[i] = Byte.parseByte(data, 2);
        }
        return byteArray;
    }
}
