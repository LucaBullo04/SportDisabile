// CODICE MORTO (a un certo punto modificato da Andrea)

var MenuSinistra = function(options){
    
    var _construct = function(options){
        
        let assistenza = $('<a>',{"href":"/assistenza", class:"nav-link"}).append('<i class="fas fa-wrench nav-icon"></i>').append("<p>Assistenza</p>");
        
        switch (options.page){
            case "assistenza": assistenza.addClass("active");

        }
        
        $("body").append(`<aside class="main-sidebar sidebar-light-primary elevation-4">
                    <div class="sidebar">
                            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                                <div class="image">
                                    <img src="../../adminLTE/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                                </div>
                                <div class="info">
                                    <a href="#" class="d-block">Associazione sportiva che si occupa di attività sportive gestita da persone sportive</a>
                                </div>
                            </div>

                            <nav class="mt-2">
                                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                                    <li class="nav-item">
                                        <a href="/account/ilMioAccount" class="nav-link" id="linkAccount"><i class="fas fa-address-card nav-icon"></i><p>Account</p></a>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#" class="nav-link dropdownLink"><i class="nav-icon fa-brands fa-accessible-icon"></i><p>Attività<i class="fas fa-angle-down right"></i></p></a>
                                        <ul class="nav nav-treeview">
                                            <li class="nav-item">
                                                <a href="/attivita/inserisciAttivita" class="nav-link"><i class="fas fa-circle-plus nav-icon"></i><p>Inserisci attività</p></a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="/attivita/listaAttivita" class="nav-link"><i class="fas fa-rectangle-list nav-icon"></i><p>Le mie attività</p></a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="nav-item">`);
            $("body").append(assistenza);
            $("body").append(`
                                        
                                    </li>

                                    <li><hr></li>
                                    <li class="nav-item">
                                        <a href="/logout" class="nav-link"><i class="fas fa-power-off nav-icon text-danger"></i><p>Logout</p></a>
                                    </li> 
                                </ul>
                            </nav>
                        </div>
                </aside>
              `);
    };
    
    _construct(options);
};

$(document).ready(function() {});