<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String lunghezzaColonne = "";
%>

<html class="h-100">
    <jsp:include page="../../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info h-100">
        <div class="wrapper overlay-wrapper h-100">
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

            <div class="content-wrapper h-75">
                <section class="content-header"></section>
                <section class="content h-75">
                    <div class="container-fluid h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-md-10">
                                <div class="card card-widget widget-user shadow mt-5">
                                    <div class="widget-user-header bg-info">
                                        <h3 class="widget-user-username mt-4">${param.titoloCard}</h3>
                                    </div>
                                    <div class="widget-user-image border border-dark mt-3" style="padding: 8px; overflow: hidden; border-radius: 50%; background: white">
                                        <img src="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_bianco.png" alt="logoGestionale" style="height: 60px; width: 60px;">
                                    </div>
                                    <div class="card-body">
                                        <div class="my-3">
                                            <form>
                                                <div class="row">
                                                    <c:if test="${isAdmin}">
                                                        <div class="${lunghezzaColonne}">
                                                            <div class="my-3 form-floating">
                                                                <label class="form-label"><i class="fas fa-user mr-2"></i>Associazione<span class="text-danger">*</span></label>
                                                                <div class="input-group">
                                                                    <select id="associazione" class="select2" style="width: 100%;"></select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>

                                                    <div class="${lunghezzaColonne}">
                                                        <div class="my-3 form-floating">
                                                            <label class="form-label"><i class="fas fa-running mr-2"></i>Sport<span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <select id="sport" class="select2" style="width: 100%;"></select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="${lunghezzaColonne}">
                                                        <div class="my-3 form-floating">
                                                            <label class="form-label"><i class="fas fa-crutch mr-2"></i>Disabilità<span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <select id="disabilita" class="select2" style="width: 100%;"></select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="my-3 form-floating">
                                                            <label class="form-label"><i class="fas fa-calendar-alt mr-2"></i>Praticata il<span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <select id="giorni" class="select2" multiple="multiple" data-placeholder="Selezionare uno o più giorni" style="width: 100%;">
                                                                    <option value='Lunedì'>Lunedì</option>
                                                                    <option value='Martedì'>Martedì</option>
                                                                    <option value='Mercoledì'>Mercoledì</option>
                                                                    <option value='Giovedì'>Giovedì</option>
                                                                    <option value='Venerdì'>Venerdì</option>
                                                                    <option value='Sabato'>Sabato</option>
                                                                    <option value='Domenica'>Domenica</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <div class="my-3 form-floating">
                                                            <label class="form-label"><i class="fas fa-clock mr-2"></i>Dalle ore<span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control text-center" id="ora" name="ora" data-inputmask='"mask": "99:99"' data-mask>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <div class="my-3 form-floating">
                                                            <label class="form-label"><i class="fas fa-stopwatch mr-2"></i>Durata<span class="text-danger">*</span></label>
                                                            <div class="input-group">
                                                                <input type="text" class="form-control text-center" id="durata" name="durata" data-inputmask='"mask": "9{2,3} minuti"' data-mask>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <input type="id" class="d-none" id="attivitaId" name="attivitaId" value="${attivitaId}" readonly>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="card-footer">
                                        <div class="row justify-content-end">
                                            <a id="${param.idSubmitButton}" class="btn btn-app bg-info"><i class="fas fa-file-upload"></i>Salva</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button type="button" id="inserisciAttivitaOK" class="btn btn-success swalDefaultSuccess d-none">Inserisci attività OK</button>
                        <button type="button" id="inserisciAttivitaKO" class="btn btn-danger swalDefaultError d-none">Inserisci attività KO</button>
                        <button type="button" id="modificaAttivitaOK" class="btn btn-success swalDefaultSuccess d-none">Modifica attività OK</button>
                        <button type="button" id="modificaAttivitaKO" class="btn btn-danger swalDefaultError d-none">Modifica attività KO</button>
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
                
                gestoreListeDropdownAttivita();
                getValoriAttivita(); 
            });
        </script>
    </body>
</html>