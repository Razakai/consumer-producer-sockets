package model;

import java.io.Serializable;

public abstract class person implements Serializable {
	protected String name;
	
	public String getName() {
		return name;
	}

}
