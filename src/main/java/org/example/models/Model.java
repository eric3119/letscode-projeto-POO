package org.example.models;

public abstract class Model {
    private int id;

    public int getId() {
        return id;
    }

    public <T extends Model> T setId(int id) {
        this.id = id;

        @SuppressWarnings("unchecked")
        T ret = (T) this;

        return ret;
    }
}
