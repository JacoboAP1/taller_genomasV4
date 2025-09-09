package org.jaco;

import org.jaco.connection.ClientSSL;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        ClientSSL client = new ClientSSL();
        client.start();
    }
}