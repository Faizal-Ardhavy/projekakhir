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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Perusahaan extends Nasabah{
    private StringProperty nib;

    public Perusahaan(String nib,int id_nasabah, String nama, ArrayList<Rekening> rekening, String alamat) {
        super(id_nasabah,nama, rekening, alamat);
        this.nib=new SimpleStringProperty(nib);
    }

    public Perusahaan(String nib,int id_nasabah, String nama, Rekening rekening, String alamat) {
        super(id_nasabah, nama, rekening, alamat);
        this.nib=new SimpleStringProperty(nib);
    }

    public void setNib(String nib){
        this.nib.set(nib);
    }
    public String getNib(){
        return nib.get();
    }
    public StringProperty nibProperty(){
        return nib;
    }
    
    @Override
    public void print() {
        System.out.println("Nama: " + getNama());
        System.out.println("Alamat: " + getAlamat());
        System.out.println("NIB: " + getNib());
        System.out.println("===========================================");
        String infoRekening=String.format("No Rekening"+"%26s","Saldo");
        System.out.println(infoRekening);
        System.out.println("===========================================");
        for(Rekening index : rekening){
            System.out.printf("%s%34.2f\n",index.getNoRekening(),index.getSaldo());
        }
    }
} 
