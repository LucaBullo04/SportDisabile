$(document).ready(function () {
    gestoreLogoTopBar();
    inserisciAttivita();
    modificaAttivitaPage();
    modificaAttivita();
    eliminaAttivita();
    inserisciAssociazione();
    gestoreAlertLogo();
    
    setTimeout(function() {
        $("#logoTopBar").click(function() {
        console.log("Schiacciato");
    });
    }, 100);
    
});

function gestoreLogoTopBar() {
    $.ajax({
        type: "GET",
        url: "/getLogoUtenteLoggato",
        success: function(data) {
            $("#logoTopBar").attr("src", "data:image/jpg;base64," + data);
        },
        error: function() {
            $("#logoTopBar").attr("src", "../../images/logo_associazione_placeholder.png");
        }
    });
}

function inserisciAttivita() {
    $("#inserisciAttivita").click(function () {
        let associazioneId = $("#associazione").val();
        let sportId = $("#sport").val();
        let disabilitaId = $("#disabilita").val();
        let giorni = $("#giorni").val().toString();
        let ora = $("#ora").val();
        let durata = $("#durata").val();

        $.ajax({
            type: "POST",
            url: "/attivita/creaAttivita",
            data: {associazioneId, sportId, disabilitaId, giorni, ora, durata},
            success: function () {
                $("#inserisciAttivitaOK").click();
            },
            error: function () {
                $("#inserisciAttivitaKO").click();
            }
        });
    });
}

function modificaAttivitaPage() {
    setTimeout(function () {
        $(".modificaAttivita").click(function () {
            let attivitaId = $(this).parents(".rigaTabellaAttivita").attr("id");

            $("#attivitaId").val(attivitaId);
            $("#formModificaAttivita").submit();
        });
    }, 100);
}

function modificaAttivita() {
    $("#modificaAttivita").click(function () {
        let associazioneId = $("#associazione").val();
        let attivitaId = $("#attivitaId").val();
        let sportId = $("#sport").val();
        let disabilitaId = $("#disabilita").val();
        let giorni = $("#giorni").val().toString();
        let ora = $("#ora").val();
        let durata = $("#durata").val();
        $.ajax({
            type: "POST",
            url: "/attivita/cambiaValoriAttivita",
            data: {associazioneId, attivitaId, sportId, disabilitaId, giorni, ora, durata},
            success: function () {
                $("#modificaAttivitaOK").click();
                $(".overlay-wrapper").append(`
                        <div class="overlay">
                        <i class="mr-3 fas fa-3x fa-sync-alt fa-spin"></i>
                            <div class="text-bold pt-2">Attendi il caricamento...</div>
                        </div>
                      `);
                setTimeout(function () {
                    window.location = "/attivita/listaAttivita";
                }, 3000);
            },
            error: function () {
                $("#modificaAttivitaKO").click();
            }
        });
    });
}

function eliminaAttivita() {
    setTimeout(function () {
        $(".eliminaAttivita").click(function () {
            let attivitaId = $(this).parents(".rigaTabellaAttivita").attr("id");
            $.ajax({
                type: "POST",
                url: "/attivita/eliminaAttivita",
                data: {attivitaId},
                success: function (isAdmin) {
                    $("#eliminaAttivitaOK").click();
                    $("#" + attivitaId).remove();

                    if ($(".rigaTabellaAttivita").length == 0) {
                        if (isAdmin) {
                            $("#tabellaAttivitaAdmin").addClass("d-none");
                            creaMessaggioTabellaVuota("Tutte le attività sono state eliminate. Attendere la creazione di altre attività da parte delle associazioni.");
                        } else {
                            $("#tabellaAttivitaAssociazione").addClass("d-none");
                            creaMessaggioTabellaVuota("Hai eliminato tutte le tue attività.");
                        }
                        /*} else {
                         if(isAdmin) {
                         $("#tabellaAttivitaAdmin").removeClass("d-none");
                         $("#messaggioTabellaVuota").addClass("d-none");
                         } else {
                         $("#tabellaAttivitaAssociazione").removeClass("d-none");
                         $("#messaggioTabellaVuota").addClass("d-none");
                         }*/
                    }
                }
            });
        });
    }, 100);
}

function listaAttivitaAssociazione() {
    $.ajax({
        type: "GET",
        url: "/listaAttivita/associazione",
        success: function (data) {
            creaTabellaAttivita(false, data, "#bodyTabellaAttivitaAssociazione", "#tabellaAttivitaAssociazione", "Non hai neancora creato un'attività.");
        }
    });
}

