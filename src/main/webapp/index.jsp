<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pet Topia - O melhor PetShop!</title>
    <link href="resources/bootstrap-5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark sticky-top border-bottom" data-bs-theme="dark" id="inicio">
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
                    <li class="nav-item"><a class="nav-link" href="#inicio">Início</a></li>
                    <li class="nav-item"><a class="nav-link" href="#servicos">Serviços</a></li>
                    <li class="nav-item"><a class="nav-link" href="#produtos">Produtos</a></li>
                    <li class="nav-item"><a class="nav-link" href="#agendamentos">Agendamentos</a></li>
                    <li class="nav-item"><a class="nav-link" href="#faqs">FAQs</a></li>
                    <a class="btn btn-primary" href="login.jsp" role="button">Entrar</a>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div class="fundo">
<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-body-black">
    <div class="col-md-6 p-lg-5 mx-auto my-5">
        <h1 class="display-3 text-white font-weight-bold">Pet Topia</h1>
        <h3 class=" mb-3 text-light">Seu Pet Shop que foca no amor, cuidado e qualidade.</h3>
        <div class="d-flex gap-3 justify-content-center lead fw-normal">
            <a class="btn btn-primary" href="#servicos">
                Serviços
            </a>
            <a class="btn btn-primary" href="cadastrar.jsp">
                Cadastrar-se
            </a>
        </div>
    </div>
    <div class="product-device shadow-sm d-none d-md-block"></div>
    <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
</div>
</div>

<div class="container px-4 py-5" id="servicos">
    <h2 class="pb-2 border-bottom">Serviços</h2>
    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
        <div class="col d-flex align-items-start">
            <div class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#toggles2"/></svg>
            </div>
            <div>
                <h3 class="fs-2 text-body-emphasis">Banho e Tosa</h3>
                <p>Esse serviço inclui banho, tosa, corte de unhas e limpeza. Profissionais experientes cuidarão do seu pet. Duração: 1 hora.</p>
                <a href="#" class="btn btn-primary">
                    Agendar Serviço
                </a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#cpu-fill"/></svg>
            </div>
            <div>
                <h3 class="fs-2 text-body-emphasis">Pet Walking</h3>
                <p>Passeio externo supervisionado. Seu pet terá exercícios, farejará novos cheiros e interagirá com outros cães. Duração: 1 hora.</p>
                <a href="#" class="btn btn-primary">
                    Agendar Serviço
                </a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#tools"/></svg>
            </div>
            <div>
                <h3 class="fs-2 text-body-emphasis">Hospedagem</h3>
                <p>
                    Oferecemos um ambiente seguro, confortável e divertido para o seu pet enquanto você está ausente. Duração flexível.
                </p>

                <a href="#" class="btn btn-primary">
                    Agendar Serviço
                </a>
            </div>
        </div>
    </div>
</div>

<script src="resources/bootstrap-5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>