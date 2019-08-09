import com.panickapps.javahttp.HTTPRequest;
import com.panickapps.javahttp.HTTPResponse;
import com.panickapps.javahttp.MimeType;
import com.panickapps.javahttp.RequestMethod;


public class Test {

    public static void main(String[] args) {

        HTTPResponse getResponse = new HTTPRequest.Builder("https://minesweeper-246410.appspot.com/PostTest", RequestMethod.POST)
                .addParam("name", "Nicos")
                .addRequestProperty("User-Agent", "Java")
                .setMimeType(MimeType.CONTENT_TYPE_URL_ENCODED)
                .build()
                .call();

        System.out.println(getResponse.getContent());

//        HTTPResponse postResponse = new HTTPRequest.Builder("https://minesweeper-246410.appspot.com/PostTest", RequestMethod.POST)
//                .addParam("name", "Nicos")
//                .addRequestProperty("User-Agent", "Java")
//                .build()
//                .call();

    }

}
