import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;

        //deafault port number
        int portnumber = 1234;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);

        }

        //Create server slide socket
        try {
            server = new ServerSocket(portnumber);

        } catch (IOException ie) {
            System.out.println("Cannot open socket " + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket created: " + server);

        //waiting for client data to reply
        while (true) {
            try {
                //listen for conection
                System.out.println("waiting for connection request...");
                client = server.accept();

                System.out.println("Connect request has been accepted.");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

                //read client data
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                String msgFromClient = br.readLine();
                System.out.println("Message resieved from client = " + msgFromClient);

                String type = br.readLine();
                System.out.println("Message resieved from client = " + type);

                //Taking in new data
                String msgFromClient2 = br.readLine();
                System.out.println("Message resieved from client = " + msgFromClient2);

                //Send respond to client
                if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye")) {
                    OutputStream clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    if(type.equalsIgnoreCase("+")) {
                        double ansMsg = Double.parseDouble(msgFromClient) + Double.parseDouble(msgFromClient2);
                        pw.println(ansMsg);
                    }
                    else if(type.equalsIgnoreCase("-")) {
                        double ansMsg = Double.parseDouble(msgFromClient) - Double.parseDouble(msgFromClient2);
                        pw.println(ansMsg);
                    }
                    else if(type.equalsIgnoreCase("/")) {
                        double ansMsg = Double.parseDouble(msgFromClient) / Double.parseDouble(msgFromClient2);
                        pw.println(ansMsg);
                    }
                    else if(type.equalsIgnoreCase("*")) {
                        double ansMsg = Double.parseDouble(msgFromClient) * Double.parseDouble(msgFromClient2);
                        pw.println(ansMsg);
                    }
                    else{
                        pw.println("sorry I did not understand the input.");
                    }


                }
                //CLose socket
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    server.close();

                    client.close();
                    break;
                }

            } catch (IOException ie) {
                System.out.println("lämpligt error meddelande");
            }
        }

    }

}
