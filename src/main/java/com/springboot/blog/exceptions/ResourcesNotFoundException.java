package com.springboot.blog.exceptions;

public class ResourcesNotFoundException extends RuntimeException {

	private String resourcesName;
	private String fieldName;
	private long fieldValue;

	public ResourcesNotFoundException(String resourcesName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourcesName, fieldName, fieldValue));
		this.resourcesName = resourcesName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

}
