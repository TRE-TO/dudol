<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><g:layoutTitle default="Dudol"/></title>

    <!-- Bootstrap core CSS -->
    <asset:stylesheet href="bootstrap.min.css"/>

    <style>
      body {
        min-height: 2000px;
        padding-top: 70px;
      }
      .buttons {
        padding: 20px;
      }
    </style>
	<g:layoutHead/>
	<r:layoutResources />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<g:createLink uri='/'/>">Dudol</a>
        </div>
        <div class="navbar-collapse collapse">
            <g:if test="${session.logado}">
              <ul class="nav navbar-nav" >

                 <li <g:if test="${controllerName == 'emailAdmin'}">class="active"</g:if>><a href="<g:createLink controller="EmailAdmin"/>">Emails</a></li>
                <li <g:if test="${controllerName == 'schedule'}">class="active"</g:if>><a href="<g:createLink controller="Schedule"/>"><g:message code="top.schedules"/></a></li>


              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li ><a href="/login/logout">Sair</a></li>

              </ul>
            </g:if>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      
		<g:layoutBody/>
    
    </div>
    <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>


	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	<g:javascript library="application"/>
	<r:layoutResources />
  </body>
</html>