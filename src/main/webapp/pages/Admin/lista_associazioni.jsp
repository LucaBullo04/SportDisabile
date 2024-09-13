<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info">
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
                                            <i class="fas fa-users-between-lines"></i>
                                            Lista delle associazioni
                                        </h3>
                                    </div>

                                    <div id="bodyCardListaAssociazioni" class="card-body table-responsive p-0" style="height: 600px;">
                                        <table id="tabellaAssociazioni" class="table table-striped table-head-fixed text-nowrap d-none">
                                            <thead>
                                                <tr>
                                                    <th class="col-2 text-center">Logo</th>
                                                    <th class="col-2 text-center">Nome associazione</th>
                                                    <th class="col-2 text-center">Username</th>
                                                    <th class="col-2 text-center">Partita IVA</th>
                                                    <th class="col-2 text-center">Scadenza account</th>
                                                    <th class="col-2 text-center">Gestisci utente</th>
                                                </tr>
                                            </thead>

                                            <tbody id="bodyTabellaAssociazioni"></tbody>
                                        </table>
                                        <div id="messaggioIniziale" class="d-flex flex-wrap justify-content-center align-items-center h-100">
                                            <p class="font-italic">Selezionare il tipo di associazioni da visualizzare.</p>
                                        </div>
                                    </div>

                                </div>
                                <div class="row justify-content-around mt-5 w-100">
                                    <button id="buttonAssociazioniAttive" type="button" class="btn btn-info w-25">Associazioni attive</button>
                                    <button id="buttonAssociazioniDisattivate" type="button" class="btn btn-info w-25">Associazioni disattivate</button>
                                </div>
                            </div>
                        </div>

                        <form method="POST" id="formAssociazioneId">
                            <input id="associazioneId" name="associazioneId" type="number" class="d-none">
                        </form>

                        <button type="button" id="eliminaAssociazioneOK" class="btn btn-success swalDefaultSuccess d-none">Elimina associazione OK</button>
                    </div>
                </section>
            </div>
        </div>

        <jsp:include page="../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/admin_script.js"></script>
        <!-- <script>
            $(document).ready(function() {
                $("#buttonAssociazioniAttive").click();
            });
        </script> -->
    </body>
</html>