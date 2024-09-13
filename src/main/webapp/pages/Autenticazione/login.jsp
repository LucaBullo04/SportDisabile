<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html class="h-100">
    <head>
        <title>${titoloDellaPagina}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_bianco.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/plugins/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/dist/css/adminlte.min.css">
    </head>

    <body class="h-100">
        <div class="container-fluid h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="w-25 mb-5">
                    <img src="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_trasparente.png" alt="logoGestionale" class="mx-auto d-block logoGestionale" style="height: 100px; width: 100px">
                    <h1 class="text-center mt-3 mb-5">Sport<span class="text-info">Disabile</span></h1>

                    <form id="formLogin" action="/login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="email" class="componenteForm" id="username" name="username" data-inputmask='"mask": "*{3,}@a{3,}.a{2,}"' data-mask>
                        </div>    
                        <div class="mb-2">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="componenteForm" id="password" name="password">
                        </div>

                        <div class="custom-control custom-checkbox ml-1 my-3">
                            <input class="custom-control-input custom-control-input-info mostraPassword" type="checkbox" id="customCheckbox4">
                            <label for="customCheckbox4" class="custom-control-label">Mostra password</label>
                        </div>

                        <button type="submit" class="submitButton">Accedi</button>

                        <p class="mt-3 text-center">Hai dimenticato la password?
                            <a href="/passwordDimenticata" class="text-info">Cambiala</a>
                        </p>

                    </form>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/adminLTE/plugins/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/sweetalert2/sweetalert2.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/moment/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/inputmask/jquery.inputmask.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/dist/js/adminlte.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/scripts/autenticazione_script.js"></script>
        <script>
            $(document).ready(function () {
                $('[data-mask]').inputmask();
            });
        </script>
    </body>
</html>