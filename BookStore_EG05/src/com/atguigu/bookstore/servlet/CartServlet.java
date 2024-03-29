package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 处理用户和购物车相关的请求
 */
public class CartServlet extends BaseServlet {
	
	private BookService service = new BookServiceImpl();
	//处理清空购物车的请求
	protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用浏览器会话对应的购物车对象的方法处理业务
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();
		//给用户响应：回到修改之前的页面
		response.sendRedirect(request.getHeader("referer"));
	}
	
	
	
	
	
	//修改指定购物项数量的请求
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数
		String bookId = request.getParameter("bookId");
		String totalCount = request.getParameter("totalCount");
		//调用浏览器会话对应的购物车对象的方法处理业务
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateCartItemCountByBookId(bookId, totalCount);
		//给用户响应：回到修改之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}
	
	
	//处理用户删除指定购物项的请求
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取要删除购物项的 图书id
		String bookId = request.getParameter("bookId");
		//2、获取本次会话浏览器对应的购物车对象
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//3、调用cart的方法处理删除业务
		cart.deleteCartItemByBookId(bookId);
		//4、给用户响应： 跳转回之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}
	
	
	//处理用户添加图书到购物车中的请求
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取图书id
		String bookId = request.getParameter("bookId");
		//查询id对应的图书对象
		Book book = service.findBook(bookId);
		//添加图书到购物车中
		//获取本次会话对应的购物车对象
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null) {
			//浏览器本次会话如果第一次使用购物车需要新创建并存入到session中
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//如果之前已经使用过购物车，从session中获取 ， 需要调用购物车方法直接将图书添加到购物车中
		cart.addBook2Cart(book);
		//将最新添加的商品信息保存到session域中，用来给添加购物车操作提示
		session.setAttribute("title", book.getTitle());
		session.setAttribute("totalCount", cart.getTotalCount());
		
		
		//给浏览器响应:跳转回添加之前的页面
		response.sendRedirect(request.getHeader("referer"));
		
	}

}
