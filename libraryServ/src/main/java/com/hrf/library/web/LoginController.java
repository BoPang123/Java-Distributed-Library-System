package com.hrf.library.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csn.dao.ClientDao;
import static com.hrf.common.DesUtil.decrypt;
import com.hrf.common.Result;
// import com.hrf.common.EncryptionTool;
import com.hrf.library.entity.LoginUser;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 2222L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Result result = null;
		try {
                    LoginUser user = new LoginUser();
                    user.setName( decrypt(request.getParameter("name")));
                    user.setPassword(decrypt(request.getParameter("password")));
                    user.setSecretKey(request.getParameter("secretKey"));
                    System.out.println("userName:" + user.getName() + "  password:" + user.getPassword() + "  secretKey:"
				+ user.getSecretKey());

			// 声明工具类对象，对密文password解密
			//EncryptionTool encryptionTool = new EncryptionTool();
			// clearPassword 解密后的密码
			//String decryptpwd = EncryptionTool.decryption(user.getPassword(), user.getSecretKey());
			//String decryptname = EncryptionTool.decryption(user.getName(), user.getSecretKey());
			//System.out.println(decryptname);
			user.setRoleId("9");
			user.setToken("1111");
			ClientDao cd = new ClientDao();
			Boolean match = cd.checkClient(user.getName(), user.getPassword()); // cd.checkClient(decryptname, decryptpwd);
			//match = true;
			if (match) {
				System.out.println("Match");
				result = Result.success(user);
			} else {
				System.out.println("NotMatch");
				result = Result.error("Wrong password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error("Server Internal Error .");
		}
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(result.toString().getBytes("utf-8"));
	}
}
