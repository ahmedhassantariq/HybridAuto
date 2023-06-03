package Functionality.Forms.OldControllerStuff;

import javafx.scene.Node;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BaseController<T> {
    private final FormInput[] inputNodes;

    public BaseController(Field[] fieldsToSet, Node... inputNodes) {
        this.inputNodes = new FormInput[fieldsToSet.length];
        for (int i = 0; i < this.inputNodes.length; i++) {
            this.inputNodes[i] = new FormInput<>(inputNodes[i], fieldsToSet[i]);
        }
    }

    public T createModel(T toCreate, Field[] additionalFields, Node[] additionalInputs) {
        FormInput[] additionalInputNodes = new FormInput[additionalFields.length];
        for (int i = 0; i < additionalInputNodes.length; i++) {
            additionalInputNodes[i] = new FormInput<>(additionalInputs[i], additionalFields[i]);
        }

        FormInput[] extended = Arrays.copyOf(this.inputNodes, this.inputNodes.length + additionalInputNodes.length);
        System.arraycopy(additionalInputNodes, 0, extended, this.inputNodes.length, additionalInputNodes.length);

        Object[] inputs = new Object[inputNodes.length + additionalInputs.length];
        Class<?>[] inputClasses = new Class[inputNodes.length + additionalInputs.length];


        for (int i = 0; i < extended.length; i++) {
            inputClasses[i] = extended[i].getFieldToSet().getType();
            inputs[i] = extended[i].getInput();

            if(inputClasses[i] != inputs[i].getClass()) {
                if(inputs[i] instanceof String str) {
                    inputs[i] = Integer.parseInt(str); // TODO: 6/2/2023 handle other input classes if used
                } else if(inputs[i] instanceof Integer intgr) {
                    inputs[i] = String.valueOf(intgr);
                }
            }
        }
        try {
            return (T) toCreate.getClass().getDeclaredConstructor(inputClasses).newInstance(inputs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public T createModel(T toCreate) {
        Object[] inputs = new Object[inputNodes.length];
        Class<?>[] inputClasses = new Class[inputNodes.length];

        for (int i = 0; i < this.inputNodes.length; i++) {
            inputClasses[i] = this.inputNodes[i].getFieldToSet().getType();
            inputs[i] = this.inputNodes[i].getInput();

            if(inputClasses[i] != inputs[i].getClass()) {
                if(inputs[i] instanceof String str) {
                    inputs[i] = Integer.parseInt(str); // TODO: 6/2/2023 handle other input classes if used
                } else if(inputs[i] instanceof Integer intgr) {
                    inputs[i] = String.valueOf(intgr);
                }
            }
        }
        try {
            return (T) toCreate.getClass().getDeclaredConstructor(inputClasses).newInstance(inputs);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
