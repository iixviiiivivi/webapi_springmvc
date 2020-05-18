package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Member {
	
	private Integer id;
	
	@NotNull(message = "name required")
	@Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5]+$", message = "name必須是中英文字母")
	private String name;
	
	@NotNull(message = "username required")
	@Pattern(regexp = "^\\w{4,20}$", message = "username必須是4~20個英數文字元包括底線")
	private String username;
	
	@NotNull(message = "password required")
	@Pattern(regexp = "^[A-Z]{1}\\w{3,19}$", message = "password必須是4~20個英數文字元包括底線，開頭必須是英文字母大寫")
	private String password;
	
	@NotNull(message = "registerDate is required")
	@Pattern(regexp = "^\\d{4}(/?\\d{2}){1}(/?\\d{2}){1}$", message = "registerDate格式必須是YYYY/MM/DD")
	private String registerDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
}
