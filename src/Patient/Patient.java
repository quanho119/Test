package Patient;

import java.time.LocalDate;

public abstract class Patient {
    private int id;
    private String medicalRecordID;
    private String nameID;
    private String name;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String reason;
    public Patient() {}
    public Patient(int id, String medicalRecordID, String nameID, String name, LocalDate dateIn, LocalDate dateOut, String reason) {
        this.id = id;
        this.medicalRecordID = medicalRecordID;
        this.nameID=nameID;
        this.name = name;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.reason = reason;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicalRecordID() {
        return medicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public abstract String toString();
    public abstract String getInfo();
}
