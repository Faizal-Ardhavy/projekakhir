import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NasabahController implements Initializable {
    
    @FXML
    private TextField tfId_nasabah;

    @FXML
    private TextField tfnama;

    @FXML
    private TextField tfalamat;

    @FXML
    private TextField tfnik;
    
    @FXML
    private TextField tfnpwp;

    @FXML
    private TextField tfnomorrekening;

    @FXML
    private TextField tfsaldo;

    @FXML
    private Button btnperbaruidata;

    @FXML
    private Button btnhapusdata;

    @FXML
    private TableView<Individu> tblnasabah;

    @FXML
    private TableColumn<Individu, Integer> colid_nasabah;

    @FXML
    private TableColumn<Individu, String> colnama;

    @FXML
    private TableColumn<Individu, String> colalamat;

    @FXML
    private TableColumn<Individu, Long> colnik;

    @FXML
    private TableColumn<Individu, Long> colnpwp;

    @FXML
    private TableView<Rekening> tblrekening;

    @FXML
    private TableColumn<Rekening, Integer> colnomorRekening;

    @FXML
    private TableColumn<Rekening, Double> colsaldo;

    @FXML
    private TextField tfnewid_nasabah;

    @FXML
    private TextField tfnewnomorrekening;

    @FXML
    private TextField tfnewsaldo;

    @FXML
    private Button btnnewrekening;
    
    
    //perusahaan
    @FXML
    private TextField tfId_nasabah1;

    @FXML
    private TextField tfnama1;

    @FXML
    private TextField tfalamat1;

    @FXML
    private TextField tfnib1;

    @FXML
    private TextField tfnomorrekening1;

    @FXML
    private TextField tfsaldo1;

    @FXML
    private Button btnperbaruidata1;

    @FXML
    private Button btnhapusdata1;

    @FXML
    private TableView<Perusahaan> tblnasabah1;

    @FXML
    private TableColumn<Perusahaan, String> colid_nasabah1;

    @FXML
    private TableColumn<Perusahaan, String> colnama1;

    @FXML
    private TableColumn<Perusahaan, String> colalamat1;
    
    @FXML
    private TableColumn<Perusahaan, String> colnib1;
    
    @FXML
    private TableView<Rekening> tblrekening1;
    
    @FXML
    private TableColumn<Rekening, Integer> colnomorRekening1;

    @FXML
    private TableColumn<Rekening, Double> colsaldo1;

    @FXML
    private TextField tfnewid_nasabah1;

    @FXML
    private TextField tfnewnomorrekening1;

    @FXML
    private TextField tfnewsaldo1;

    @FXML
    private Button btnnewrekening1;

    @FXML
    private Label statusDB;
    
    private NasabahModel model;
    
    @FXML
    void handleAddNasabahBTN(ActionEvent event) {
        Individu i = new Individu(Long.parseLong(tfnik.getText()),
            Long.parseLong(tfnpwp.getText()),
            Integer.parseInt(tfId_nasabah.getText()),
            tfnama.getText(),
            new Rekening(Integer.parseInt(tfnomorrekening.getText()), Double.parseDouble(tfsaldo.getText())),
            tfalamat.getText());
        try {
            model.addNasabah(i);
            btnperbaruidata.fire();
            btnhapusdata.fire();
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleAddNasabahBTN1(ActionEvent event) {
        Perusahaan p = new Perusahaan(tfnib1.getText(),
            Integer.parseInt(tfId_nasabah1.getText()),
            tfnama1.getText(),
            new Rekening(Integer.parseInt(tfnomorrekening1.getText()), Double.parseDouble(tfsaldo1.getText())),
            tfalamat1.getText());
        try {
            model.addNasabah(p);
            btnperbaruidata1.fire();
            btnhapusdata1.fire();
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleAddNewNasabahBTN(ActionEvent event) {
        try {
            Rekening rek =  new Rekening(Integer.parseInt(tfnewnomorrekening.getText()),
                            Double.parseDouble(tfnewsaldo.getText()));
            
            model.addRekening(Integer.parseInt(tfnewid_nasabah.getText()), rek);          
            viewDataRekening(Integer.parseInt(tfnewid_nasabah.getText()));
            btnperbaruidata.fire();
            tfnewsaldo.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleAddNewNasabahBTN1(ActionEvent event) {
        try {
            Rekening rek =  new Rekening(Integer.parseInt(tfnewnomorrekening1.getText()),
                            Double.parseDouble(tfnewsaldo1.getText()));
            
            model.addRekening(Integer.parseInt(tfnewid_nasabah.getText()), rek);          
            viewDataRekening1(Integer.parseInt(tfnewid_nasabah.getText()));
            btnperbaruidata1.fire();
            tfnewsaldo1.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleClearDataBTN(ActionEvent event) {
        try {
            tfId_nasabah.setText("" + model.nextIdNasabah());
            tfnomorrekening.setText(tfId_nasabah.getText() + "01");
            tfnama.setText("");
            tfalamat.setText("");
            tfnik.setText("");
            tfnpwp.setText("");
            tfsaldo.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleClearDataBTN1(ActionEvent event) {
        try {
            tfId_nasabah1.setText("" + model.nextIdNasabah());
            tfnomorrekening1.setText(tfId_nasabah1.getText() + "01");
            tfnama1.setText("");
            tfalamat1.setText("");
            tfnib1.setText("");
            tfsaldo1.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleReloadDataBTN(ActionEvent event) {
         ObservableList<Individu> data = model.getIndividu();
        colid_nasabah.setCellValueFactory(new PropertyValueFactory<>("id_nasabah"));
        colnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colalamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colnik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colnpwp.setCellValueFactory(new PropertyValueFactory<>("npwp"));
        tblnasabah.setItems(null);
        tblnasabah.setItems(data);
        btnnewrekening.setDisable(true);
    }

    @FXML
    void handleReloadDataBTN1(ActionEvent event) {
        ObservableList<Perusahaan> data = model.getPerusahaan();
        colid_nasabah1.setCellValueFactory(new PropertyValueFactory<>("id_nasabah"));
        colnama1.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colalamat1.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colnib1.setCellValueFactory(new PropertyValueFactory<>("nib"));
        tblnasabah1.setItems(null);
        tblnasabah1.setItems(data);
        btnnewrekening1.setDisable(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            model = new NasabahModel("SQLite");
            statusDB.setText(model.conn == null ? "Not Connected" : "Connected");
            btnhapusdata.fire();
            btnperbaruidata.fire();
            btnhapusdata1.fire();
            btnperbaruidata1.fire();
        } catch (SQLException ex) {
            Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //individu
        tblnasabah.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblnasabah.getSelectionModel().getSelectedItem() != null){
                Individu i =  tblnasabah.getSelectionModel().getSelectedItem();
                viewDataRekening(i.getId_nasabah());
                
                btnnewrekening.setDisable(false);
                tfnewid_nasabah.setText("" + i.getId_nasabah());
                try {
                    tfnewnomorrekening.setText("" + model.nextRekeningNumber(i.getId_nasabah()));
                } catch (SQLException ex) {
                    Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tblnasabah1.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblnasabah1.getSelectionModel().getSelectedItem() != null){
                Perusahaan p =  tblnasabah1.getSelectionModel().getSelectedItem();
                viewDataRekening1(p.getId_nasabah());
                
                btnnewrekening1.setDisable(false);
                tfnewid_nasabah1.setText("" + p.getId_nasabah());
                try {
                    tfnewnomorrekening1.setText("" + model.nextRekeningNumber(p.getId_nasabah()));
                } catch (SQLException ex) {
                    Logger.getLogger(NasabahController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
    
    //individu
    public void viewDataRekening(int idNasabah){
        ObservableList<Rekening> data = model.getRekening(idNasabah);
        colnomorRekening.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colsaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblrekening.setItems(null);
        tblrekening.setItems(data);
    }
    
    //perusahaan
    public void viewDataRekening1(int idNasabah){
        ObservableList<Rekening> data = model.getRekening(idNasabah);
        colnomorRekening1.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colsaldo1.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblrekening1.setItems(null);
        tblrekening1.setItems(data);
    }
    
}
