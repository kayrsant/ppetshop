<%--
  Created by IntelliJ IDEA.
  User: Aluno
  Date: 15/06/2023
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pet Topia - Bem-vindo ao melhor PetShop!</title>
    <link href="resources/bootstrap-5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:out value="${sessionScope.nomeUsuario}" />
                    </button>
                    <ul class="dropdown-menu">
                        <li>
                            <form action="logout" method="post">
                                <button class="dropdown-item" type="submit">Sair</button>
                            </form>
                        </li>
                    </ul>
                </div>
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
                            <h2 class="fw-bold mb-2 text-uppercase">Agendar Serviço</h2>
                            <p class="text-white-50 mb-5">Por favor, selecione o pet, o serviço e a data para agendamento.</p>
                            <form method="POST" action="agendar">
                                <input type="hidden" id="idUsuario" name="idUsuario" value="${sessionScope.idUsuario}">
                                <div class="form-outline form-white mb-4">
                                    <select class="form-control form-control-lg" name="pet">
                                        <option value="">Selecione um pet</option>
                                        <c:forEach var="pet" items="${pets}">
                                            <option value="${pet.id}">${pet.nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <select class="form-control form-control-lg" name="servico">
                                        <c:forEach var="servico" items="${servicos}">
                                            <option value="${servico.id}">${servico.nome}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="datetime-local" id="dataagendamento" name="dataagendamento" class="form-control form-control-lg" />
                                    <label class="form-label" for="dataagendamento">Data</label>
                                </div>

                                <div id="agendarStatus" class="alert " role="alert">

                                    <c:if test="${loginSucessful}">
                                        Login bem-sucedido.
                                        <script>document.getElementById('loginStatus').classList.add('alert-success')</script>
                                    </c:if>

                                    <c:if test="${loginIncorreto}">
                                        Usuário ou senha incorretos.
                                        <script>document.getElementById('loginStatus').classList.add('alert-danger')</script>
                                    </c:if>
                                </div>

                                <button class="btn btn-outline-light btn-lg px-5" name="acao" value="criar" type="submit">Agendar</button>

                            </form>

                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
