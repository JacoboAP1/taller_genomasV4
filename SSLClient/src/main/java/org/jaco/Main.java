package org.jaco;

import org.jaco.connection.ClientSSL;

public class Main {
    public static void main(String[] args) throws Exception {
        ClientSSL client = new ClientSSL();
        client.start();
    }
}