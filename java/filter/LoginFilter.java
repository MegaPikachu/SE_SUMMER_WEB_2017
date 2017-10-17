package filter;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  


public class LoginFilter implements Filter {  
      
    public FilterConfig config;  
      
    public void destroy() {  
    }  
      
    public void doFilter(ServletRequest request, ServletResponse response,
    		   FilterChain chain) throws IOException, ServletException {
    		    
    		  HttpServletRequest httpreq = (HttpServletRequest) request;
    		  HttpServletResponse httpres = (HttpServletResponse) response;
    		  
    		  ((HttpServletResponse)response).sendRedirect("http://115.159.74.217:8080/bzbp/login");
    		     		     			      		  
    }
    		 

   
  
    public void init(FilterConfig filterConfig) throws ServletException {  
    }  
}  