package com.cloud.computing.project.database.type;

public enum DataBaseType {
	ORIENTDB("ORIENTDB"),
	ORACLE("ORACLE");
	
    private final String val;       

    private DataBaseType( String s ) {
    	val = s;
    }
    
    public String toString() {
       return this.val;
    }
}
