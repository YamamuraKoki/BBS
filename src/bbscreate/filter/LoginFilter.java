package bbscreate.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbscreate.beans.User;
import bbscreate.service.UserService;

@WebFilter(urlPatterns = { "/home", "/article", "/setting", "/managment", "/newuser" })
public class LoginFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			User loginUser =(User)((HttpServletRequest) request).getSession().getAttribute("loginUser");
			User user = null;
			if(loginUser != null) {
				user = new UserService().getUser(loginUser.getId());
			}
			if(user == null){
				HttpSession session = ((HttpServletRequest)request).getSession();
				session.setAttribute("Messages", "ログインして下さい");
				((HttpServletResponse)response).sendRedirect("login");
				return;
			}
			if(user != null && Boolean.valueOf(user.getUserState() == false)) {
				HttpSession session = ((HttpServletRequest)request).getSession();
				session.setAttribute("Messages", "ロックがかかったためにログインできません。"
						+ "管理者にお問い合わせください。");
				((HttpServletResponse)response).sendRedirect("login");
				return;
			}

			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig cofig) throws ServletException {
	}

}
