package com.cloud.computing.project.database;

public enum DataBaseType {
	ORIENTDB("ORIENTDB"),
	SQLITE("SQLITE");
	
    private final String val;       

    private DataBaseType( String s ) {
    	val = s;
    }
    
    public String toString() {
       return this.val;
    }
}
