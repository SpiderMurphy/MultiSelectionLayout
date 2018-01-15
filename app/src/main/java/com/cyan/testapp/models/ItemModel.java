package com.cyan.testapp.models;

/**
 * Created by Cyan on 15/01/2018.
 */

public class ItemModel {
    private int m_id;
    private String m_desc;

    public ItemModel(int id, String desc) {
        this.m_id = id;
        this.m_desc = desc;
    }

    public int get_id() {
        return m_id;
    }

    public void set_id(int id) {
        this.m_id = id;
    }

    public String get_desc() {
        return m_desc;
    }

    public void set_desc(String desc) {
        this.m_desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;

        ItemModel itemModel = (ItemModel) o;

        if (m_id != itemModel.m_id) return false;
        return m_desc != null ? m_desc.equals(itemModel.m_desc) : itemModel.m_desc == null;
    }

    @Override
    public int hashCode() {
        int result = m_id;
        result = 31 * result + (m_desc != null ? m_desc.hashCode() : 0);
        return result;
    }
}
