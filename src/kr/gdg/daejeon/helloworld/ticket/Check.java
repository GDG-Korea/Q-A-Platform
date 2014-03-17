package kr.gdg.daejeon.helloworld.ticket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Check() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		JSONObject result = new JSONObject();
		PrintWriter out = response.getWriter();
		String callBack = request.getParameter("callback");
		String type = request.getParameter("type");

		if (type.equals("login")) {
			String user = request.getParameter("user");
			request.getSession().setAttribute("user", user);
			result.put("user", user).put("code", true);
		} else if (type.equals("check")) {
			if (request.getSession() == null || request.getSession().getAttribute("user") == null
					|| request.getSession().getAttribute("user").toString().length() == 0)
				result.put("code", false);
			else
				result.put("code", true).put("user", request.getSession().getAttribute("user"));

		} else if (type.equals("logout")) {
			request.getSession().invalidate();
			result.put("code", false);
		} else {
			result.put("code", false);
		}

		out.write(callBack + "(");
		out.print(result);
		out.print(")");

	}
}
