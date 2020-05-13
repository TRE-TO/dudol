
<%@ page import="br.treto.dudol.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<title>Email</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="create" action="create"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Novo Email</g:link></p>
		</div>
		<div class="page-header">
			<h1>Email <small>Listagem</small></h1>
		</div>

		<div id="list-schedule" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-hover">
			<thead>
					<tr>

					    <th>Ord.</th>
						<th>Host</th>
						<th>Usuário</th>
						<th>Porta</th>
						<th>SSL</th>
						<th>Qtde Máxima/dia</th>
						<th>Util. hoje</th>
						<th>Ação</th>
					

					</tr>
				</thead>
				<tbody>
				<g:each in="${lista}" status="i" var="obj">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>

                             <g:if test="${lista.size() > 1 && i > 0}">
						        <g:link class="create" action="up" id="${obj.id}"><span class="glyphicon glyphicon-arrow-up"></span></g:link>
						      </g:if>
						       <g:if test="${lista.size() > 1 && i < lista.size()-1}">
						        <g:link class="create" action="down" id="${obj.id}"><span class="glyphicon glyphicon-arrow-down"></span></g:link>
						       </g:if>
						</td>
					
						<td>${fieldValue(bean: obj, field: "host")}</td>
					
						<td>${fieldValue(bean: obj, field: "username")}</td>

						<td>${fieldValue(bean: obj, field: "port")}</td>

						<td>${fieldValue(bean: obj, field: "ssl")}</td>

						<td>${fieldValue(bean: obj, field: "qtdeMaxima")}</td>

						<td>${obj.envios[0]?.quantidade}</td>

						<td align='center'>

						    <g:link action="edit" id="${obj.id}">Editar</g:link> |
						     <g:link action="excluir" id="${obj.id}">Excluir</g:link>
						</td>


					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lista ?: 0}" />
			</div>
		</div>
	</body>
</html>
