<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
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
	<title>Lista de produtos</title>
</head>
<body>

	<div>
		<ul>
		  <li><a class="active" href="http://localhost:8080/Supermercado/Index.html">Home</a></li>
		  <li><a href="http://localhost:8080/Supermercado/TelaLogin.html">Login</a></li>
		  <li><a href="#cadastro">Cadastro</a></li>
		</ul>
	</div>


<h1 align="center">Lista de Produtos</h1>

  <%
    String msg = (String) request.getAttribute("msg");
  
    out.print(msg != null ? msg : "");
  %>
	<table id="customers">
			<tr>
			    <th>#</th>
				<th>Código</th>
				<th>Produto</th>
				<th>Ações</th>
				
			</tr>
		   <%
				List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
				for (int i = 0; i< produtos.size();i++){
			%>
			<tr>
 		      	<td align="center"> <%=i+1 %> </td>	
				<td align="center"> <a target="_blank" href="DetalhaProduto?codigo=<%=produtos.get(i).getCodigo() %>"><%=produtos.get(i).getCodigo() %></a>  </td>
				<td align="center"> <%=produtos.get(i).getDescricao() %> </td>
				<td align="center"> <a  href="ExcluiProduto?codigo=<%=produtos.get(i).getCodigo() %>">Excluir</a></td>
			</tr>
 			<%
		 	  }
 		%>
	</table>	

</body>
</html>