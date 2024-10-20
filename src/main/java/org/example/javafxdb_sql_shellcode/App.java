package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);

            switch (input) {
                case 'g':
                     launch(args); //GUI
                    break;

                case 'c':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;

                case 'i':
                    System.out.print("Enter Name: ");
                    String name = scan.next();
                    System.out.print("Enter Email: ");
                    String email = scan.next();
                    System.out.print("Enter Phone: ");
                    String phone = scan.next();
                    System.out.print("Enter Address: ");
                    String address = scan.next();
                    System.out.print("Enter Password: ");
                    String password = scan.next();
                    cdbop.insertUser(name, email, phone, address, password); //Your insertUser method
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName); //Your queryUserByName method
                    break;
                case 'e':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();

       
    }


    public static class DB_GUI_Controller implements Initializable {

        private final ObservableList<Person> data =
                FXCollections.observableArrayList(
                        new Person(1, "Jacob", "Smith", "CPIS", "CS"),
                        new Person(2, "Jacob2", "Smith1", "CPIS1", "CS")

                );


        @FXML
        TextField first_name, last_name, department, major;
        @FXML
        private TableView<Person> tv;
        @FXML
        private TableColumn<Person, Integer> tv_id;
        @FXML
        private TableColumn<Person, String> tv_fn, tv_ln, tv_dept, tv_major;

        @FXML
        ImageView img_view;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tv_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
            tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));


            tv.setItems(data);
        }


        @FXML
        protected void addNewRecord() {


            data.add(new Person(
                    data.size()+1,
                    first_name.getText(),
                    last_name.getText(),
                    department.getText(),
                    major.getText()
            ));
        }

        @FXML
        protected void clearForm() {
            first_name.clear();
            last_name.setText("");
            department.setText("");
            major.setText("");
        }

        @FXML
        protected void closeApplication() {
            System.exit(0);
        }


        @FXML
        protected void editRecord() {
            Person p= tv.getSelectionModel().getSelectedItem();
            int c=data.indexOf(p);
            Person p2= new Person();
            p2.setId(c+1);
            p2.setFirstName(first_name.getText());
            p2.setLastName(last_name.getText());
            p2.setDept(department.getText());
            p2.setMajor(major.getText());
            data.remove(c);
            data.add(c,p2);
            tv.getSelectionModel().select(c);
        }

        @FXML
        protected void deleteRecord() {
            Person p= tv.getSelectionModel().getSelectedItem();
            data.remove(p);
        }



        @FXML
        protected void showImage() {
            File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
            if(file!=null){
                img_view.setImage(new Image(file.toURI().toString()));

            }
        }





        @FXML
        protected void selectedItemTV(MouseEvent mouseEvent) {
            Person p= tv.getSelectionModel().getSelectedItem();
            first_name.setText(p.getFirstName());
            last_name.setText(p.getLastName());
            department.setText(p.getDept());
            major.setText(p.getMajor());


        }
    }

    public static class Person {


        private Integer id;
        private String firstName;
        private String lastName;
        private String dept;
        private String major;

        public Person() {
        }


        public Person(Integer id, String firstName, String lastName, String dept, String major) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.dept = dept;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }


        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }


        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }


    }
}
