<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="main-header navbar navbar-expand navbar-white navbar-light">

    <ul class="navbar-nav">
        <li id="bottoneMostraSidebar" class="nav-item d-none">
            <a class="nav-link" data-widget="pushmenu" role="button"><i class="fas fa-bars"></i></a>
        </li>
    </ul>

    <ul class="navbar-nav ml-auto">
        
        <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown">
                <i id="iconaTopBarAdmin" class="fas fa-door-closed text-info"></i>
            </a>
            <div id="opzioniTopBarAdmin" class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
                <a href="/logout" class="dropdown-item">
                    <i class="fas fa-power-off text-danger"></i> 
                    <span class="float-right text-muted text-sm my-auto">Logout</span>
                </a>
                
                <div class="dropdown-divider"></div>
                <a id="espansioneButton" class="dropdown-item" data-widget="fullscreen" role="button">
                    <i class="fas fa-expand-arrows-alt"></i> 
                    <span class="float-right text-muted text-sm my-auto">Espandi</span>
                </a>
            </div>
        </li>
        
    </ul>
</nav>
