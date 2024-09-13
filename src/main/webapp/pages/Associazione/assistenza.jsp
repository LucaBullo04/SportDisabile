<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../Imports/importaLinkStyle.jsp" />
    
    <body class="hold-transition accent-info">
        <div class="wrapper">
            <jsp:include page="../Topbars/top_bar_associazione.jsp" />
            <jsp:include page="../Sidebars/sidebar_associazione.jsp" />
            
            <div class="content-wrapper">
                <section class="content-header"></section>
                <section class="content">
                    <div class="container-fluid">

                        <div class="row justify-content-center mt-5">
                            <div class="col-10">
                                <!-- <div class="card"> -->
                                    <div class="card-body row">
                                        <div class="col-5 text-center d-flex align-items-center justify-content-center">
                                            <div class="card card-danger card-outline">

                                                <div class="card-body">
                                                    <p class="text-muted"><img id="logoGestionale" src="../../images/logo_servicematica.jpg" height="50px"></p>
                                                    <p class="font-weight-bolder">SERVICEMATICA®</p>
                                                    <hr>
                                                            
                                                    <strong><i class="fas fa-phone"></i><p class="text-info">041 309 4509</p></strong>
                                                    <hr>

                                                    <strong><i class="fas fa-envelope"></i><p class="text-info">segreteria@servicematica.com</p></strong>
                                                    <hr>
                                                    
                                                    <strong><i class="fas fa-globe"></i><p class="text-muted"><a class="text-info" href="https://servicematica.com/">Servicematica</a></p></strong>
                                                    <hr>

                                                    <strong><i class="fab fa-instagram"></i><p class="text-muted"><a class="text-info" href="https://www.instagram.com/servicematica/">Instagram</a></p></strong>
                                                    <hr>

                                                    <strong><i class="fab fa-facebook-square"></i><p class="text-muted"><a class="text-info" href="https://www.facebook.com/Servicematica/?locale=it_IT">Facebook</a></p></strong> 
                                                    <hr>

                                                    <strong><i class="fab fa-telegram"></i><p class="text-muted"><a class="text-info" href="https://t.me/newsgiustiziaservicematica">Telegram</a></p></strong>
                                                                  
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6 mt-5">
                                            <div class="form-group">
                                                <label for="inputMessage">Contattaci via mail</label>
                                                <textarea id="formContattaciMessaggio" class="form-control" rows="20"></textarea>
                                            </div>
                                            <div class="col-12">
                                                <input id="inviaMessaggioButton" type="submit" class="btn btn-info float-right" value="Invia messaggio">
                                            </div>
                                        </div>
                                    </div>
                                <!-- </div> -->
                            </div>
                        </div>
                    </div>
                    
                    <button type="button" id="invioMessaggioOK" class="btn btn-success swalDefaultSuccess d-none">Invio messaggio OK</button>
                    <button type="button" id="invioMessaggioKO" class="btn btn-danger swalDefaultError d-none">Invio messaggio KO</button>    
                </section>
            </div>
        </div>

        <jsp:include page="../Imports/importaScript.jsp" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/associazione_script.js"></script>
    </body>
</html>