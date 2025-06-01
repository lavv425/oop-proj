package com.devtrack.model.abstracts;

public abstract class Tool {
    protected String name;
    protected String version;

    public Tool(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public abstract boolean isInstalled();

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}