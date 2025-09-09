package org.jaco.CommunicationProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Protocol {
    public void handleRequest(BufferedReader in, PrintWriter out) throws IOException {
        // Usamos el parser para interpretar el mensaje
        MessageParser parser = new MessageParser();
        parser.parse(in);

        String command = parser.getCommand();

        if ("CREATE_PATIENT".equals(command)) {
            System.out.println("Recibido CREATE_PATIENT");
            System.out.println("Paciente: " + parser.getField("NAME"));
            System.out.println("FASTA:\n" + parser.getFASTA());

            out.println("STATUS: OK");
            out.println("MESSAGE: Paciente " + parser.getField("ID") + " creado correctamente");
            out.println("END");
        } else {
            out.println("STATUS: ERROR");
            out.println("MESSAGE: Comando no reconocido");
            out.println("END");
        }
    }
}
