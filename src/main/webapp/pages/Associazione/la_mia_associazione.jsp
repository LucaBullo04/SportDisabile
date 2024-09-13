<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String scrittaBottoneInfo = "";
%>

<html class="h-100">
    <jsp:include page="../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info h-100">
        <div class="wrapper h-100">
            <c:choose>
                <c:when test="${isAdmin}">
                    <jsp:include page="../Topbars/top_bar_admin.jsp" />
                    <jsp:include page="../Sidebars/sidebar_admin.jsp" />
                </c:when>
                <c:otherwise>
                    <jsp:include page="../Topbars/top_bar_associazione.jsp" />
                    <jsp:include page="../Sidebars/sidebar_associazione.jsp" />
                </c:otherwise>
            </c:choose>

            <div class="content-wrapper h-75">
                <section class="content-header"></section>
                <section class="content h-75">
                    <div class="container-fluid h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-md-6">
                                <div class="card card-widget widget-user shadow">

                                    <div class="widget-user-header bg-info">
                                        <h3 class="widget-user-username">${associazione.nome}</h3>
                                    </div>
                                    <div class="widget-user-image">
                                        <c:choose>
                                            <c:when test="${associazione.logo != null}">
                                                <img class="img-circle elevation-2" src="data:image/jpg;base64,${associazione.logo.dataBase64}" alt="User Logo" style="height: 100px; width: 100px">
                                            </c:when>
                                            <c:otherwise>  
                                                <img class="img-circle elevation-2 defaultUserLogo" src="../../images/logo_associazione_placeholder.png" alt="User Logo">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="card-body">
                                        <c:choose>
                                            <c:when test="${fn: length(associazione.proprieta) == 0}">
                                                <div id="messaggioTabellaVuota" class="mt-5 d-flex flex-wrap justify-content-center align-items-center h-100">
                                                    <p class="font-italic">Le informazioni non sono ancora state inserite.</p>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${associazione.proprieta[0].valoreProprieta != null}">
                                                    <strong><i class="fas fa-signature mr-1"></i> Acronimo</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[0].valoreProprieta}</p>
                                                    <hr>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[1].valoreProprieta != null}">
                                                    <strong><i class="fas fa-birthday-cake mr-1"></i> Anno di fondazione</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[1].valoreProprieta}</p>
                                                    <hr>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[2].valoreProprieta != null}">
                                                    <strong><i class="fas fa-map-marker-alt mr-1"></i> Provincia</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[2].valoreProprieta}</p>
                                                    <hr>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[3].valoreProprieta != null}">
                                                    <strong><i class="fas fa-city mr-1"></i> Città</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[3].valoreProprieta}</p>
                                                    <hr>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[4].valoreProprieta != null}">
                                                    <strong><i class="fas fa-map-signs mr-1"></i> Indirizzo</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[4].valoreProprieta}</p>
                                                    <hr>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[5].valoreProprieta != null}">
                                                    <strong><i class="fas fa-location-arrow mr-1"></i> Numero civico</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[5].valoreProprieta}</p>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[6].valoreProprieta != null}">
                                                    <hr>
                                                    <strong><i class="fas fa-phone-square mr-1"></i> Numero di telefono</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[6].valoreProprieta}</p>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[7].valoreProprieta != null}">
                                                    <hr>
                                                    <strong><i class="fas fa-envelope mr-1"></i> Email</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[7].valoreProprieta}</p>
                                                </c:if>

                                                <c:if test="${associazione.proprieta[8].valoreProprieta != null}">
                                                    <hr>
                                                    <strong><i class="fas fa-at mr-1"></i> PEC</strong>
                                                    <p class="float-right text-muted">${associazione.proprieta[8].valoreProprieta}</p>
                                                </c:if>
                                                    
                                                    <c:if test="${associazione.proprieta[9].valoreProprieta != null}">
                                                    <hr>
                                                    <strong><i class="fas fa-globe mr-1"></i> Link sito web</strong>
                                                    <a href="${associazione.proprieta[9].valoreProprieta}" class="float-right text-info">${associazione.proprieta[9].valoreProprieta}</a>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    
                                        <c:if test="${!isAdmin}">
                                    <div class="card-footer">
                                        <div class="row justify-content-end">
                                            <a href="/associazione/modificaAssociazione" class="btn btn-app bg-info"><i class="fas fa-user-edit"></i>${scrittaBottoneInfo}</a>
                                        </div>
                                    </div>
                                            </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>  

        <jsp:include page="../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/associazione_script.js"></script>
    </body>
</html>