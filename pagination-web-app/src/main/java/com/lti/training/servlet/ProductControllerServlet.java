package com.lti.training.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.training.dao.ProductDao;
import com.lti.training.model.Product;

/**
 * Servlet implementation class ProductControllerServlet
 */
public class ProductControllerServlet extends HttpServlet {
	   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int pageSize=3;
   	  
   	HttpSession session = request.getSession();
  	Integer currentPosition=(Integer)session.getAttribute("cp");
  	if(currentPosition==null)
  	currentPosition = 1;

   	  
   	  String go = request.getParameter("go");
   	  if(go!=null) {
   		  if(go.equals("next"))
   			  currentPosition+=pageSize;
   		  else if(go.equals("prev"))
   			  currentPosition-=pageSize;
   			  }
   	  else 
   		   currentPosition=1;
   		   
   		   session.setAttribute("cp", currentPosition);
   		   
   		   ProductDao dao= new ProductDao();
   		   List<Product> products = dao.fetchProducts(currentPosition,currentPosition+pageSize);
   	  
   		   request.setAttribute("currentProducts", products);
   		   
   		   RequestDispatcher dispatcher = request.getRequestDispatcher("viewProduct.jsp");
   		   dispatcher.forward(request, response);
   		   
   	  

}
   	}
