<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info">
        <div class="wrapper">
            <c:choose>
                <c:when test="${isAdmin}">
                    <jsp:include page="../../Topbars/top_bar_admin.jsp" />
                    <jsp:include page="../../Sidebars/sidebar_admin.jsp" />
                </c:when>
                <c:otherwise>
                    <jsp:include page="../../Topbars/top_bar_associazione.jsp" />
                    <jsp:include page="../../Sidebars/sidebar_associazione.jsp" />
                </c:otherwise>
            </c:choose>

            <div class="content-wrapper">
                <section class="content-header"></section>
                <section class="content">
                    <div class="container-fluid">
                        <!-- DA IMPLEMENTARE IN FUTURO -->
                        <!-- <form>
                            <div class="row">
                                <div class="col-md-10 offset-md-1">
                                    <div class="row">
                                        <div class="col-3">
                                            <div class="form-group">
                                                <label>Sport: </label>
                                                <select id="selectSport" class="select2" multiple="multiple" data-placeholder="Tutti" style="width: 100%;"></select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-3">
                                            <div class="form-group">
                                                <label>Disabilità: </label>
                                                <select id="selectDisabilita" class="select2" multiple="multiple" data-placeholder="Tutte" style="width: 100%;"></select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-3">
                                            <div class="form-group">
                                                <label>Tipologia ordine: </label>  
                                                <select id="selectTipologiaOrdine" class="select2" style="width: 100%;">
                                                    <option selected>Ascendente</option>
                                                    <option>Discendente</option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-3">
                                            <div class="form-group">
                                                <label>Ordina per: </label>
                                                <select id="selectOrdinamento" class="select2" data-placeholder="Nome associazione" style="width: 100%;"></select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="input-group input-group-lg">
                                            <input type="search" class="form-control form-control-lg" placeholder="Inserire il nome di un'associazione">
                                            <div class="input-group-append">
                                                <button type="submit" class="btn btn-lg btn-default">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form> -->

                        <div class="row justify-content-center">
                            <div class="col-10">
                                <div id="cardListaAttivita" class="card card-info card-outline mt-5">

                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="fa-brands fa-accessible-icon"></i>
                                            Lista delle attività
                                        </h3>
                                    </div>

                                    <div id="bodyCardListaAttivita" class="card-body table-responsive p-0" style="height: 600px;">
                                        <c:choose>
                                            <c:when test="${isAdmin}">
                                                <jsp:include page="../../StrutturePagineComuni/TabelleAttivita/tabella_admin.jsp" />
                                            </c:when>
                                            <c:otherwise>
                                                <jsp:include page="../../StrutturePagineComuni/TabelleAttivita/tabella_associazione.jsp" />
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                </div>
                                <form method="POST" action="/attivita/modificaAttivita" id="formModificaAttivita">
                                    <input id="attivitaId" name="attivitaId" type="number" class="d-none">
                                </form>
                            </div>
                        </div>
                        <button type="button" id="eliminaAttivitaOK" class="btn btn-success swalDefaultSuccess d-none">Elimina attività OK</button>
                    </div>
                </section>
            </div>
        </div>

        <jsp:include page="../../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/associazione_script.js"></script>

        <script>
            $(function () {
                $('.select2').select2();
            });

            $(document).ready(function () {
            <c:choose>
                <c:when test="${isAdmin}">listaAttivitaAdmin();</c:when>
                <c:otherwise>listaAttivitaAssociazione();</c:otherwise>
            </c:choose>

                        gestoreListeDropdownAttivita();
                    });
        </script>
    </body>
</html>