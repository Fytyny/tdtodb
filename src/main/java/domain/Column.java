package domain;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public class Column {
    private String name;
    private String type;
    private boolean isGenerated = false;
    private boolean isPrimaryKey = false;

    public Column(String name, String type){
        this.name = name;
        this.type = type;
    }

    public Column(String name, String type, boolean isG, boolean isPrim){
        this.name = name;
        this.type = type;
        this.isGenerated = isG;
        this.isPrimaryKey = isPrim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isGenerated() {
        return isGenerated;
    }

    public void setGenerated(boolean generated) {
        isGenerated = generated;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
