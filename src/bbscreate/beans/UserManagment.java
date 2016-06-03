package bbscreate.beans;

import java.io.Serializable;

public class UserManagment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String name;
	private String branchName;
	private String positionName;
	private boolean userState;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public boolean getUserState() {
		return userState;
	}

	public void setUserState(boolean userState) {
		this.userState = userState;
	}

}