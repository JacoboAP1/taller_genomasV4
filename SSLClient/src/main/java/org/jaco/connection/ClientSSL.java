package org.jaco.connection;

import org.jaco.MessageBuilder.MessageBuilder;
import org.jaco.configclient.Configuration;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class ClientSSL {
    public void start() {
        try {
            // Leer configuración
            String keystorePath = Configuration.get("keystore.path");
            String keystorePassword = Configuration.get("keystore.password");
            String serverIp = Configuration.get("server.ip");
            int serverPort = Configuration.getInt("server.port");

            // Configurar trustStore
            System.setProperty("javax.net.ssl.trustStore", keystorePath);
            System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);

            // Conexión al servidor
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(serverIp, serverPort);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Construir mensaje de prueba
            MessageBuilder mb = new MessageBuilder("CREATE_PATIENT")
                    .addField("NAME", "Juan Perez")
                    .addField("DOCUMENT_ID", "123456789")
                    .addField("AGE", "35")
                    .addField("SEX", "M")
                    .addField("EMAIL", "juan.perez@example.com")
                    .addField("NOTES", "Paciente de prueba con FASTA de ejemplo")
                    .addFASTA(">patient\nACGTACGTACGTACGT");

            out.print(mb.build());
            out.flush();

            // Leer respuesta del servidor
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Servidor respondió: " + line);
                if (line.equals("END")) break;
            }

            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
