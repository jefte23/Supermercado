package br.com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ExcluiProduto
 */
@WebServlet("/ExcluiProduto")
public class ExcluiProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcluiProduto() {
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

		String msg = " OK !";

		// começa a montar HTML
		out.println("<html><head>");
		out.println("<meta charset=\"utf-8\" />\r\n" + "\r\n"
				+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/Estilo.css\" />\r\n" + "\r\n"
				+ "<title>Login</title>\r\n" + "\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<div>\r\n" + "\r\n"
				+ "	\r\n" + "	<div>\r\n" + "		<ul>\r\n"
				+ "		  <li><a class=\"active\" href=\"http://localhost:8080/Supermercado/Index.html\">Home</a></li>\r\n"
				+ "		  <li><a href=\"http://localhost:8080/Supermercado/TelaLogin.html\">Login</a></li>\r\n"
				+ "		  <li><a href=\"#cadastro\">Cadastro</a></li>\r\n" + "		</ul>\r\n" + "	</div>\r\n" + "");

		String codigo = request.getParameter("codigo");

		try {
			// Referenciar o driver JDBV
			Class.forName("com.mysql.jdbc.Driver");// TODO
			String url = "jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";

			// Realizar conexão com banco de dados
			Connection conexao = (Connection) DriverManager.getConnection(url, username, password);

			// Criando o SQL - Jeito melhor
			String sql = "DELETE FROM supermercado.listaprodutos WHERE codigo = ?";

			// preparar o SQL para envio ao BD
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, codigo);

			System.out.println(ps.executeUpdate());

			// // Executando o SQL
			// ResultSet rs = ps.executeQuery();
			//
			// ArrayList<Produto> produtos = new ArrayList<Produto>();
			// while (rs.next()) {
			//
			// Produto pr = new Produto(rs.getString("codigo"), rs.getString("Descricao"),
			// rs.getString("fabricante"),
			// rs.getFloat("preco"));
			//
			// produtos.add(pr);
			// }

			// String sqlDelet = "DELETE FROM supermercado.listaprodutos WHERE codigo = ?";
			// // preparar o SQL para envio ao BD
			// PreparedStatement psDelet = (PreparedStatement)
			// conexao.prepareStatement(sqlDelet);
			// psDelet.setString(1, codigo);

			// ps.executeUpdate(sqlDelet);

			// rsDelet.close();
			// psDelet.close();

			out.println("</body> " + codigo + "</html>");

			// Fechar o ResultSet
			// rs.close();

			// Fechar o PrepareStatement
			ps.close();

			// Fechar o Connection
			conexao.close();

			request.setAttribute("msg", msg);
			getServletConfig().getServletContext().getRequestDispatcher("/ListaProdutos").forward(request, response);
		} catch (Exception e) {
			msg = "ERRO";
			request.setAttribute("msg", msg);
			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/ListaProdutos").forward(request, response);

		}
		out.println("</body></html>");
	}
}