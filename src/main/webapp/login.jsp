<%--
  Created by IntelliJ IDEA.
  User: kayrs
  Date: 03/06/2023
  Time: 02:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pet Topia - Seu melhor PetShop!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark sticky-top border-bottom" data-bs-theme="dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvas" aria-controls="#offcanvas" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="#offcanvas" aria-labelledby="#offcanvasLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="#offcanvasLabel">Aperture</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav flex-grow-1 justify-content-between">
                    <li class="nav-item"><a class="nav-link" href="#">Início</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Serviços</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Produtos</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Agendamentos</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">FAQs</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>


<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-3">
                            <h2 class="fw-bold mb-2 text-uppercase">Iniciar Sessão</h2>
                            <p class="text-white-50 mb-5">Por favor, insira seu login e senha para iniciar sessão.</p>
                            <form method="POST" action="login">
                                <div class="form-outline form-white mb-4">
                                    <input type="text" id="login" name="login" class="form-control form-control-lg" />
                                    <label class="form-label" for="login">Login</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="senha" name="senha" class="form-control form-control-lg" />
                                    <label class="form-label" for="senha">Senha</label>
                                </div>

                                <p class="small mb-5 pb-lg-1"><a class="text-white-50" href="#!">Esqueceu sua senha?</a></p>

                                <div id="loginStatus" class="alert " role="alert">

                                    <c:if test="${loginSucessful}">
                                        Login bem-sucedido.
                                        <script>document.getElementById('loginStatus').classList.add('alert-success')</script>
                                    </c:if>

                                    <c:if test="${loginIncorreto}">
                                        Usuário ou senha incorretos.
                                        <script>document.getElementById('loginStatus').classList.add('alert-danger')</script>
                                    </c:if>
                                </div>

                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Entrar</button>

                            </form>

                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                            </div>
                        </div>

                        <div>
                            <p class="mb-0">Ainda não tem uma conta? <a href="cadastrar.jsp" class="text-white-50 fw-bold">Cadastrar</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>