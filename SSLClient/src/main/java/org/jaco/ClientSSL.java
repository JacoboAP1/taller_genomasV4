package org.jaco;

import javax.net.ssl.*;
import java.io.*;

public class ClientSSL {
    public void start() throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller_genomasV4/certs/keyserver.p12");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 8443)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hola servidor SSL");

            String response = in.readLine();
            System.out.println("Servidor respondió: " + response);
        }
    }
}
