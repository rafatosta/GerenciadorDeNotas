package com.uefs.gerenciadordenotas.controller;

import com.uefs.gerenciadordenotas.Main;
import com.uefs.gerenciadordenotas.components.DetalhesIconButton;
import com.uefs.gerenciadordenotas.dao.DAO;
import com.uefs.gerenciadordenotas.model.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainController implements MyController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAlt;

    @FXML
    private Button btnDel;

    @FXML
    private Label labelError;

    @FXML
    private TextField nomeAluno;

    @FXML
    private TextField nota1;

    @FXML
    private TextField nota2;

    @FXML
    private TextField nota3;

    @FXML
    private TableView<Aluno> tabelaAlunos;

    private ObservableList<Aluno> alunosData;

    @FXML
    void initialize() {
        DAO.getAluno().create(new Aluno("Joao", 5.3, 7.5, 9.5));
        DAO.getAluno().create(new Aluno("Maria", 5.3, 1.5, 9.5));
        DAO.getAluno().create(new Aluno("Maria 2", 5.3, 1.5, 9.5));
        DAO.getAluno().create(new Aluno("João 2", 5.3, 1.5, 9.5));


        this.alunosData = FXCollections.observableArrayList();
        this.alunosData.addAll(DAO.getAluno().findMany());

        TableColumn nomeCol = new TableColumn("Nome");
        TableColumn n1Col = new TableColumn("Nota 1");
        TableColumn n2Col = new TableColumn("Nota 2");
        TableColumn n3Col = new TableColumn("Nota 3");
        TableColumn mediaCol = new TableColumn("Média");
        TableColumn situacaoCol = new TableColumn("Situação");
        TableColumn detalheCol = new TableColumn("Detalhe");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        n1Col.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("n1"));
        n2Col.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("n2"));
        n3Col.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("n3"));
        mediaCol.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("media"));
        situacaoCol.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("situacao"));

        detalheCol.setCellFactory(new DetalhesIconButton(this));
        detalheCol.setCellValueFactory(new PropertyValueFactory<Object, Aluno>("aluno"));

        this.tabelaAlunos.getColumns().addAll(nomeCol, n1Col, n2Col, n3Col, mediaCol, situacaoCol, detalheCol);
        this.tabelaAlunos.setItems(alunosData);

    }

    private Aluno getDataAluno() {
        return new Aluno(this.nomeAluno.getText(), Double.parseDouble(this.nota1.getText()),
                Double.parseDouble(this.nota2.getText()), Double.parseDouble(this.nota3.getText()));
    }

    @FXML
    void btnAddAction(ActionEvent event) {

        Aluno novoAluno = getDataAluno();
        try {
            //atualiza o view
            this.alunosData.add(novoAluno);
            this.clearAll();
            //salva no DAO
            DAO.getAluno().create(novoAluno);
        } catch (Exception e) {
            this.labelError.setText("Entradas inválidas!");
        }
    }

    @FXML
    void btnAltAction(ActionEvent event) {
        this.editAluno(null);

    }

    public void editAluno(Aluno a) {
        int i = this.tabelaAlunos.getSelectionModel().getSelectedIndex();

        if (i >= 0) {
            try {
                Aluno novoAluno;
                if (a == null) {
                    novoAluno = this.getDataAluno();
                } else {
                    novoAluno = a;
                }

                novoAluno.setId(i);
                this.alunosData.set(i, novoAluno);
                this.clearAll();

                DAO.getAluno().update(novoAluno);
            } catch (Exception e) {
                this.labelError.setText("Entradas inválidas!");
            }

        }
    }

    @FXML
    void btnDelAction(ActionEvent event) {
        int i = this.tabelaAlunos.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            this.alunosData.remove(i);
            DAO.getAluno().delete(i);
        }
    }

    private void clearAll() {
        this.nomeAluno.clear();
        this.nota1.clear();
        this.nota2.clear();
        this.nota3.clear();
        this.labelError.setText("");
    }

    @Override
    public void consultar(Object o) {
        this.openDetalhes((Aluno) o);
    }

    @Override
    public void editar(Object o) {

    }

    @Override
    public void excluir(Object o) {

    }

    public void openDetalhes(Aluno a) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("aluno-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Consulta aluno: " + a.getNome());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);

            AlunoController alunoController = fxmlLoader.getController();
            alunoController.setDialogStage(stage);
            alunoController.setMainController(this);
            alunoController.setAluno(a);

            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void dataUpdate(Aluno a) {
        this.editAluno(a);
    }
}
