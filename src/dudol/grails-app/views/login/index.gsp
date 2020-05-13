<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'login.label', default: 'Login')}" />
		<title>Login</title>
	</head>
	<body>

		<div class="container">
        <g:if test="${flash.message}">
			<div class="alert alert-danger">${flash.message}</div>
		</g:if>

            <div class="row">
             <div class="col-md-3">
             </div>
                <div class="col-md-6">


                    <div class="panel panel-default">

                        <div class="panel-heading">Acesso ao Sistema</div>

                        <div class="panel-body">
                        <form  id="form1"   METHOD="post" >


                            <div class="form-group" id="search"  >
                                <label for="">Login</label>
                                <div  id="search">
                                    <input type="text" name="login" id="login"  class="  form-control"  placeholder="digite aqui">
                                </div>
                            </div>
                                <div class="form-group" id="search"  >
                                    <label for=""> Senha</label>
                                    <div  id="search">
                                        <input type="password" name="senha" id="senha"  class="  form-control"  placeholder="digite aqui">
                                    </div>
                                </div>

                                <div class="form-group " style="padding-top: 20px" >
                                    <input type="submit" class="btn btn-info" value="Entrar" >
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

	</body>
</html>