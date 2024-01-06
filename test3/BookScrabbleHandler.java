package test3;

import java.io.*;
import java.util.Arrays;

public class BookScrabbleHandler implements ClientHandler {
    private DictionaryManager dictionaryManager;
    // private volatile boolean stop = false;

    BookScrabbleHandler() {
        this.dictionaryManager = new DictionaryManager();
    }

    @Override
    public void close() {
        // this.stop = true;
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {
            BufferedReader inputFromclient = new BufferedReader(new InputStreamReader(inFromclient));
            PrintWriter outputToClient = new PrintWriter(outToClient);

            String[] textInput = inputFromclient.readLine().split(",");
            String[] data = Arrays.copyOfRange(textInput, 1, textInput.length);
            boolean result = false;

            if (textInput[0].equals("Q")) {
                result = this.dictionaryManager.query(data);
            }

            else if (textInput[0].equals("C")) {
                result = this.dictionaryManager.challenge(data);
            }

            if (result == true) {
                outputToClient.println("true");
            } else {
                outputToClient.println("false");
            }

            outputToClient.flush();

            // Close all the memory..
            inputFromclient.close();
            inFromclient.close();
            outputToClient.close();
            outToClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
