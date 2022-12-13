package org.dbp.lecture.midterm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args)  throws Exception{
        String sentence;
        String modifiedSentence;

        //Create input Stream
        BufferedReader  inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // Create client socket, connect to server
        Socket clientSocket = new Socket("localhost", 6789);
        // Create output stream attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();

        outToServer.writeBytes(sentence + '\n');

        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();

    }
}
