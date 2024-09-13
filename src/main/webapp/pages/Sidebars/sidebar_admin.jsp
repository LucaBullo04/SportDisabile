<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    String dropdownAssociazioniGestore = "";
    String dropdownAssociazioniClass = "";
    String inserisciAssociazioneClass = "";
    String listaAssociazioniClass = "";
    String dropdownAttivitaGestore = "";
    String dropdownAttivitaClass = "";
    String inserisciAttivitaClass = "";
    String listaAttivitaClass = "";
    String gestioneAttivitaClass = "";
%>

<aside class="main-sidebar sidebar-light-info elevation-4">
    <div class="sidebar">
        <div class="user-panel mt-3 pb-3 mb-3 d-flex justify-content-center">
            <div class="image" style="padding: 5px; overflow: hidden; border-radius: 50%; background: white">
                <img src="${pageContext.request.contextPath}/images/logo_gestionale_sfondo_trasparente.png" alt="Logo gestionale" style="width: 30px; height: 30px;">
            </div>
            <div class="info mt-2 p-0">
                <h5>Sport<span class="text-info">Disabile</span></h5>
            </div>
        </div>

        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item ${dropdownAssociazioniGestore}">
                    <a href="#" class="nav-link ${dropdownAssociazioniClass} dropdownLink"><i class="nav-icon fas fa-address-card"></i><p>Associazioni<i class="fas fa-angle-down right"></i></p></a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="/inserisciAssociazione" class="nav-link ${inserisciAssociazioneClass}"><i class="fas fa-user-plus nav-icon"></i><p>Inserisci associazione</p></a>
                        </li>
                        <li class="nav-item">
                            <a href="/listaAssociazioni" class="nav-link ${listaAssociazioniClass}"><i class="fas fa-users-between-lines nav-icon"></i><p>Lista associazioni</p></a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item ${dropdownAttivitaGestore}">
                    <a href="#" class="nav-link ${dropdownAttivitaClass} dropdownLink"><i class="nav-icon fa-brands fa-accessible-icon"></i><p>Attività<i class="fas fa-angle-down right"></i></p></a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="/attivita/inserisciAttivita" class="nav-link ${inserisciAttivitaClass}"><i class="fas fa-circle-plus nav-icon"></i><p>Inserisci attività</p></a>
                        </li>
                        <li class="nav-item">
                            <a href="/attivita/listaAttivita" class="nav-link ${listaAttivitaClass}"><i class="fas fa-rectangle-list nav-icon"></i><p>Lista attività</p></a>
                        </li>
                    </ul>
                </li>
                <!-- <li class="nav-item">
                    <a href="/gestioneListeAttivita" class="nav-link ${gestioneAttivitaClass} linkPaginaSingola"><i class="fas fa-tools nav-icon"></i><p>Gestione attività</p></a>
                </li> -->

                <li><hr></li>
            </ul>
        </nav>
    </div>
</aside>
