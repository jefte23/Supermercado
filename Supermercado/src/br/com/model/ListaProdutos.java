package br.com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaProdutos
 */
@WebServlet("/ListaProdutos")
public class ListaProdutos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListaProdutos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obter objeto de resposta
		PrintWriter out = response.getWriter();

		// come�a a montar HTML
		out.println("<html><head>");
		out.println("<meta charset=\"utf-8\" />\r\n" + "\r\n"
				+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/Estilo.css\" />\r\n" + "\r\n"
				+ "<title>Login</title>\r\n" + "\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<div>\r\n" + "\r\n"
				+ "	\r\n" + "	<div>\r\n" + "		<ul>\r\n"
				+ "		  <li><a class=\"active\" href=\"http://localhost:8080/Supermercado/index.html\">Home</a></li>\r\n"
				+ "		  <li><a href=\"#login\">Login</a></li>\r\n"
				+ "		  <li><a href=\"http://localhost:8080/Supermercado/Cadastro.jsp\">Cadastro</a></li>\r\n"
				+ "		</ul>\r\n" + "	</div>\r\n" + "");

		// Conectar no banco de dados
		try {
			// Referenciar o driver JDBV
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";

			// Realizar conex�o com banco de dados
			Connection conexao = DriverManager.getConnection(url, username, password);

			// Criando o SQL - Jeito Ruim
			/*
			 * String sql = "SELECT nomeusuario, senhausuario FROM supermercado.usuario" +
			 * "WHERE nomeusuario = '" + usuario + "' and senhausuario = '" + senha + "'";
			 */

			// Criando o SQL - Jeito melhor
			String sql = "SELECT codigo, Descricao, fabricante, preco FROM supermercado.listaprodutos;";

			// preparar o SQL para envio ao BD
			PreparedStatement ps = conexao.prepareStatement(sql);

			// Executando o SQL
			ResultSet rs = ps.executeQuery();

			ArrayList<Produto> produtos = new ArrayList<Produto>();

			while (rs.next()) {

				Produto pr = new Produto(rs.getString("codigo"), rs.getString("Descricao"), rs.getString("fabricante"),
						rs.getFloat("preco"));

				produtos.add(pr);
			}

			request.setAttribute("produtos", produtos);

			// response.sendRedirect("ListaProdutos.jsp");
			getServletConfig().getServletContext().getRequestDispatcher("/ListaProdutos.jsp").forward(request,
					response);

			out.println("</body></html>");

			// Fechar o ResultSet
			rs.close();

			// Fechar o PrepareStatement
			ps.close();

			// Fechar o Connection
			conexao.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("N�o foi poss�vel estabelecer conex�o com o BD");
			// Mostra o erro
			e.printStackTrace();
		}

	}

}
