package com.cyan.multiselectionlayout;

/**
 * Created by Cyan on 14/01/2018.
 */

public interface MultiSelectionEvents {
    /**
     * Add item to selection set
     *
     * @param item
     * item to be added
     */
    void add(Object item);

    /**
     * Remove item from selection set
     *
     * @param item
     * item to be removed
     */
    void remove(Object item);

    /**
     * Notify selection modo switch on MultiSelectionLayout
     *
     * @param active
     * activation flag
     */
    void notifySelectionMode(boolean active);
}
