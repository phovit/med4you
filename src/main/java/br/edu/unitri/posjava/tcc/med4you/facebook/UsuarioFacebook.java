package br.edu.unitri.posjava.tcc.med4you.facebook;

import org.json.JSONObject;

public class UsuarioFacebook {

	private Long id; 
	private String firstName; 
	private Integer timezone; 
	private String email; 
	private Boolean verified;	
	private String middleName;	
	private String gender;	
	private String lastName;
	private String link;
	private String locale;	
	private String name;	
	private String updatedTime;
	
	public UsuarioFacebook(JSONObject jsonUsuario){
		
		String[] fields = JSONObject.getNames(jsonUsuario);
		
		for (String field : fields) {
			if (field.equals("id")) {
				id = jsonUsuario.getLong("id");
				continue;
			}
			if (field.equals("first_name")) {
				firstName = jsonUsuario.getString("first_name");
				continue;
			}
			if (field.equals("timezone")) {
				timezone = jsonUsuario.getInt("timezone");
				continue;
			}
			if (field.equals("email")) {
				email = jsonUsuario.getString("email");
				continue;
			}
			if (field.equals("verified")) {
				verified = jsonUsuario.getBoolean("verified");
				continue;
			}
			if (field.equals("middle_name")) {
				middleName = jsonUsuario.getString("middle_name");
			}
			if (field.equals("gender")) {
				gender = jsonUsuario.getString("gender");
				continue;
			}
			if (field.equals("last_name")) {
				lastName = jsonUsuario.getString("last_name");
				continue;
			}
			if (field.equals("link")) {
				link = jsonUsuario.getString("link");
				continue;
			}
			if (field.equals("locale")) {
				locale = jsonUsuario.getString("locale");
				continue;
			}
			if (field.equals("name")) {
				name = jsonUsuario.getString("name");
				continue;
			}
			if (field.equals("updated_time")) {
				updatedTime = jsonUsuario.getString("updated_time");
				continue;
			}
		}
	}

	public String getName() {
		return name;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "UsuarioFacebook [id=" + id + ", email=" + email + ", firstName=" + firstName
				+ ", timezone=" + timezone + ", verified="
				+ verified + ", middleName=" + middleName + ", gender="
				+ gender + ", lastName=" + lastName + ", link=" + link
				+ ", locale=" + locale + ", name=" + name + ", updatedTime="
				+ updatedTime + "]";
	}
	
}