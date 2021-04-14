import com.raylabz.javahttp.*;

import java.util.List;

public class HTTPTest {

    public static void main(String[] args) {

//        Example 1:
        BasicHTTPRequest.create("http://google.com", RequestMethod.GET) //Specify the URL and request method
                .build() //Build the request
                .sendAndWait(); //Send the request and wait for a response

//        //Example 2:
        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .addParam("intParam", 1) //Add an integer parameter
                .addParam("doubleParam", 3.14) //Add a double parameter
                .addParam("floatParam", 3.14f) //Add a float parameter
                .addParam("stringParam", "hello") //Add a String parameter
                .addParam("charParam", 'c') //Add a character parameter
                .addParam("booleanParam", true) //Add a boolean parameter
                .addParam("longParam", System.currentTimeMillis()) //Add a long parameter
                .addParam("shortParam", (short) 123) //Add a short parameter
                .build()
                .sendAndWait();

        //Example 3:
        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .onSuccess(response -> {
                    System.out.println("Response received successfully"); //Print a message
                    int status = response.getStatus(); //Get the status
                    String body = response.getContent(); //Get the content
                    List<String> keepAlive = response.getHeaderField("Keep-Alive"); //Get meta-data by name (headers)
                })
                .build()
                .sendAndWait();

        //Example 4:
        BasicHTTPRequest.create("http://google.com", RequestMethod.POST)
                .build()
                .sendAndWait();

        //Example 4:
        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .setRequestProperty("User-Agent", "Java") //Set the User-Agent header
                .build()
                .sendAndWait();

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .setContentType(ContentType.CONTENT_TYPE_JSON)
                .build()
                .sendAndWait();

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .setContentType("text/*")
                .build()
                .sendAndWait();

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .setConnectTimeout(20000) //Set the connection timeout
                .setReadTimeout(15000) //Set the read data timeout
                .build()
                .sendAndWait();

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .onSuccess(response -> {
                    if (response.isSuccess()) {
                        //TODO - Success!
                    }
                    else {
                        //TODO - Failed to connect :(
                    }
                })
                .build()
                .sendAndWait();

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .build()
                .send(); //Send the request and keep executing other code

        BasicHTTPRequest.create("http://google.com", RequestMethod.GET)
                .onFailure(error -> {
                    error.printStackTrace();
                })
                .build()
                .sendAndWait();

        BinaryHTTPRequest.create("http://localhost:8080/binaryEndpoint")
                .build()
                .sendAndWait();

        BinaryHTTPRequest.create("http://localhost:8080/binaryEndpoint")
                .putInt(1) //Put an integer
                .putShort((short) 1) //Put a short
                .putLong(System.currentTimeMillis()) //Put a long
                .putFloat(1.3f) //Put a float
                .putDouble(3.14) //Put a double
                .putChar('c') //Put a character
                .putString("hello") //Put a string
                .putBoolean(true) //Put a boolean
                .putByte((byte) 112) //Put a byte
                .putBytes(new byte[10]) //Put an array of bytes
                .build()
                .sendAndWait();


        BinaryHTTPRequest.create("http://localhost:8080/binaryEndpoint")
                .onSuccess(response -> {
                    if (response.isSuccess()) {
                        //...
                    }
                })
                .onFailure(error -> {
                    error.printStackTrace();
                })
                .setConnectTimeout(10000)
                .setReadTimeout(15000)
                .setRequestProperty("User-Agent", "Java")
                .build()
                .sendAndWait();

        BinaryHTTPRequest.create("http://localhost:8080/binaryEndpoint")
                .onSuccess(response -> {
                    if (response.isSuccess()) {
                        int i = response.readInt();
                        long l = response.readLong();
                        short s = response.readShort();
                        float f = response.readFloat();
                        double d = response.readDouble();
                        char c = response.readChar();
                        String str = response.readString();
                        boolean b = response.readBoolean();
                        byte b1 = response.readByte();
                    }
                })
                .build()
                .sendAndWait();

        new BinaryHTTPRequest.Builder("http://localhost:8080/api/binaryTest")
                .putInt(4)
                .putInt(1993)
                .onSuccess(response -> {
                    final int length = response.readInt();
                    final int secretNumber = response.readInt();
                    System.out.println("length = " + length);
                    System.out.println("secretNumber = " + secretNumber);
                })
                .build()
                .send();


    }

}
