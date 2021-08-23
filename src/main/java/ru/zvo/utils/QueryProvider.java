package ru.zvo.utils;

import java.util.ResourceBundle;

public class QueryProvider {

    private ResourceBundle resourceBundle;

    public QueryProvider(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }
}
