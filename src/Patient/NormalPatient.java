package Patient;

import java.time.LocalDate;

public class NormalPatient extends Patient {
    private int expenses;
    public NormalPatient() {}
    public NormalPatient(int id, String medicalRecordID, String nameID, String name, LocalDate dateIn, LocalDate dateOut, String reason, int expenses) {
        super(id,medicalRecordID,nameID,name,dateIn,dateOut,reason);
        this.expenses = expenses;
    }
    public int getExpenses() {
        return expenses;
    }
    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "NormalPatient{" +
                super.getId()+","+super.getMedicalRecordID()+","+super.getNameID()+","+super.getName()+","+super.getDateIn()+","+super.getDateOut()+","+super.getReason()+","+expenses+
                '}';
    }

    @Override
    public String getInfo() {
        return super.getId()+","+super.getMedicalRecordID()+","+super.getNameID()+","+super.getName()+","+super.getDateIn()+","+super.getDateOut()+","+super.getReason()+","+expenses;
    }


}
