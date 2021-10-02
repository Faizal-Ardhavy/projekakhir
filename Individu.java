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
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
public class Individu extends Nasabah{
    LongProperty nik;
    LongProperty npwp;    

    public Individu(Long nik, Long npwp, int id_nasabah, String nama, ArrayList<Rekening> rekening, String alamat) {
        super(id_nasabah,nama, rekening, alamat);
        this.nik=new SimpleLongProperty(nik);
        this.npwp=new SimpleLongProperty(npwp);
    }

    public Individu(Long nik, Long npwp, int id_nasabah, String nama, Rekening rekening, String alamat) {
        super(id_nasabah,nama, rekening, alamat);
        this.nik=new SimpleLongProperty(nik);
        this.npwp=new SimpleLongProperty(npwp);
    }
    

    public long getNik() {
        return nik.get();
    }

    public void setNik(long nik) {
        this.nik.set(nik);
    }

    public long getNpwp() {
        return npwp.get();
    }

    public void setNpwp(long npwp) {
        this.npwp.set(npwp);
    }
    public LongProperty nikProperty(){
        return nik;
    }
    public LongProperty npwpProperty(){
        return npwp;
    }    
    
    public void print() {
        System.out.println("Nama: " + getNama());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("NIK: " + getNik());
        System.out.println("NPWP: "+ getNpwp());
        System.out.println("===========================================");
        String infoRekening=String.format("No Rekening"+"%26s","Saldo");
        System.out.println(infoRekening);
        System.out.println("===========================================");
        for(Rekening index : rekening){
            System.out.printf("%s%34.2f\n",index.getNoRekening(),index.getSaldo());
        }
    }
}
