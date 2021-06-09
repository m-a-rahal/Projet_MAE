package Home;

import Classes.ClauseList;
import Classes.table_cnf_clause;
import io_classes.FileManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import partie1.A_star;
import partie1.BFS;
import partie1.DFS;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    /*@FXML
    private CheckBox bfs_check;

    @FXML
    private CheckBox dfs_check;

    @FXML
    private CheckBox a_check;*/

    @FXML
    private RadioButton rad_sat_depuis_fichier;

    @FXML
    private ToggleGroup SAT_selction;

    @FXML
    private RadioButton rad_sat_random;

    @FXML
    private TextField text_file_path;

    @FXML
    private Button chose_file;

    @FXML
    private CheckBox chk_ifSat;

    @FXML
    private TextField text_nbr_var;

    @FXML
    private TextField text_nbr_clause;

    @FXML
    private TableView<table_cnf_clause> tabV_clause;

    @FXML
    private TableColumn<table_cnf_clause, String> tabCol_clause;

    @FXML
    private Button run;
    @FXML
    private Button genere_clauses_btn;

    @FXML
    private BarChart<?, ?> bar_graph;

    @FXML
    private CategoryAxis algo;

    @FXML
    private NumberAxis temps;

    @FXML
    private Label a_star_temps;

    @FXML
    private Label bfs_temps;

    @FXML
    private Label dfs_temps;


    @FXML
    public void toggle_SAT_radios(){

        boolean fichier;
        fichier = rad_sat_depuis_fichier.isSelected();
        chk_ifSat.setDisable(fichier);
        text_nbr_var.setDisable(fichier);
        text_nbr_clause.setDisable(fichier);
        text_file_path.setDisable(!fichier);

    }


    FileChooser fileChooser = null;
    File file = null;

    @FXML
    public void chooseFile_pressed(){
        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        if(file != null ){
            text_file_path.setText(file.getName());
            readFile(file.getAbsolutePath());
        }
    }




    private void readFile(String path){
        ObservableList<table_cnf_clause> clauses = FXCollections.observableArrayList();

        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null){
                clauses.add(new table_cnf_clause(line));

            }
            tabCol_clause.setCellValueFactory(new PropertyValueFactory<>("clause"));
            tabV_clause.setItems(clauses);

        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    ClauseList clauses;
    @FXML
    public void genere_clauses_pressed(){
        int n  = Integer.parseInt(text_nbr_clause.getText());
        int m  = Integer.parseInt(text_nbr_var.getText());
        boolean SAT = chk_ifSat.isSelected();
         clauses = new ClauseList(n,m).gen_aleat(SAT);

        ObservableList<table_cnf_clause> clausesOBL = FXCollections.observableArrayList();

        for (int i = 0; i < clauses.as_string_list().size(); i++) {
            //System.out.println(i+" - "+clauses.as_string_list().get(i));
            clausesOBL.add(new table_cnf_clause(clauses.as_string_list().get(i)));
        }

        tabCol_clause.setCellValueFactory(new PropertyValueFactory<>("clause"));
        tabV_clause.setItems(clausesOBL);
    }

    long temps_a_star = 0;
    long temps_dfs = 0;
    long temps_bfs = 0;


    @FXML
    public void run_press() throws InterruptedException {
        ClauseList clauseList;
        if(rad_sat_depuis_fichier.isSelected()){
            clauseList = new FileManager().read(file.getAbsolutePath());
        }else{
            clauseList = clauses;
        }

        A_Star_Thread a_star_thread = new A_Star_Thread(clauseList);
        BFS_Thread bfs_thread = new BFS_Thread(clauseList);
        DFS_Thread dfs_thread = new DFS_Thread(clauseList);
        a_star_thread.start();
        bfs_thread.start();
        dfs_thread.start();

        a_star_thread.join();
        bfs_thread.join();
        dfs_thread.join();



//        if(!a_star_thread.isAlive() && !bfs_thread.isAlive() &&!dfs_thread.isAlive() ){
//                        XYChart.Series set = new XYChart.Series();
//                        XYChart.Series set1 = new XYChart.Series();
//                        XYChart.Series set2 = new XYChart.Series();
//
//                        set.getData().add(new XYChart.Data<>("A*",temps_a_star));
//                        set1.getData().add(new XYChart.Data<>("BFS",temps_bfs));
//                        set2.getData().add(new XYChart.Data<>("DFS",temps_dfs));
//
//                        bar_graph.getData().add(set);
//                        bar_graph.getData().add(set1);
//                        bar_graph.getData().add(set2);
////        }


        //bar_graph.getData().addAll(set);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chk_ifSat.setDisable(true);
        text_nbr_var.setDisable(true);
        text_nbr_clause.setDisable(true);
        text_file_path.setPromptText("Selectionner un ficher");
        /*bfs_check.setSelected(true);
        bfs_check.setDisable(true);
        dfs_check.setSelected(true);
        dfs_check.setDisable(true);
        a_check.setSelected(true);
        a_check.setDisable(true);*/


        XYChart.Series set = new XYChart.Series();
        XYChart.Series set1 = new XYChart.Series();
        XYChart.Series set2 = new XYChart.Series();

        set.getData().add(new XYChart.Data<>("A*",temps_a_star));
        set1.getData().add(new XYChart.Data<>("BFS",temps_bfs));
        set2.getData().add(new XYChart.Data<>("DFS",temps_dfs));

        bar_graph.getData().add(set);
        bar_graph.getData().add(set1);
        bar_graph.getData().add(set2);



    }





    public class A_Star_Thread extends Thread{
        ClauseList file;
        public A_Star_Thread(ClauseList file){
            super("A_Star");
            this.file = file;
        }
        public void run(){
//            try {
                temps_a_star = new A_star().temps_execution(this.file);
                System.out.println(temps_a_star + " s");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    XYChart.Series set = new XYChart.Series();
                    set.getData().add(new XYChart.Data<>("A*",temps_a_star));
                    bar_graph.getData().add(set);
                    a_star_temps.setText(temps_a_star+" s");
                }
            });


//            }finally {
//                XYChart.Series set = new XYChart.Series();
//                set.getData().clear();
//                set.getData().add(new XYChart.Data<>("A*",temps_a_star));
//                bar_graph.getData().clear();
//            }

        }
    }

    public class BFS_Thread extends Thread{
        ClauseList file;
        public BFS_Thread(ClauseList file){
            super("BFS");
            this.file = file;
        }
        public void run(){
            temps_bfs = new BFS().temps_execution(this.file);
            System.out.println(temps_bfs + " s");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    XYChart.Series set1 = new XYChart.Series();
                    set1.getData().add(new XYChart.Data<>("BFS",temps_bfs));
                    bar_graph.getData().add(set1);
                    bfs_temps.setText(temps_bfs+" s");
                }
            });

        }
    }

    public class DFS_Thread extends Thread{
        ClauseList file;
        public DFS_Thread(ClauseList file){
            super("DFS");
            this.file = file;
        }
        public void run(){
            temps_dfs = new DFS().temps_execution(this.file);
            System.out.println(temps_dfs + " s");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    XYChart.Series set2 = new XYChart.Series();
                    set2.getData().add(new XYChart.Data<>("DFS",temps_dfs));
                    bar_graph.getData().add(set2);
                    dfs_temps.setText(temps_dfs+ " s");
                }
            });

        }
    }

}
