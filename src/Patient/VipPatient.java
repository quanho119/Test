package Patient;

import java.time.LocalDate;
public class VipPatient extends Patient {
    private String vipType;     // loai vip
    private String vipPeriod;   // thoi han vip
    public VipPatient() {}
    public VipPatient(int id, String medicalRecordID, String nameID, String name, LocalDate dateIn, LocalDate dateOut, String reason, String vipType, String vipPeriod) {
        super(id, medicalRecordID, nameID, name, dateIn, dateOut, reason);
        this.vipType = vipType;
        this.vipPeriod = vipPeriod;
    }

    @Override
    public String toString() {
        return "VipPatient{" +
                super.getId()+","+super.getMedicalRecordID()+","+super.getNameID()+","+super.getName()+","+super.getDateIn()+","+super.getDateOut()+","+super.getReason()+","+vipType+","+vipPeriod+
                '}';
    }

    @Override
    public String getInfo() {
        return super.getId()+","+super.getMedicalRecordID()+","+super.getNameID()+","+super.getName()+","+super.getDateIn()+","+super.getDateOut()+","+super.getReason()+","+vipType+","+vipPeriod;
    }


}
