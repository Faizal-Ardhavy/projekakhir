
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Rekening {
    protected IntegerProperty noRekening;
    protected DoubleProperty saldo;

    public Rekening(int noRekening, double saldo) {
        this.noRekening=new SimpleIntegerProperty(noRekening);
        this.saldo=new SimpleDoubleProperty(saldo);
    }
    
    
    public void setNoRekening(int noRekening){
        this.noRekening.set(noRekening);
    }
    
    public int getNoRekening(){
        return noRekening.get();
    }
    
    public void tambahSaldo(double jumlah){
        this.saldo.set(getSaldo()+jumlah);
    }
    public double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(double saldo) {
        this.saldo.set(saldo);
    }
    public IntegerProperty noRekeningProperty(){
        return noRekening; 
    }
    public DoubleProperty saldoProperty(){
        return saldo; 
    }
    
    
    public void tarikTunai(double jumlah){
        if(getSaldo()-jumlah>=0&&getSaldo()>=0){
            this.saldo.set(getSaldo()-jumlah);
        }
        else{
            
        }
    }
}

