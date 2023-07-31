<%--
  Created by IntelliJ IDEA.
  User: kayrs
  Date: 02/06/2023
  Time: 01:51
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
                    <div class="btn-group button-login">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <c:out value="${sessionScope.nomeUsuario}" />
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="editarusuario">Editar Usuário</a></li>
                            <li>
                                <form action="logout" method="post">
                                    <button class="dropdown-item" type="submit">Sair</button>
                                </form>
                            </li>

                        </ul>
                    </div>
                </ul>
                </div>
                </div>
        </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>Agendamentos</h1>
            <div id="agendamentos">
                <form action="agendar">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome do Pet</th>
                        <th>Servico</th>
                        <th>Data</th>
                        <th>Opções</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="agendamento" items="${agendamentos}">
                        <tr>
                            <input type="hidden" name="idAgendamento" value="${agendamento.id}"/>
                            <td>${agendamento.id}</td>
                            <td>
                                <c:set var="idPet" value="${agendamento.idPet}" />
                                <c:forEach var="pet" items="${pets}">
                                    <c:if test="${pet.id eq idPet}">
                                        ${pet.nome}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:set var="idServico" value="${agendamento.idServico}"/>
                                <c:forEach var="servico" items="${servicos}">
                                    <c:if test="${servico.id eq idServico}">
                                        ${servico.nome}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${agendamento.dataAgendamento}</td>
                            <td><button class="btn btn-warning" type="submit" name="acao" value="editar">Editar</button><button class="btn btn-danger" type="submit" name="acao" value="excluir">Excluir</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary" name="acao" value="criar">Novo Agendamento</button>
            </form>
            </div>
        </div>
        <div class="col-md-6">
            <h1>Pets</h1>
            <div id="pets">
                <form action="pet">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Raça</th>
                        <th>Idade</th>
                        <th>Opções</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="idTipo" value="${pet.idTipo}" />
                    <c:forEach var="pet" items="${pets}">
                        <tr>
                            <td>${pet.id}</td>
                            <input type="hidden" id="idPet" name="idPet" value="${pet.id}"/>
                            <td>${pet.nome}</td>
                            <td>
                                <c:forEach var="tipo" items="${tipos}">
                                    <c:if test="${tipo.id eq pet.idTipo}">
                                        ${tipo.nome}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="raca" items="${racas}">
                                    <c:if test="${raca.id eq pet.idRaca}">
                                        ${raca.nome}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${pet.idade} anos</td>
                            <td>
                                <button type="submit" name="acao" value="editar" class="btn btn-warning">Editar</button>
                                <button type="submit" name="acao" value="excluir" class="btn btn-danger">Excluir</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <button type="submit" name="acao" value="criar" class="btn btn-primary">Novo Pet</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="resources/bootstrap-5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>
