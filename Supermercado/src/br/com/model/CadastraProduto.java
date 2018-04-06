package br.com.model;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class CadastraProduto
 */
@WebServlet("/CadastraProduto")
public class CadastraProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastraProduto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperar parametros
		String codigo = request.getParameter("codigo");
		String descricao = request.getParameter("Descricao");
		String fabricante = request.getParameter("fabricante");
		float preco = Float.parseFloat(request.getParameter("preco"));

		try {
			// Referenciar o driver JDBV
			Class.forName("com.mysql.jdbc.Driver");// TODO
			String url = "jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";

			// Realizar conexão com banco de dados
			Connection conexao = (Connection) DriverManager.getConnection(url, username, password);

			// Criando o SQL - Jeito melhor
			String sql = "INSERT INTO supermercado.listaprodutos(codigo,Descricao,fabricante,preco) VALUES(?,?,?,?)";

			// preparar o SQL para envio ao BD
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ps.setString(1, codigo);
			ps.setString(2, descricao);
			ps.setString(3, fabricante);
			ps.setFloat(4, preco);

			System.out.println(ps.executeUpdate());

			// Fechar o Cps
			ps.close();

			// Fechar o Connection
			conexao.close();

			getServletConfig().getServletContext().getRequestDispatcher("/ListaProdutos").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			getServletConfig().getServletContext().getRequestDispatcher("/ListaProdutos").forward(request, response);

		}
	}

}
