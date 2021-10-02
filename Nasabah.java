/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
import java.util.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
abstract class Nasabah {
    protected IntegerProperty id_nasabah;
    protected StringProperty nama;
    protected ArrayList<Rekening> rekening=new ArrayList<Rekening>();
    protected StringProperty alamat;

    public Nasabah(int id_nasabah, String nama, ArrayList<Rekening> rekening, String alamat) {
        this.id_nasabah=new SimpleIntegerProperty(id_nasabah);
        this.nama=new SimpleStringProperty(nama);
        this.rekening = rekening;
        this.alamat=new SimpleStringProperty(alamat);
    }
    public Nasabah(int id_nasabah, String nama, Rekening rekening, String alamat) {
        this.id_nasabah=new SimpleIntegerProperty(id_nasabah);
        this.nama=new SimpleStringProperty(nama);
        this.rekening.add(rekening);
        this.alamat=new SimpleStringProperty(alamat);
    }

    public int getId_nasabah() {
        return id_nasabah.get();
    }

    public void setId_nasabah(int id_nasabah) {
        this.id_nasabah.set(id_nasabah);
    }
    
    public String getNama() {
        return nama.get();
    }
    public ArrayList<Rekening> getRekening() {
        return rekening;
    }
    public String getAlamat() {
        return alamat.get();
    }
    
    public void setNama(String nama){
        this.nama.set(nama);
    }
    public void setRekening(ArrayList<Rekening> rekening){
        this.rekening=rekening;
    }
    public void setAlamat(String alamat){
        this.alamat.set(alamat);
    }
    public void tambahRekening(Rekening rekening){
        this.rekening.add(rekening);
    }
    
    public IntegerProperty id_nasabah(){
        return id_nasabah;
    }
    public StringProperty namaProperty(){
        return nama;
    }
    public StringProperty alamatProperty(){
        return alamat;
    }
    
     public void print(){
        System.out.println("Nama: " + getNama());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("===========================================");
        System.out.println(String.format("No Rekening"+"%26s","Saldo"));
        System.out.println("===========================================");
        rekening.forEach(index -> {
            System.out.printf("%d %33.2f\n", index.getNoRekening(), index.getSaldo());
        });
    }
     
    
}    


