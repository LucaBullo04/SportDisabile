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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/adminLTE/dist/css/adminlte.min.css">
    </head>

    <body class="h-100">
        <div class="container-fluid h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="w-25 mb-5">
                    <img src="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_trasparente.png" alt="logoGestionale" class="mx-auto d-block logoGestionale" style="height: 100px; width: 100px">
                    <h1 class="text-center mt-3 mb-5">Sport<span class="text-info">Disabile</span></h1>

                    <div>
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="componenteForm" id="email" name="email" data-inputmask='"mask": "*{3,}@a{3,}.a{2,}"' data-mask>
                    </div>
                    <p class="form-text mb-3">L'indirizzo email richiesto deve corrispondere a quello utilizzato come username.</p>    
                    <button type="button" class="submitButton mb-3">Cambia password</button> 
                    <p class="mt-3 text-center">Password ritrovata? <a href="/login" class="text-info">Accedi</a></p>
                </div>
            </div>

            <button type="button" id="passwordDimenticataOK" class="btn btn-default toastsDefaultFullImage d-none">Password dimenticata OK</button>
            <button type="button" id="passwordDimenticataKO" class="btn btn-danger swalDefaultError d-none">Password dimenticata KO</button>
        </div>

        <script src="${pageContext.request.contextPath}/adminLTE/plugins/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/sweetalert2/sweetalert2.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/moment/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/inputmask/jquery.inputmask.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/dist/js/adminlte.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/scripts/autenticazione_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script>
            $(document).ready(function () {
                $('[data-mask]').inputmask();
            });
        </script>

    </body>
</html>