import com.panickapps.javahttp.HTTPRequest;
import com.panickapps.javahttp.HTTPResponse;
import com.panickapps.javahttp.MimeType;
import com.panickapps.javahttp.RequestMethod;

public class Example {

    public static void main(String[] args) {

        //Perform a GET HTTP request:
        HTTPResponse response = new HTTPRequest.Builder("http://www.example.com/api/endpoint", RequestMethod.GET)
                .addParam("myParam", "myValue") //Add parameter(s)
                .setMimeType(MimeType.CONTENT_TYPE_JSON) //Set the Mime type (Content-Type)
                //.setCustomMimeType("text/*") //Allows you to set a custom text-based Mime Type
                .setRequestProperty("User-Agent", "Java") //Set a request property/header
                .setConnectTimeout(20000) //Set the connection timeout
                .setReadTimeout(15000) //Set the data read timeout
                .build() //Build the request
                .call(); //Make the call

        //Get the response data:
        System.out.println("Response code: " + response.getStatus()); //Fetch the response code/status
        System.out.println("Response content: " + response.getContent()); //Fetch the response content (body)
        System.out.print("Response header field 'Content-Type': " + response.getHeaderField("Content-Type").get(0));

        //Perform a POST HTTP request:
        HTTPResponse response2 = new HTTPRequest.Builder("http://www.example.com/api/endpoint", RequestMethod.POST)
                .addParam("myParam", "myValue") //Add parameter(s)
                .build() //Build the request
                .call(); //Make the call


    }

}
