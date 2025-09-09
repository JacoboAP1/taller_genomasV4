package org.jaco;

import org.jaco.listener.ServerSSL;

public class Main {
    public static void main(String[] args) throws  Exception {
        ServerSSL server = new ServerSSL();
        server.start();
    }
}