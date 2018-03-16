
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
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
		String usuario = request.getParameter("nameusuario");
		String senha = request.getParameter("senha");

		// Conectar no banco de dados

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
			String sql = "SELECT nomeusuario, senhausuario FROM supermercado.usuario WHERE nomeusuario = ? and senhausuario = ?";

			// preparar o SQL para envio ao BD
			PreparedStatement ps = conexao.prepareStatement(sql);

			// Passar o valor de usuario
			ps.setString(1, usuario);
			// Passar o valor de senha
			ps.setString(2, senha);

			// Executando o SQL
			ResultSet rs = ps.executeQuery();

			// Obter objeto de resposta
			PrintWriter out = response.getWriter();

			// começa a montar HTML
			out.println("<html><head><title>Login</title></head>");
			out.println("<body>");

			// Verificar se usuario = senha
			if (rs.first()) {
				out.println("<h1> Login com sucesso</h1>");
			} else {
				out.println("<h1>Login sem sucesso</h1>");
			}
			out.println("</body></html>");

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
