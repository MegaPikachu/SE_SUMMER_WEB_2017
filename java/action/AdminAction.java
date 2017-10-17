package action;

import java.util.List;

import model.Admin;
import service.AdminService;

public class AdminAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Admin admin;
	private AdminService adminService;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/* methods */
	public String login() {
		Boolean b = adminService.check(admin);
		if (b){
			session().setAttribute("admin", admin.getUsername());
			return SUCCESS;
		}
		else {
			addFieldError("passwd", "用户名或密码错误!");
			return "login";
		}
	}

	public String getAll() {
		List<Admin> admins = adminService.getAll();
		return "success";
	}
	
	public String logout() {
		session().removeAttribute("admin");
		return "login";
	}

}
