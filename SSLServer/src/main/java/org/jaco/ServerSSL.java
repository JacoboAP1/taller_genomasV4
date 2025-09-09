package org.jaco;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
import org.jaco.CommunicationProtocol.Protocol;


public class ServerSSL {
    public void start() {
        try {
            String keystorePath = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/certs/keyserver.p12";
            char[] password = "123456".toCharArray();

            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(keystorePath), password);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), null, null);

            SSLServerSocketFactory ssf = sc.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(8443);

            System.out.println("Servidor SSL escuchando en puerto 8443");

            while (true) {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                        // Lógica delegada a Protocol
                        Protocol protocol = new Protocol();
                        protocol.handleRequest(in, out);

                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
