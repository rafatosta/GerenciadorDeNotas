package com.uefs.gerenciadordenotas.components;

import com.uefs.gerenciadordenotas.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.File;

public abstract class CustomIconButton implements Callback<TableColumn<Object, Object>, TableCell<Object, Object>> {

    private String iconName;

    public CustomIconButton(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public TableCell<Object, Object> call(TableColumn<Object, Object> arg0) {
        // cria nova célula personalizada
        TableCell<Object, Object> cell = new TableCell<Object, Object>() {

            // sobreescreve um método de TableCell apenas para o objeto a ser construído
            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                // A variável item possui o valor enviado pelo setCellValueFactory
                if (empty) {
                    setGraphic(null);
                    setText(null);
                    return;
                }
                // cria o botão javaFX
                Button btn = new Button();
                Image img = new Image(Main.class.getResource("img" + File.separator + iconName).toExternalForm());
                btn.setGraphic(new ImageView(img));
                btn.setStyle("-fx-background-color: transparent;");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // aqui será executado o evento ao ser precionado
                        actionRun(item);
                    }
                });

                if (item == null) {
                    btn.setDisable(true);
                }
                setGraphic(btn);
                setText(null);
            }
        };
        return cell;
    }

    public abstract void actionRun(Object item);
}
