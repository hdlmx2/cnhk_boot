package store.cnhk.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] notFilter = new String[]{"/cnhkManager/page/login/login.html", "/cnhkManager/login"};
        String uri = request.getRequestURI();
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1 || uri.contains(".css") || uri.contains(".js") || uri.contains(".png") || uri.contains(".jpg") || uri.contains(".ttf") || uri.contains(".woff")) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter == true) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("userName");
            if (null == obj) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.setCharacterEncoding("UTF-8");
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    return;
                }
                response.sendRedirect("/cnhkManager/login");
                return;
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
}
