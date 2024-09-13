<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html class="h-100">
    <jsp:include page="../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info h-100">
        <div class="wrapper h-100">
            <jsp:include page="../Topbars/top_bar_associazione.jsp" />
            <jsp:include page="../Sidebars/sidebar_associazione.jsp" />

            <div class="content-wrapper h-75">
                <section class="content-header"></section>
                <section class="content h-75">
                    <div class="container-fluid h-100">

                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-6">
                                <div id="cardIlMioAccount" class="card card-widget widget-user shadow">
                                    <div class="widget-user-header bg-info">
                                        <h3 class="widget-user-username">${associazioneUtenteLoggato.nome}</h3>
                                    </div>
                                    <div class="widget-user-image">
                                        <c:choose>
                                            <c:when test="${associazioneUtenteLoggato.logo != null}">
                                                <img class="img-circle elevation-2" src="data:image/jpg;base64,${associazioneUtenteLoggato.logo.dataBase64}" alt="User Logo" style="height: 100px; width: 100px">
                                            </c:when>
                                            <c:otherwise>  
                                                <img class="img-circle elevation-2 defaultUserLogo" src="../../images/logo_associazione_placeholder.png" alt="User Logo">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="card-body">
                                        <strong><i class="fas fa-user-circle mr-1"></i> Username</strong>
                                        <p class="float-right text-muted">${associazioneUtenteLoggato.utente.username}</p>
                                        <hr>
                                        
                                        <strong><i class="fas fa-signature mr-1"></i> Partita IVA</strong>
                                        <p class="float-right text-muted">${associazioneUtenteLoggato.partitaIva}</p>
                                        <hr>
                                        
                                        <strong><i class="fas fa-calendar-alt mr-1"></i> Scadenza account</strong>
                                        <p class="float-right text-muted">${dataScadenzaFormattata}</p>
                                        <hr>
                                        
                                        <strong><i class="fas fa-user-clock mr-1"></i> Giorni mancanti alla scadenza</strong>
                                        <p class="float-right text-muted">${giorniMancanti}</p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>    
                </section>
            </div>
        </div>

        <jsp:include page="../Imports/importaScript.jsp" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/associazione_script.js"></script>
    </body>
</html>