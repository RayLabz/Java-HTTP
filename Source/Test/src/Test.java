import com.panickapps.javahttp.HTTPRequest;
import com.panickapps.javahttp.HTTPResponse;
import com.panickapps.javahttp.MimeType;
import com.panickapps.javahttp.RequestMethod;

public class Test {

    public static void main(String[] args) {
        HTTPResponse response = new HTTPRequest.Builder("https://minesweeper-246410.appspot.com/game/list", RequestMethod.GET)
                .setMimeType(MimeType.CONTENT_TYPE_JSON)
                .build()
                .call();

        System.out.println(response.getContent());

        //TODO - Test with POST
        //TODO - Test with Parameters GET
        //TODO - Test with Parameters POST

    }

}
