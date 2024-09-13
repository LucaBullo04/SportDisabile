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
        <div class="container-fluid overlay-wrapper h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="w-25 mb-5">
                    <img src="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_trasparente.png" alt="logoGestionale" class="mx-auto d-block" style="height: 100px; width: 100px">
                    <h1 class="text-center mt-3 mb-5">Sport<span class="text-info">Disabile</span></h1>

                    <div class="mt-5 text-center">
                        <label class="form-label">Account disabilitato</label>
                        <p class="form-text">Il tuo abbonamento è scaduto e non è stato rinnovato. Contattare la mail <span class="text-info">email@esempio.com</span> per avere l'account nuovamente attivo.</p>
                    </div>

                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/adminLTE/plugins/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/sweetalert2/sweetalert2.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/plugins/moment/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/adminLTE/dist/js/adminlte.min.js"></script>
        
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/autenticazione_script.js"></script>
    </body>
</html>