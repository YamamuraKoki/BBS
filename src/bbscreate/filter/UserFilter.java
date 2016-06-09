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

@WebFilter(urlPatterns = { "/managment", "/setting", "/newuser" })
public class UserFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			User user =(User)((HttpServletRequest) request).getSession().getAttribute("loginUser");

				if(user.getPosition() != 2){
				HttpSession session = ((HttpServletRequest)request).getSession();
				session.setAttribute("Messages", "権限がないためにホーム画面に移動します");
				((HttpServletResponse)response).sendRedirect("home");

				return;
			}
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig cofig) throws ServletException {
	}

}
