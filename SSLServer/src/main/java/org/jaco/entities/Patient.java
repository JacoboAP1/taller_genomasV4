package org.jaco.entities;

import java.time.LocalDateTime;

public class Patient {

    private String patientId;        // generado por el servidor
    private String fullName;
    private String documentId;
    private int age;
    private String sex;              // "M" o "F"
    private String contactEmail;
    private LocalDateTime registrationDate;
    private String clinicalNotes;
    private String checksumFasta;    // MD5 o SHA-256
    private long fileSizeBytes;      // tamaño del archivo FASTA en bytes

    public Patient(String patientId, String fullName, String documentId, int age, String sex,
                   String contactEmail, LocalDateTime registrationDate,
                   String clinicalNotes, String checksumFasta, long fileSizeBytes) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.documentId = documentId;
        this.age = age;
        this.sex = sex;
        this.contactEmail = contactEmail;
        this.registrationDate = registrationDate;
        this.clinicalNotes = clinicalNotes;
        this.checksumFasta = checksumFasta;
        this.fileSizeBytes = fileSizeBytes;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public String getChecksumFasta() {
        return checksumFasta;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setClinicalNotes(String clinicalNotes) {
        this.clinicalNotes = clinicalNotes;
    }

    public void setChecksumFasta(String checksumFasta) {
        this.checksumFasta = checksumFasta;
    }

    public void setFileSizeBytes(long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }
}

