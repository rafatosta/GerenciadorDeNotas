package com.uefs.gerenciadordenotas.controller;


import com.uefs.gerenciadordenotas.dao.DAO;
import com.uefs.gerenciadordenotas.model.Aluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlunoController {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField media;

    @FXML
    private TextField n1;

    @FXML
    private TextField n2;

    @FXML
    private TextField n3;

    @FXML
    private TextField nome;

    @FXML
    private TextField situacao;

    private Aluno aluno;

    private Stage dialogStage;

    private MainController mainController;

    @FXML
    void initialize() {

    }

    @FXML
    void closeDialog(ActionEvent event) {
        this.dialogStage.close();
    }

    public void setAluno(Aluno a) {
        this.aluno = a;
        this.nome.setText(a.getNome());
        this.n1.setText(a.getN1().toString());
        this.n2.setText(a.getN2().toString());
        this.n3.setText(a.getN3().toString());
        this.media.setText(a.getMedia().toString());
        this.situacao.setText(a.getSituacao());
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @FXML
    void saveAluno(ActionEvent event) {
        //salvar os dados do aluno
        this.aluno.setNome(this.nome.getText());
        /** nota 1 2 e 3*/
        DAO.getAluno().update(this.aluno);

        // atualizar a janela principal
        this.mainController.dataUpdate(this.aluno);

        //fechar o dialog
        this.dialogStage.close();
    }
}
