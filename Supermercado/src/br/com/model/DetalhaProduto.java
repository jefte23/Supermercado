package br.com.model;

import java.io.IOException;
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
 * Servlet implementation class DetalhaProduto
 */
@WebServlet("/DetalhaProduto")
public class DetalhaProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetalhaProduto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codigo = request.getParameter("codigo");

		System.out.println(" codigo : " + codigo);

		try {
			// Referenciar o driver JDBV
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";

			// Realizar conexão com banco de dados
			Connection conexao = DriverManager.getConnection(url, username, password);

			// Criando o SQL - Jeito Ruim
			/*
			 * String sql = "SELECT nomeusuario, senhausuario FROM supermercado.usuario" +
			 * "WHERE nomeusuario = '" + usuario + "' and senhausuario = '" + senha + "'";
			 */

			// Criando o SQL - Jeito melhor
			String sql = "SELECT * FROM supermercado.listaprodutos WHERE codigo = ?";

			// preparar o SQL para envio ao BD
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, codigo);

			// Executando o SQL
			ResultSet rs = ps.executeQuery();

			ArrayList<Produto> produtos = new ArrayList<Produto>();
			while (rs.next()) {

				Produto pr = new Produto(rs.getString("codigo"), rs.getString("Descricao"), rs.getString("fabricante"),
						rs.getFloat("preco"));

				produtos.add(pr);
			}

			request.setAttribute("produto", produtos);
			// response.sendRedirect("ListaProdutos.jsp");
			getServletConfig().getServletContext().getRequestDispatcher("/TelaProduto.jsp").forward(request, response);

			// Fechar o ResultSet
			rs.close();

			// Fechar o PrepareStatement
			ps.close();

			// Fechar o Connection
			conexao.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Não foi possível estabelecer conexão com o BD");
			// Mostra o erro
			e.printStackTrace();
		}
	}

}
