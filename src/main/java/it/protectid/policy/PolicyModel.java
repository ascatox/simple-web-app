package it.protectid.policy;

import java.util.*;

public class PolicyModel {
	private List<Attribute> attributes;

	public PolicyModel() {
		this.attributes = new ArrayList<>();
	}

	public PolicyModel(List<Attribute> attributeList) {
		this.attributes = attributeList;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<String> getAttributeKeys() {
		Map keyMap = new HashMap<String, String>();
		for (Attribute attribute :
			this.getAttributes()) {
			keyMap.put(attribute.getName(), attribute.getName());
		}
		return keyMap.keySet();
	}

	public static class Attribute {
		private String name;
		private String value;
		private boolean confirm;

		public Attribute(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isConfirm() {
			return confirm;
		}

		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}
	}
}
