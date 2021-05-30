package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Application {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem exit_menu;

    @FXML
    private CheckBox bfs_choice;

    @FXML
    private CheckBox dfs_choice;

    @FXML
    private CheckBox a_star_choice;

    @FXML
    private RadioButton sat_from_file_radio;

    @FXML
    private ToggleGroup sat_src_radio;

    @FXML
    private RadioButton sat_gen_alea_radio;

    @FXML
    private TextField fichier_cnf_txt;

    @FXML
    private Button choisir_fichier_btn;

    @FXML
    private CheckBox sat_choice;

    @FXML
    private TextField nb_var_txt;

    @FXML
    private Label nbr_var_label;

    @FXML
    private TextField nbr_clauses_txt;

    @FXML
    private Label nbr_clauses_label;

    @FXML
    private Button generer_clauses_btn;

    @FXML
    private ListView<?> clauses_list;

    @FXML
    private Label clauses_data_label;

    @FXML
    private BarChart<?, ?> chart;

    @FXML
    private Button test_btn;

    @FXML
    void choose_cnf_file(ActionEvent event) {

    }

    @FXML
    void exit_prog(ActionEvent event) {

    }

    @FXML
    void generer_clauses_btn_action(ActionEvent event) {

    }

    @FXML
    void run_test(ActionEvent event) {

    }

    @FXML
    void src_radio_update(ActionEvent event) {

    }

    @FXML
    void update_a_star_choice(ActionEvent event) {

    }

    @FXML
    void update_bfs_choice(ActionEvent event) {

    }

    @FXML
    void update_dfs_choice(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert exit_menu != null : "fx:id=\"exit_menu\" was not injected: check your FXML file 'Untitled'.";
        assert bfs_choice != null : "fx:id=\"bfs_choice\" was not injected: check your FXML file 'Untitled'.";
        assert dfs_choice != null : "fx:id=\"dfs_choice\" was not injected: check your FXML file 'Untitled'.";
        assert a_star_choice != null : "fx:id=\"a_star_choice\" was not injected: check your FXML file 'Untitled'.";
        assert sat_from_file_radio != null : "fx:id=\"sat_from_file_radio\" was not injected: check your FXML file 'Untitled'.";
        assert sat_src_radio != null : "fx:id=\"sat_src_radio\" was not injected: check your FXML file 'Untitled'.";
        assert sat_gen_alea_radio != null : "fx:id=\"sat_gen_alea_radio\" was not injected: check your FXML file 'Untitled'.";
        assert fichier_cnf_txt != null : "fx:id=\"fichier_cnf_txt\" was not injected: check your FXML file 'Untitled'.";
        assert choisir_fichier_btn != null : "fx:id=\"choisir_fichier_btn\" was not injected: check your FXML file 'Untitled'.";
        assert sat_choice != null : "fx:id=\"sat_choice\" was not injected: check your FXML file 'Untitled'.";
        assert nb_var_txt != null : "fx:id=\"nb_var_txt\" was not injected: check your FXML file 'Untitled'.";
        assert nbr_var_label != null : "fx:id=\"nbr_var_label\" was not injected: check your FXML file 'Untitled'.";
        assert nbr_clauses_txt != null : "fx:id=\"nbr_clauses_txt\" was not injected: check your FXML file 'Untitled'.";
        assert nbr_clauses_label != null : "fx:id=\"nbr_clauses_label\" was not injected: check your FXML file 'Untitled'.";
        assert generer_clauses_btn != null : "fx:id=\"generer_clauses_btn\" was not injected: check your FXML file 'Untitled'.";
        assert clauses_list != null : "fx:id=\"clauses_list\" was not injected: check your FXML file 'Untitled'.";
        assert clauses_data_label != null : "fx:id=\"clauses_data_label\" was not injected: check your FXML file 'Untitled'.";
        assert chart != null : "fx:id=\"chart\" was not injected: check your FXML file 'Untitled'.";
        assert test_btn != null : "fx:id=\"test_btn\" was not injected: check your FXML file 'Untitled'.";

    }
}