function listaAttivitaAdmin() {
    $.ajax({
        type: "GET",
        url: "/listaAttivita/admin",
        success: function (data) {
            creaTabellaAttivita(true, data, "#bodyTabellaAttivitaAdmin", "#tabellaAttivitaAdmin", "Nessuna attività presente nel database.");
        }
    });
}

function creaTabellaAttivita(isAdmin, listaAttivita, bodyTabella, nomeTabella, messaggioTabellaVuota) {
    if (listaAttivita.length > 0) {
        // FUNZIONA MA è DA SISTEMARE, FARE LA STESSA COSA CON LA LISTA DEGLI UTENTI
        if (isAdmin) {
            $(nomeTabella).append(`
        <thead>
        <tr>
                <th class="col-1 text-center">Logo associazione</th>
            <th class="col-1 text-center">Nome associazione</th>
            <th class="col-2 text-center">Sport</th>
            <th class="col-2 text-center">Disabilità</th>
            <th class="col-2 text-center">Praticata il</th>
            <th class="col-2 text-center">Alle</th>
            <th class="col-2 text-center">Durata</th>
            <th class="col-2 text-center">Gestisci attività</th>
        </tr>
    </thead>
            `);
        } else {
            $(nomeTabella).append(`
        <thead>
        <tr>
            <th class="col-2 text-center">Sport</th>
            <th class="col-2 text-center">Disabilità</th>
            <th class="col-2 text-center">Praticata il</th>
            <th class="col-2 text-center">Alle</th>
            <th class="col-2 text-center">Durata</th>
            <th class="col-2 text-center">Gestisci attività</th>
        </tr>
    </thead>
            `);
        }

        for (let attivita of listaAttivita) {
            let sport = checkSportAttivita(attivita);
            let disabilita = checkDisabilitaAttivita(attivita);
            $(bodyTabella).append(`
                        <tr id="${attivita.id}" class="rigaTabellaAttivita">
                            <td id="cellaSport" class="text-center" style="vertical-align: middle">${sport}</td>
                            <td id="cellaDisabilita" class="text-center" style="vertical-align: middle">${disabilita}</td>
                            <td id="cellaGiorni" class="text-center" style="vertical-align: middle">${attivita.proprieta[0].valoreProprieta}</td>
                            <td id="cellaOra" class="text-center" style="vertical-align: middle">${attivita.proprieta[1].valoreProprieta}</td>
                            <td id="cellaDurata" class="text-center" style="vertical-align: middle">${attivita.proprieta[2].valoreProprieta}</td>
                            <td id="cellaGesticiAttivita" style="vertical-align: middle">
                                <div class="row justify-content-center">
                                    <div><i class="fas fa-pencil-alt mr-3 text-secondary modificaAttivita" onmouseenter="$(this).addClass('text-info');" onmouseleave="$(this).removeClass('text-info');"></i></div>
                                    <div><i class="fas fa-times-circle text-secondary eliminaAttivita" onmouseenter="$(this).addClass('text-danger');" onmouseleave="$(this).removeClass('text-danger');"></i></div>
                                </div>
                            </td>
                        </tr>
                `);
            if (isAdmin) {
                let logo = "../../images/logo_associazione_placeholder.png";
                if (attivita.associazione.logo != null) {
                    logo = "data:image/jpg;base64," + attivita.associazione.logo.dataBase64;
                }
                $("#" + attivita.id).prepend(`
                        <td id="cellaLogo" style="vertical-align: middle">
                            <div class="image">
                                <img alt="logo" src="${logo}" class="img-circle elevation-2 mx-auto d-block" height="70px" width="70px">
                            </div>
                        </td>
                        <td id="cellaNomeAssociazione" class="text-center" style="vertical-align: middle">${attivita.associazione.nome}</td>
                      `);
            }
        }
    } else {
        $(nomeTabella).addClass("d-none");
        creaMessaggioTabellaVuota(messaggioTabellaVuota);
    }
}

function creaMessaggioTabellaVuota(messaggioTabellaVuota) {
    $("#bodyCardListaAttivita").append(`
        <div id="messaggioTabellaVuota" class="d-flex flex-wrap justify-content-center align-items-center h-100">
            <p class="font-italic">${messaggioTabellaVuota}</p>
        </div>
    `);
}

function checkSportAttivita(attivita) {
    if (attivita.sport != null) {
        return attivita.sport.nomeSport;
    } else {
        return "//";
    }
}

