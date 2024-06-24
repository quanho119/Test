package Manager;
import Patient.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordManager {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private MedicalRecordManager(){}
    private static MedicalRecordManager instance;
    private static File file=new File("./src/data/medical_records.csv");
    private static List<Patient> patients= new ArrayList<>();
    public static MedicalRecordManager getInstance() {
        if (instance == null) {
            instance = new MedicalRecordManager();
        }
        return instance;
    }
    public void addPatient(Patient newPatient) {
        try{
            write(newPatient);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void showList() {
        try{
            this.patients=read();
            for(Patient patient:patients){
                System.out.println(patient);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void removePatient(String nameID) {
        try {
            this.patients=read();
            patients.removeIf(patient -> patient.getNameID().equals(nameID));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void write(Patient patient) throws IOException {
        FileWriter fileWriter=new FileWriter(file,true);
        BufferedWriter bw=new BufferedWriter(fileWriter);
        String info=patient.getInfo() +"\n";
        bw.write(info);
        bw.close();
        fileWriter.close();
    }
    public List<Patient> read() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Patient> patients = new ArrayList<>();
        String content=null;
        while((content=bufferedReader.readLine())!=null) {
            String[] info=content.split(",");
            int id=Integer.parseInt(info[0]);
            String medicalRecordID=info[1];
            String nameID=info[2];
            String name=info[3];
            LocalDate dateIn= LocalDate.parse(info[4],formatter);
            LocalDate dateOut= LocalDate.parse(info[5],formatter);
            String reason=info[6];
            String check=info[7];
            if(check.matches("VIP (I|II|III)")) {
                String period=info[8];
                VipPatient patient= new VipPatient(id,medicalRecordID,nameID,name,dateIn,dateOut,reason,check,period);
                patients.add(patient);
            }
            else{
                NormalPatient patient =new NormalPatient(id,medicalRecordID,nameID,name,dateIn,dateOut,reason,Integer.parseInt(check));
            }
        }
        return patients;
    }
    public boolean DupName(String nameID) {
        try{
            this.patients=read();
            for(Patient patient:patients){
                if(patient.getNameID().equals(nameID)) {
                    return true;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean DupMedical(String medicalRecordID) {
        try{
            this.patients=read();
            for(Patient patient:patients){
                if(patient.getNameID().equals(medicalRecordID)) {
                    return true;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
