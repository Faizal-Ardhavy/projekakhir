
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projek.db.DBHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class NasabahModel {
    public final Connection conn;

    public NasabahModel(String driver) throws SQLException {
    this.conn=DBHelper.getConnection(driver);
    }
    public void addNasabah(Individu individu) throws SQLException{
        String insertNasabah="INSERT INTO Nasabah(id_nasabah, nama,alamat)" + "VALUES(?,?,?)";
        String insertIndividu="INSERT INTO Individu(id_nasabah, nik,npwp)" + "VALUES(?,?,?)";
        String insertRekening="INSERT INTO Rekening(noRekening, saldo, id_nasabah)" + "VALUES(?,?,?)";
        
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, individu.getId_nasabah());
        stmtNasabah.setString(2, individu.getNama());
        stmtNasabah.setString(3, individu.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtIndividu = conn.prepareStatement(insertIndividu);
        stmtIndividu.setInt(1, individu.getId_nasabah());
        stmtIndividu.setLong(2, individu.getNik());
        stmtIndividu.setLong(3, individu.getNpwp());
        stmtIndividu.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, individu.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, individu.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, individu.getId_nasabah());
        stmtRekening.execute();
    }
    
    
    public void addNasabah(Perusahaan perushaan) throws SQLException{
        String insertNasabah="INSERT INTO Nasabah(id_nasabah, nama,alamat)" + "VALUES(?,?,?)";
        String insertPerushaan="INSERT INTO Perushaan(id_nasabah, nib)" + "VALUES(?,?)";
        String insertRekening="INSERT INTO Rekening(noRekening, saldo, id_nasabah)" + "VALUES(?,?,?)";
        
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, perushaan.getId_nasabah());
        stmtNasabah.setString(2, perushaan.getNama());
        stmtNasabah.setString(3, perushaan.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtPerushaan = conn.prepareStatement(insertPerushaan);
        stmtPerushaan.setInt(1, perushaan.getId_nasabah());
        stmtPerushaan.setString(2, perushaan.getNib());
        stmtPerushaan.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, perushaan.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, perushaan.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, perushaan.getId_nasabah());
        stmtRekening.execute();
    }
    
     public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();
        String sql="SELECT `nik`, `npwp`,`id_nasabah`, `nama`,`alamat` "
                + "FROM `Nasabah` NATURAL JOIN `Individu` "
                + "ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT noRekening, saldo "
                    + "FROM `Rekening` WHERE id_nasabah="+rs.getInt(1);
                ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
                
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                while (rsRekening.next()){
                    dataRekening.add(new Rekening(rsRekening.getInt(1), rsRekening.getDouble(2)));
                }
                 data.add(new Individu(rs.getLong(1), rs.getLong(2), rs.getInt(3), rs.getString(4), dataRekening, rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    public ObservableList<Perusahaan> getPerusahaan(){
       ObservableList<Perusahaan> data = FXCollections.observableArrayList();
       String sql="SELECT `nib`,`id_nasabah`, `nama`,`alamat` "
               + "FROM `Nasabah` NATURAL JOIN `Perusahaan` "
               + "ORDER BY nama";
       try {
           ResultSet rs = conn.createStatement().executeQuery(sql);
           while (rs.next()){
               String sqlRekening = "SELECT noRekening, saldo "
                   + "FROM Rekening WHERE id_nasabah="+rs.getInt(1);
               ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
               ArrayList<Rekening> dataRekening = new ArrayList<>();
               while (rsRekening.next()){
                   dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
               }
               data.add(new Perusahaan(rs.getString(1),rs.getInt(2),rs.getString(3),dataRekening,rs.getString(4)));
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(NasabahModel.class.getName()).log(Level.SEVERE, null, ex);
       }
       return data;
   }
    
    public ObservableList<Rekening> getRekenings(int id_nasabah){
       ObservableList<Rekening> data = FXCollections.observableArrayList();
       String sql="SELECT `noRekening`, `saldo` "
               + "FROM `Rekening` "
               + "WHERE id_nasabah="+id_nasabah;
       try {
           ResultSet rs = conn.createStatement().executeQuery(sql);
           while (rs.next()){
               data.add(new Rekening(rs.getInt(1),rs.getDouble(2)));
           }
       } catch (SQLException ex) {
           Logger.getLogger(NasabahModel.class.getName()).log(Level.SEVERE, null, ex);
       }
       return data;
   }
      public int nextIdNasabah() throws SQLException{
        String sql="SELECT MAX(id_nasabah) from Nasabah";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)==0?1000001:rs.getInt(1) + 1;
            }
        return 1000001;
    }
   public int nextRekeningNumber(int id_nasabah) throws SQLException{
       String sql="SELECT MAX(noRekening) FROM Rekening WHERE id_nasabah="+id_nasabah;
       ResultSet rs = conn.createStatement().executeQuery(sql);
       while (rs.next()){
               return rs.getInt(1)+1;
           }
       return 0;
   }
   
   public void addRekening(int id_nasabah, Rekening acc) throws SQLException{
       String insertRekening = "INSERT INTO Rekening (noRekening, saldo, id_nasabah)" + " VALUES (?,?,?)";
 
       PreparedStatement stmtPerusahaan = conn.prepareStatement(insertRekening);
       stmtPerusahaan.setInt(1, acc.getNoRekening());
       stmtPerusahaan.setDouble(2, acc.getSaldo());
       stmtPerusahaan.setInt(3, id_nasabah);
       stmtPerusahaan.execute();
       
   }
    
}
