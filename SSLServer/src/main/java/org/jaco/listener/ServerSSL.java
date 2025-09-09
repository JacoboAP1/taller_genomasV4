package org.jaco.listener;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
import org.jaco.CommunicationProtocol.Protocol;
import org.jaco.configserver.Configuration;

public class ServerSSL {
    public void start() {
        try {
            // Leer configuración
            String keystorePath = Configuration.get("keystore.path");
            char[] password = Configuration.get("keystore.password").toCharArray();
            int port = Configuration.getInt("server.port");

            // Configurar SSL con el keystore
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(keystorePath), password);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), null, null);

            SSLServerSocketFactory ssf = sc.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(port);

            System.out.println("Servidor SSL escuchando en puerto " + port);

            while (true) {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                        Protocol protocol = new Protocol();
                        protocol.handleRequest(in, out);

                        out.close();
                        in.close();
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
