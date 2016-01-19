package isen.study.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.MenuItem;

/**
 * isen.study.listeners Created by Pierre-Alexandre Adamski on 18/01/2016.
 */
public abstract class StatChangeListener implements ChangeListener<MenuItem> {
	@Override
	public void changed(ObservableValue<? extends MenuItem> observable, MenuItem oldValue, MenuItem newValue) {
		handleNewItemSelected(newValue);
	}

	public abstract void handleNewItemSelected(MenuItem menuItem);
}
