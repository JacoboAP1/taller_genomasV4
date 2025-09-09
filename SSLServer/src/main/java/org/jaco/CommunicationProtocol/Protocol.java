package org.jaco.CommunicationProtocol;

import org.jaco.entities.Patient;
import org.jaco.persistence.PatientRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Protocol {

    private final PatientRepository repository = new PatientRepository();

    public void handleRequest(BufferedReader in, PrintWriter out) throws IOException {
        // Usamos el parser para interpretar el mensaje
        MessageParser parser = new MessageParser();
        parser.parse(in);

        String command = parser.getCommand();

        if ("CREATE_PATIENT".equals(command)) {
            handleCreatePatient(parser, out);
        } else {
            out.println("STATUS: ERROR");
            out.println("MESSAGE: Comando no reconocido");
            out.println("END");
        }
    }

    private void handleCreatePatient(MessageParser parser, PrintWriter out) {
        try {
            // Generar ID único del paciente
            String patientId = "P" + System.currentTimeMillis();

            Patient patient = new Patient(
                    patientId,
                    parser.getField("NAME"),
                    parser.getField("DOCUMENT"),
                    Integer.parseInt(parser.getField("AGE")),
                    parser.getField("SEX"),
                    parser.getField("EMAIL"),
                    LocalDateTime.now(),
                    parser.getField("CLINICAL_NOTES"),
                    null, // checksum se genera al guardar
                    0     // tamaño se calcula al guardar
            );

            // Guardar paciente y archivo FASTA
            repository.savePatient(patient, parser.getFASTA());

            // Respuesta al cliente
            out.println("STATUS: OK");
            out.println("MESSAGE: Paciente " + patient.getPatientId() + " creado correctamente");
            out.println("END");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("STATUS: ERROR");
            out.println("MESSAGE: No se pudo crear el paciente");
            out.println("END");
        }
    }
}
