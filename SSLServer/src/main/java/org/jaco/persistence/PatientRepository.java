package org.jaco.persistence;

import org.jaco.entities.Patient;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;

public class PatientRepository {

    private static final String CSV_FILE = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/data/PatientsMetadata.csv";
    private static final String FASTA_DIR = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/data/PatientFasta";

    public PatientRepository() {
        // Crear directorio PatientFasta si no existe
        try {
            Files.createDirectories(Paths.get(FASTA_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar paciente en CSV y archivo FASTA
    public void savePatient(Patient patient, String fastaContent) throws Exception {
        // Guardar archivo FASTA en la carpeta PatientFasta
        String fastaFile = FASTA_DIR + File.separator + "patient" + patient.getPatientId() + ".fasta";
        Files.writeString(Paths.get(fastaFile), fastaContent);

        // Calcular checksum y tamaño
        String checksum = calculateChecksum(fastaFile);
        long fileSize = Files.size(Paths.get(fastaFile));
        patient.setChecksumFasta(checksum);
        patient.setFileSizeBytes(fileSize);

        // Guardar en CSV
        try (FileWriter fw = new FileWriter(CSV_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.printf("%s,%s,%s,%d,%s,%s,%s,%s,%s,%d%n",
                    patient.getPatientId(),
                    patient.getFullName(),
                    patient.getDocumentId(),
                    patient.getAge(),
                    patient.getSex(),
                    patient.getContactEmail(),
                    patient.getRegistrationDate(),
                    patient.getClinicalNotes(),
                    patient.getChecksumFasta(),
                    patient.getFileSizeBytes()
            );
        }
    }

    private String calculateChecksum(String filePath) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        byte[] hash = digest.digest(fileBytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
