package Functionality.Forms;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;

public class FormInput<T> {
    private final Node inputNode;
    private final Field fieldToSet;

    public FormInput(Node inputNode, Field fieldToSet) {
        this.inputNode = inputNode;
        this.fieldToSet = fieldToSet;
    }

    public Node getInputNode() {
        return inputNode;
    }

    public Field getFieldToSet() {
        return fieldToSet;
    }

    public T getInput() {
        return getInput(inputNode);
    }

    private static <T> T getInput(Node inputNode) {
        if(inputNode instanceof TextField tf)
            return (T) tf.getText();
        if(inputNode instanceof ComboBox<?> cb)
            return (T) cb.getSelectionModel().getSelectedItem();
        return null;
    }
}
