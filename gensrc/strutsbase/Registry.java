package strutsbase;

// Generated 26 janv. 2012 14:00:05 by Hibernate Tools 3.4.0.CR1

/**
 * Registry generated by hbm2java
 */
public class Registry implements java.io.Serializable {

	private Integer registryId;
	private String label;
	private Integer scope;
	private String scopeKey;
	private String value;

	public Registry() {
	}

	public Registry(String label, Integer scope, String scopeKey, String value) {
		this.label = label;
		this.scope = scope;
		this.scopeKey = scopeKey;
		this.value = value;
	}

	public Integer getRegistryId() {
		return this.registryId;
	}

	public void setRegistryId(Integer registryId) {
		this.registryId = registryId;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getScope() {
		return this.scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public String getScopeKey() {
		return this.scopeKey;
	}

	public void setScopeKey(String scopeKey) {
		this.scopeKey = scopeKey;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
