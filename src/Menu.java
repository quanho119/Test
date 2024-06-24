import Manager.MedicalRecordManager;
import Exception.*;
import Patient.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    private static final MedicalRecordManager medicalRecordManager=MedicalRecordManager.getInstance();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static int order=1;
    private Menu(){}
    private static Menu instance;
    public static Menu getInstance(){
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
    public void showMenu(){
        System.out.println("            --CHƯƠNG TRÌNH QUẢN LÍ BỆNH ÁN--        ");
        makeChoice();
    }
    public void makeChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Thêm mới");
        System.out.println("2. Xóa");
        System.out.println("3. Xem danh sách các bệnh án");
        System.out.println("4. Thoát");
        System.out.println("Chọn chức năng");
        int choice=-1;
        try {
            while (choice != 4) {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        add();
                        break;
                    case 2:
                        remove();
                        break;
                    case 3:
                        showList();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");


                }
            }
        }catch (DuplicateMedicalRecordException e){
            System.out.println(e.getMessage());
        }
    }
    public void add() throws DuplicateMedicalRecordException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Loại bệnh án");
        System.out.println("1. Bệnh án thường");
        System.out.println("2. Bệnh án VIP");
        int Type=Integer.parseInt(scanner.nextLine());
        String medicalRecordCode="";
        while(!medicalRecordCode.matches("BA-\\d{3}")||isMedicalRecordCodeExists(medicalRecordCode)) {
            System.out.print("Mã bệnh án (BA-XXX): ");
            medicalRecordCode=scanner.nextLine();
            if (!medicalRecordCode.matches("BA-\\d{3}")) {
                System.out.println("Mã bệnh án không hợp lệ. Vui lòng nhập lại.");
            }
            else if (isMedicalRecordCodeExists(medicalRecordCode)) {
                throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại.");
            }
        }

        String nameID="";
        while(!medicalRecordCode.matches("BN-\\d{3}")||isNameID(nameID)) {
            System.out.println("Mã bệnh nhân BN-XXX");
            medicalRecordCode=scanner.nextLine();
            if (!medicalRecordCode.matches("BN-\\d{3}")) {
                System.out.println("Mã bệnh nhân không hợp lệ. Vui lòng nhập lại.");
            }
            else if (isNameID(nameID)) {
                throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại.");
            }
        }
        System.out.println("Tên bệnh nhân");
        String name=scanner.nextLine().trim();
        System.out.println("Ngày nhập viện");
        LocalDate dateIn=LocalDate.parse(scanner.nextLine(),formatter);
        System.out.println("Ngày xuất viện");
        LocalDate dateOut=LocalDate.parse(scanner.nextLine(),formatter);
        System.out.println("Lí do nhập viện");
        String reason = scanner.nextLine().trim();
        switch (Type){
            case 1:
                System.out.println("Phí nhập viện");
                int expense=Integer.parseInt(scanner.nextLine());
                NormalPatient patientAdd1=new NormalPatient(order,medicalRecordCode,nameID,name,dateIn,dateOut,reason,expense);
                medicalRecordManager.addPatient(patientAdd1);
                break;
            case 2:
                System.out.println("Loại VIP");
                String vipType=scanner.nextLine().trim();
                System.out.println("Thời hạn VIP");
                String vipReason=scanner.nextLine().trim();
                VipPatient patientAdd2= new VipPatient(order,medicalRecordCode,nameID,name,dateIn,dateOut,reason,vipType,vipReason);
                medicalRecordManager.addPatient(patientAdd2);
                break;
            default:
                System.out.println("Vui lòng nhập lại loại bệnh án");
        }
        order++;
    }
    public void remove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã bệnh nhân");
        String medicalRecordCode=scanner.nextLine();
        System.out.println("Có hay không");
        String bool=scanner.nextLine();
        if(Objects.equals(bool, "yes")) {
            medicalRecordManager.removePatient(medicalRecordCode);
        }
    }
    public void showList(){
        medicalRecordManager.showList();
    }
    private boolean isMedicalRecordCodeExists(String medicalRecordCode) {
        if(medicalRecordManager.DupMedical(medicalRecordCode)) {
            return true;
        }
        return false;
    }
    private boolean isNameID(String nameID) {
        if(medicalRecordManager.DupName(nameID)) {
            return true;
        }
        return false;
    }
}
