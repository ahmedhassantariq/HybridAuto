package Screens;

import javafx.scene.Node;

import java.lang.reflect.Field;

public interface InputForm { //interface to go with BaseController stuff
    public Node[] getInputs();
    public Field[] getFieldsToSet();
}
