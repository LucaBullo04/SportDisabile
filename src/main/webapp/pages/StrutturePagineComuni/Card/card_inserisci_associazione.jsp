<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html class="h-100">
    <jsp:include page="../../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info h-100">
        <div class="wrapper overlay-wrapper h-100">
            <jsp:include page="../../Topbars/top_bar_admin.jsp" />
            <jsp:include page="../../Sidebars/sidebar_admin.jsp" />

            <div class="content-wrapper h-75">
                <section class="content-header"></section>

                <section class="content h-75">
                    <div class="container-fluid h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-md-8">
                                <div class="card card-info card-outline mt-5">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="fas fa-address-card"></i>
                                            ${param.titoloCard}
                                        </h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col mx-auto">
                                            <div class="col-8  mx-auto">
                                                <div class="my-3 form-floating">
                                                    <label class="form-label">Username<span class="text-danger">*</span></label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user-circle"></i></span></div>
                                                        <input type="text" class="form-control text-center" id="username" name="username" data-inputmask='"mask": "*{3,}@a{3,}.a{2,}"' data-mask>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-8  mx-auto">
                                                <div class="my-3 form-floating">
                                                    <label class="form-label">Partita IVA<span class="text-danger">*</span></label>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-signature"></i></span></div>
                                                        <input type="text" class="form-control text-center" id="partitaIva" name="partitaIva" data-inputmask='"mask": "99999999999"' data-mask>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <c:if test="${associazioneId != 0}">
                                                <div class="custom-control custom-checkbox col-8 mx-auto d-flex justify-content-center mt-3">
                                                    <input class="custom-control-input custom-control-input-info nonRinnovare" type="checkbox" id="customCheckbox4" checked>
                                                    <label for="customCheckbox4" class="custom-control-label">Non rinnovare</label>
                                                </div>
                                                </c:if>

                                            <div id="divDurataAccount" class="col-8 mx-auto mb-3 ${param.nascondiDiv}">
                                                <label class="form-label">${labelDurataAccount}<span class="text-danger">*</span></label>
                                                <div class="input-group form-inline">
                                                    <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user-clock"></i></span></div>
                                                    <select id="durataAccount" class="select2" style="width: 92%">
                                                        <option value="1 mese">1 mese</option>
                                                        <option value="3 mesi">3 mesi</option>
                                                        <option value="6 mesi">6 mesi</option>
                                                        <option value="1 anno">1 anno</option>
                                                    </select>
                                                    
                                                </div>
                                            </div>
                                            
                                            
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="row justify-content-end">
                                            <a id="${param.idSubmitButton}" class="btn btn-app bg-info"><i class="fas fa-user-check"></i>${param.scrittaSubmitButton}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button type="button" id="creaAssociazioneOK" class="btn btn-default toastsDefaultFullImage d-none">Crea associazione OK</button>
                        <button type="button" id="creaAssociazioneKO" class="btn btn-danger swalDefaultError d-none">Crea associazione KO</button>
                        <button type="button" id="modificaAssociazioneOK" class="btn btn-success swalDefaultSuccess d-none">Modifica associazione OK</button>
                        <button type="button" id="modificaAssociazioneKO" class="btn btn-danger swalDefaultError d-none">Modifica associazione KO</button>
                    </div>

                    <input type="id" class="d-none" id="associazioneId" name="associazioneId" value="${associazioneId}" readonly>
                    <input type="text" id="scadenzaAccount" class="d-none" readonly>
                </section>
            </div>
        </div>

        <jsp:include page="../../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/admin_script.js"></script>
        <script>
            $(document).ready(function () {
                $('.select2').select2();

                getValoriAssociazione();
                checkRinnovoAccount();
                gestoreCheckboxRinnovoAccount();
            });
        </script>
    </body>
</html>