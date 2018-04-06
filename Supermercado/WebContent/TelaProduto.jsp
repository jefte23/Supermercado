<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="br.com.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta charset="utf-8" />

	<link rel="stylesheet" type="text/css" href="css/Estilo.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Produtos</title>
</head>
<body>

	<div>
		<ul>
		  <li><a class="active" href="http://localhost:8080/Supermercado/Index.html">Home</a></li>
		  <li><a href="http://localhost:8080/Supermercado/TelaLogin.html">Login</a></li>
		  <li><a href="#cadastro">Cadastro</a></li>
		</ul>
	</div>


<h1 align="center">Produtos</h1>
 	
 		<table id="customers">
			<tr>
				<th>Codigo</th>
				<th>Fabricante</th>
				<th>Produto</th>
				<th>Preço</th>
				
			</tr>
 		 	<%
	 		 	
 		 		List<Produto> produtos = (List<Produto>) request.getAttribute("produto");
				for (int i = 0; i< produtos.size();i++){
					
							 	
		    	//Produto produto = (Produto) request.getAttribute("produto");
		    
 		 	%> 
		     
      		<tr>
		
				<td align="center"> <%=produtos.get(i).getCodigo() %> </td>
				<td align="center"> <%=produtos.get(i).getFabricante() %> </td>
				<td align="center"> <%=produtos.get(i).getDescricao() %> </td>
				<td align="center"> <%=produtos.get(i).getPreco() %> </td>
		
			</tr>
			
			<%
				}
			%>
		</table>
		
</body>
</html>