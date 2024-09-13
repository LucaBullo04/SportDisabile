<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String logoClass = "";
%>

<html class="h-100">
    <jsp:include page="../Imports/importaLinkStyle.jsp" />

    <body class="hold-transition accent-info h-100">
        <div class="wrapper overlay-wrapper h-100">
            <jsp:include page="../Topbars/top_bar_associazione.jsp" />
            <jsp:include page="../Sidebars/sidebar_associazione.jsp" />

            <div class="content-wrapper h-75">
                <section class="content-header"></section>

                <section class="content h-75">
                    <div class="container-fluid h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-md-10">
                                <div class="card card-info card-outline mt-5">
                                    <div class="card-header">
                                        <h3 class="card-title">
                                            <i class="fas fa-user-edit"></i>
                                            Modifica la tua associazione
                                        </h3>
                                    </div>
                                    <div class="card-body">
                                        <form id="formAssociazione" method="POST" action="/associazione/inserisciAssociazione2" enctype="multipart/form-data">
                                            <div class="row">
                                                <div class="${logoClass}">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Logo<!-- (512 x 512)--><span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-upload"></i></span></div>
                                                            <input readonly type="text" class="form-control bg-white" id="gestoreFileLogo" name="gestoreFileLogo">
                                                        </div>
                                                        <input type="file" accept="image/png, image/jpeg" class="form-control d-none" id="logo" name="logo">
                                                    </div>
                                                </div>

                                                <c:if test="${!isLogoNull}">
                                                    <div class="col-4 d-flex justify-content-center align-items-end mb-4">
                                                <div class="custom-control custom-checkbox ">
                                                    <input class="custom-control-input custom-control-input-info mantieniLogo" type="checkbox" id="customCheckbox4" checked>
                                                    <label for="customCheckbox4" class="custom-control-label">Mantieni logo</label>
                                                </div>
                                                    </div>
                                                </c:if>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Nome<span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-id-badge"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="nome" name="nome">
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-md-4">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Acronimo</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-signature"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="acronimo" name="acronimo">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-4">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Anno di fondazione</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-birthday-cake"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="annoDiFondazione" name="annoDiFondazione" data-inputmask='"mask": "9999"' data-mask>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                                    
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Città<span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-city"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="citta" name="citta">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Indirizzo<span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-map-signs"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="indirizzo" name="indirizzo">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Numero civico<span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-location-arrow"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="numeroCivico" name="numeroCivico">
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Provincia<span class="text-danger">*</span></label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="provincia" name="provincia" data-inputmask='"mask": "AA"' data-mask>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Numero di telefono</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-phone-square"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="numeroDiTelefono" name="numeroDiTelefono" data-inputmask='"mask": "999-999-9999"' data-mask>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Email</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-envelope"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="email" name="email" data-inputmask='"mask": "*{3,}@a{3,}.a{2,}"' data-mask>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">PEC</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-at"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="pec" name="pec" data-inputmask='"mask": "*{3,}@a{3,}.a{2,}"' data-mask>
                                                        </div>
                                                    </div>
                                                </div> 
                                                
                                                <div class="col-md-3">
                                                    <div class="my-3 form-floating">
                                                        <label class="form-label">Sito web (link)</label>
                                                        <div class="input-group">
                                                            <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-globe"></i></span></div>
                                                            <input type="text" class="form-control text-center" id="sitoWeb" name="sitoWeb">
                                                        </div>
                                                    </div>
                                                </div> 
                                            </div>
                                        </form>
                                    </div>

                                    <div class="card-footer">
                                        <div class="row justify-content-end">
                                            <a id="inserisciAssociazione" class="btn btn-app bg-info"><i class="fas fa-user-check"></i>Salva</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="toastsContainerTopRight" class="toasts-top-right fixed m-3">
                            <div id="alertAssociazioneInfoOK" class="toast fade" role="alert" aria-live="assertive" aria-atomic="true">
                                <div class="toast-header">
                                    <img id="logoAlertModificaAssociazione" src="${logoBase64}" class="rounded mr-2" alt="Logo" style="height: 25px; width: auto;">
                                    <strong id="titoloAlertModificaAssociazione" class="mr-auto"></strong>
                                    <small></small><button data-dismiss="toast" type="button" class="ml-2 mb-1 close" aria-label="Close"><span aria-hidden="true">×</span></button>
                                </div>
                                <div class="toast-body">Le tue informazioni sono state inserite!</div>
                            </div>
                        </div>

                        <button type="button" id="associazioneInfoOK" class="btn btn-default toastsDefaultFullImage d-none">Associazione info OK</button>
                        <button type="button" id="associazioneInfoKO" class="btn btn-danger swalDefaultError d-none">Associazione info KO</button>
                        <button type="button" id="dimensioniLogoOK" class="btn btn-success swalDefaultSuccess d-none">Dimensioni logo OK</button>
                        <button type="button" id="dimensioniLogoKO" class="btn btn-warning swalDefaultWarning d-none">Dimensioni logo KO</button>
                        <button type="button" id="linkSidebarBloccati" class="btn btn-warning swalDefaultWarning d-none">Link sidebar bloccati</button>
                    </div>
                </section>
            </div>
        </div>

        <jsp:include page="../Imports/importaScript.jsp" />
        <script src="${pageContext.request.contextPath}/js/scripts/gestionale_script.js"></script>
        <script src="${pageContext.request.contextPath}/js/scripts/associazione_script.js"></script>
        <script>
            $(document).ready(function () {
                let regole = ${regole};
                
                getValoriAssociazione();
                gestoreCheckboxLogo(${idLogoAssociazione});
                gestoreLinkPrimoAccesso(${isLogoNull});
            });
        </script>
    </body>
</html>