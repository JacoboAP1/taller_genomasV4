package org.jaco;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class ServerSSL {
    public void start() throws Exception {
        String keystorePath = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller_genomasV4/certs/keyserver.p12";
        char[] password = "123456".toCharArray();

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(keystorePath), password);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory ssf = sc.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(8443);

        System.out.println("Servidor SSL escuchando en puerto 8443...");

        while (true) {
            try (SSLSocket socket = (SSLSocket) serverSocket.accept()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String msg = in.readLine();
                System.out.println("Cliente dice: " + msg);

                out.println("Hola cliente, soy el servidor SSL");
            }
        }
    }
}
