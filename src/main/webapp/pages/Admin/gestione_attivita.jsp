<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition">
        <div class="wrapper">
            <jsp:include page="../Topbars/top_bar_admin.jsp" />
            <jsp:include page="../Sidebars/sidebar_admin.jsp" />

            <div class="content-wrapper">
                <section class="content-header"></section>
                <section class="content">
                    <div class="container-fluid">

                        <div class="row justify-content-center">
                            <div class="col-10">
                                <div class="card card-info card-outline mt-5">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="fas fa-tools"></i>
                                            Gestione attività
                                        </h3>
                                    </div>
                                    <div class="card-body">

                                        <div class="row"> 
                                            <div class="col-6">
                                                <div class="d-flex justify-content-center align-items-center h-100">
                                                    <div class="w-75">
                                                        <div class="input-group mb-3">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-running"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="sport" name="sport" placeholder="Inserire uno sport">
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <a id="inserisciSport" class="btn btn-app bg-info"><i class="fas fa-plus-circle"></i>Aggiungi alla lista</a>
                                                            <a id="eliminaSport" class="btn btn-app bg-danger"><i class="fas fa-times-circle"></i>Rimuovi dalla lista</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-6 table-responsive" style="height: 300px">
                                                <table id="tabellaSport" class="table table-bordered table-head-fixed text-nowrap">
                                                    <thead>
                                                        <tr>
                                                            <th class="col-2 text-center">Sport</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="bodyTabellaSport"></tbody>
                                                </table>
                                            </div> 
                                        </div>

                                        <hr>

                                        <div class="row">
                                            <div class="col-6">
                                                <div class="d-flex justify-content-center align-items-center h-100">
                                                    <div class="w-75">
                                                        <div class="input-group mb-3">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-crutch"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="disabilita" name="disabilita" placeholder="Inserire una disabilità">
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <a id="inserisciDisabilita" class="btn btn-app bg-info"><i class="fas fa-plus-circle"></i>Aggiungi alla lista</a>
                                                            <a id="eliminaDisabilita" class="btn btn-app bg-danger"><i class="fas fa-times-circle"></i>Rimuovi dalla lista</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-6 table-responsive" style="height: 300px">
                                                <table id="tabellaDisabilita" class="table table-bordered table-head-fixed text-nowrap">
                                                    <thead>
                                                        <tr>
                                                            <th class="col-2 text-center">Disabilità</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="bodyTabellaDisabilita"></tbody>
                                                </table>
                                            </div> 
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <button type="button" id="inserisciSportOK" class="btn btn-success swalDefaultSuccess d-none">Inserisci sport OK</button>
                    <button type="button" id="inserisciSportKO" class="btn btn-danger swalDefaultError d-none">Inserisci sport KO</button> 
                    <button type="button" id="eliminaSportOK" class="btn btn-success swalDefaultSuccess d-none">Elimina sport OK</button>
                    <button type="button" id="eliminaSportKO" class="btn btn-danger swalDefaultError d-none">Elimina sport KO</button> 
                    <button type="button" id="inserisciDisabilitaOK" class="btn btn-success swalDefaultSuccess d-none">Inserisci disabilità OK</button>
                    <button type="button" id="inserisciDisabilitaKO" class="btn btn-danger swalDefaultError d-none">Inserisci disabilità KO</button> 
                    <button type="button" id="eliminaDisabilitaOK" class="btn btn-success swalDefaultSuccess d-none">Elimina disabilità OK</button>
                    <button type="button" id="eliminaDisabilitaKO" class="btn btn-danger swalDefaultError d-none">Elimina disabilità KO</button> 
                </section>
            </div>
        </div>

        <jsp:include page="../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/admin_script.js"></script>
        <script>
            $(document).ready(function () {
                listaSport();
                listaDisabilita();
            });
        </script>
    </body>
</html>