function checkDisabilitaAttivita(attivita) {
    if (attivita.disabilita != null) {
        return attivita.disabilita.nomeDisabilita;
    } else {
        return "//";
    }
}

function getValoriAttivita() {
    let attivitaId = $("#attivitaId").val();

    if (attivitaId != 0) {
        setTimeout(function () {
            $.ajax({
                type: "POST",
                url: "/attivita/getAttivitaById",
                data: {attivitaId},
                success: function (data) {
                    console.log(data);

                    $("#sport").val(data.sport.id).change();
                    $("#disabilita").val(data.disabilita.id).change();
                    let arrayValoreGiorni = [];
                    for (let giorno of data.proprieta[0].valoreProprieta.split(",")) {
                        giorno = giorno.toString().trim();
                        arrayValoreGiorni.push(giorno);
                    }
                    $("#giorni").val(arrayValoreGiorni).trigger("change");
                    $("#ora").val(data.proprieta[1].valoreProprieta);
                    $("#durata").val(data.proprieta[2].valoreProprieta);
                    if (data.associazione != undefined) {
                        $("#associazione").val(data.associazione.id).change();
                    }
                }
            });
        }, 10);
    }

}

function inserisciAssociazione() {
    $("#inserisciAssociazione").click(function () {
        $("#formAssociazione").submit();
    });

    $("#formAssociazione").submit(function (event) {
        // event.preventDefault() è sbagliato
        event.preventDefault();

        // INIZIO APPROCCIO 1: gestione dei dati e il conseguente invio del form con return true (o eventuale rifiuto con return false
        // VANTAGGIO: Gestione dei dati lasciata al form, che esegue tutto in automatico
        // SVANTAGGIO: La pagina viene sempre ricaricata all'invio del form, con la conseguente perdita dei dati inseriti in pagina

        /*let fileLogo = document.getElementById("logo").files[0];
         console.log("file logo form");
         console.log(fileLogo);
         
         return true;*/

        // FINE APPROCCIO 1

        // INIZIO APPROCCIO 2: gestione dei dati compiuta manualmente tramite FormData e il metodo append(nomeCampo, valoreInseritoNellaPagina) per l'invio dei dati
        // VANTAGGIO: La pagina non viene ricaricata, lasciando all'utente i valori inseriti in precedenza
        // SVANTAGGIO: Approccio più lungo da gestire manualmente
        // N.B. ---> Il nome del campo inserito nel form data con il metodo append() deve essere UGUALE all'id dell'input corrispondente

        let formData = new FormData();
        let fileLogo = document.getElementById("logo").files[0];
        let nome = $("#nome").val();
        let acronimo = $("#acronimo").val();
        let annoDiFondazione = $("#annoDiFondazione").val();
        let provincia = $("#provincia").val();
        let citta = $("#citta").val();
        let indirizzo = $("#indirizzo").val();
        let numeroCivico = $("#numeroCivico").val();
        let numeroDiTelefono = $("#numeroDiTelefono").val();
        let email = $("#email").val();
        let pec = $("#pec").val();
        let sitoWeb = $("#sitoWeb").val();
        $("#titoloAlertModificaAssociazione").text(nome);

        console.log(fileLogo);
        if (fileLogo == undefined && $(".mantieniLogo").prop("checked")) {
            console.log("Mantieni logo");
        } else {
            formData.append("logo", fileLogo);
        }

        formData.append("nome", nome);
        formData.append("acronimo", acronimo);
        formData.append("annoDiFondazione", annoDiFondazione);
        formData.append("provincia", provincia);
        formData.append("citta", citta);
        formData.append("indirizzo", indirizzo);
        formData.append("numeroCivico", numeroCivico);
        formData.append("numeroDiTelefono", numeroDiTelefono);
        formData.append("email", email);
        formData.append("pec", pec);
        formData.append("sitoWeb", sitoWeb);

        $.ajax({
            type: "POST",
            url: "/associazione/inserisciAssociazione",
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                $("#associazioneInfoOK").click();
                $(".overlay-wrapper").append(`
                        <div class="overlay">
                        <i class="mr-3 fas fa-3x fa-sync-alt fa-spin"></i>
                            <div class="text-bold pt-2">Attendi il caricamento...</div>
                        </div>
                      `);
                setTimeout(function () {
                    window.location = "/associazione/laMiaAssociazione";
                }, 3000);
            },
            error: function () {
                $("#associazioneInfoKO").click();
            }
        });

        // FINE APPROCCIO 2
    });
}

