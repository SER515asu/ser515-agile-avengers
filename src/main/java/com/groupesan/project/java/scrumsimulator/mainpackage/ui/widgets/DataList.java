package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class DataList<T> extends JList<T> implements BaseComponent {
    private final DataModel<List<T>> items;
    private final DefaultListModel<T> listModel;

    public DataList(DataModel<List<T>> items) {
        this.items = items;
        this.listModel = new DefaultListModel<>();
        this.init();
    }

    @Override
    public void init() {
        setModel(listModel);
        for (T item : items.getData()) {
            listModel.addElement(item);
        }
        if (listModel.getSize() > 0) {
            setSelectedIndex(0);
            ensureIndexIsVisible(0);
        }
    }

    public void addItem(T item) {
        this.listModel.addElement(item);
        List<T> data = new ArrayList<>(this.items.getData());
        data.add(item);
        this.items.setData(data);
        setSelectedIndex(listModel.size() - 1);
    }

    public void removeItem(int index) {
        this.listModel.remove(index);
        List<T> data = new ArrayList<>(this.items.getData());
        data.remove(index);
        this.items.setData(data);
        if (index == listModel.getSize()) {
            index--;
        }
        setSelectedIndex(index);
        ensureIndexIsVisible(index);
    }

    public void editItem(int index, T editedItem) {
        this.listModel.set(index, editedItem);
        List<T> data = new ArrayList<>(this.items.getData());
        data.set(index, editedItem);
        this.items.setData(data);
    }

    public void removeSelectedItem() {
        int index = getSelectedIndex();
        if (index == -1) {
            return;
        }
        this.removeItem(index);
    }

    public int itemCount() {
        return listModel.getSize();
    }
}
