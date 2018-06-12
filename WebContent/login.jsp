<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String title = "Fichadas"; %>    
<%@ include file = "/views/header.jsp" %>
</head>
<body>
<div class="container">
	<div class="row">
    <div class="col s12 m8 l6 offset-l3 z-depth-4 card-panel">
        <div class="row">
        	<div class="col l2">
        		<br>
        		<br>
        		<a class="btn-floating tooltipped btn-medium waves-effect waves-light blue lighten-2" data-position="bottom" data-tooltip="Volver!" href="index.jsp"><i class="material-icons">arrow_back</i></a>
        	</div>
          	<div class="col l8">
            	<img src="/SubeUnla/img/logo.jpg" alt="" class="responsive-img valign profile-image-login">
          	</div>
        </div>
        <div class="row margin">
          	<div class="input-field col s10 m8 l8 offset-l2">
            	<input placeholder="Usuario" id="usuario" type="text">
            	<label for="usuario">Usuario</label>
          	</div>
        </div>
        <div class="row margin">
        	<div class="input-field col s10 m8 l8 offset-l2">
           	 	<input placeholder="Shhhhh contraseña" id="password" type="password">
            	<label for="password">Contraseña</label>
          	</div>
        </div>
        <div class="row">
  			<div class="l4">
  				<button class="btn waves-effect waves-light blue lighten-2 black-text col s12 l12 m12" type="submit" id="login">Ingresar<i class="material-icons right">send</i>
	  			</button>
  			</div>
  		</div>
    </div>
  </div>
</div>

<script type="text/javascript" src="js/jsLogin.js"></script>
<%@ include file = "/views/footer.jsp" %>