function getValoriAssociazione() {
    $.ajax({
        type: "GET",
        url: "/associazione/getValoriAssociazione",
        success: function (data) {
            let logo = data.logo.fileName + "." + data.logo.fileType.split("/")[1];
            let nome = data.nome;
            let acronimo = data.proprieta[0].valoreProprieta;
            let annoDiFondazione = data.proprieta[1].valoreProprieta;
            let provincia = data.proprieta[2].valoreProprieta;
            let citta = data.proprieta[3].valoreProprieta;
            let indirizzo = data.proprieta[4].valoreProprieta;
            let numeroCivico = data.proprieta[5].valoreProprieta;
            let numeroDiTelefono = data.proprieta[6].valoreProprieta;
            let email = data.proprieta[7].valoreProprieta;
            let pec = data.proprieta[8].valoreProprieta;
            let sitoWeb = data.proprieta[9].valoreProprieta;

            if (logo != null) {
                $("#gestoreFileLogo").attr("placeholder", logo);
            }
            if (nome != null) {
                $("#nome").val(nome);
            }
            if (acronimo != null) {
                $("#acronimo").val(acronimo);
            }
            if (annoDiFondazione != null) {
                $("#annoDiFondazione").val(annoDiFondazione);
            }
            if (provincia != null) {
                $("#provincia").val(provincia);
            }
            if (citta != null) {
                $("#citta").val(citta);
            }
            if (indirizzo != null) {
                $("#indirizzo").val(indirizzo);
            }
            if (numeroCivico != null) {
                $("#numeroCivico").val(numeroCivico);
            }
            if (numeroDiTelefono != null) {
                $("#numeroDiTelefono").val(numeroDiTelefono);
            }
            if (email != null) {
                $("#email").val(email);
            }
            if (pec != null) {
                $("#pec").val(pec);
            }
            if (sitoWeb != null) {
                $("#sitoWeb").val(sitoWeb);
            }
        }
    });
}

function gestoreAlertLogo() {
    $("#logo").change(function () {
        var immagineLogo = new Image;
        immagineLogo.src = window.URL.createObjectURL(this.files[0]);
        immagineLogo.onload = function () {
            console.log("Width and height = " + this.width + " " + this.height);
            /*if (this.width != 512 || this.height != 512) {
             $("#logo").val("");
             $("#dimensioniLogoKO").click();
             } else {*/
            //$("#dimensioniLogoOK").click();
            $("#logoAlertModificaAssociazione").attr("src", immagineLogo.src);
            //}
        };
    });
}

function gestoreCheckboxLogo(idLogoAssociazione) {
    let logo;
    let logoAlert = $("#logoAlertModificaAssociazione").attr("src");
    
    if (idLogoAssociazione != 0) {
        $.ajax({
            type: "POST",
            url: "/getLogoById",
            data: {idLogoAssociazione},
            success: function (data) {
                console.log(data);
                logo = data;
            },
            error: function () {

            }
        });
    }
    
    

    if ($(".mantieniLogo").prop("checked")) {
     $("#logo").prop("disabled", true);
     $("#logo").val("");
     }

    $(".mantieniLogo").change(function () {
        if ($(".mantieniLogo").prop("checked")) {
            $("#logo").prop("disabled", true);
            $("#logo").val("");
            $("#gestoreFileLogo").attr("placeholder", logo.fileName + "." + logo.fileType.split("/")[1]);
            $("#logoAlertModificaAssociazione").attr("src", logoAlert);
        } else {
            $("#logo").prop("disabled", false);
            $("#gestoreFileLogo").removeAttr("placeholder");
        }
    });
}

function gestoreListeDropdownAttivita() {
    $.ajax({
        type: "GET",
        url: "/attivita/listaSport",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                $("#selectSport,#sport").append("<option value='" + data[i].id + "'>" + data[i].nomeSport + "</option>");
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "/attivita/listaDisabilita",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                $("#selectDisabilita,#disabilita").append("<option value='" + data[i].id + "'>" + data[i].nomeDisabilita + "</option>");
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "/attivita/listaProprieta",
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                $("#selectOrdinamento").append("<option>" + data[i] + "</option>");
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "/gestioneAttivita/listaAssociazioniAttive",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                if(data[i].nome != null) {
                    $("#associazione").append("<option value='" + data[i].id + "'>" + data[i].nome + "</option>");
                }
            }
        }
    });
}

function gestoreLinkPrimoAccesso(isLogoNull) {
    if(isLogoNull) {
        $(".linkButtonSidebar").click(function(e) {
            e.preventDefault();
            $("#linkSidebarBloccati").click();
        });
    }
}