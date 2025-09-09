package org.jaco;

import org.jaco.MessageBuilder.MessageBuilder;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;


public class ClientSSL {
    public void start() {
        try {
            // Configuración SSL
            System.setProperty("javax.net.ssl.trustStore", "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/certs/keyserver.p12");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            // Conexión al servidor
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 8443);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Crear mensaje con MessageBuilder
            MessageBuilder mb = new MessageBuilder("CREATE_PATIENT")
                    .addField("ID", "1001")
                    .addField("NAME", "Juan Perez")
                    .addField("DOCUMENT", "123456789")
                    .addField("AGE", "35")
                    .addField("SEX", "M")
                    .addField("EMAIL", "juan.perez@example.com")
                    .addFASTA(">patient1001\nACGTACGTACGTACGT");

            // Enviar mensaje
            out.print(mb.build());
            out.flush();

            // Leer respuesta del servidor
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Servidor respondió: " + line);
                if (line.equals("END")) break;
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
