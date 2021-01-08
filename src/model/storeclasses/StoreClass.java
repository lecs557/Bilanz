package model.storeclasses;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class StoreClass {

    private String id;
    protected String tableName;
    protected String choiceBoxMethodName;
    protected ArrayList<FieldName> fieldNames = new ArrayList<>();
    protected ArrayList<ForeignKey<? extends StoreClass>> foreignKeys = new ArrayList<>();

    public StoreClass() {
        fieldNames.add(FieldName.storeId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChoiceBoxMethodName() {
        return choiceBoxMethodName;
    }

    public String getTableName() {
        return tableName;
    }

    public ArrayList<ForeignKey<? extends StoreClass>> getForeignKeys() {
        return foreignKeys;
    }

    public ArrayList<FieldName> getFieldNames() {
        return fieldNames;
    }

}
