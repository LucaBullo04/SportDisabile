$(document).ready(function () {
    moment().format();
    
    /*inserisciSport();
    eliminaSport();
    inserisciDisabilita();
    eliminaDisabilita();*/
    inserisciAssociazione();
    listaAssociazioni();
    eliminaAssociazione();
    modificaAssociazionePage();
    modificaAssociazione();
});

/*function inserisciSport() {
    $("#inserisciSport").click(function () {
        let nomeSport = $("#sport").val();
        $("#tabellaSport").removeClass("d-none");

        $.ajax({
            type: "POST",
            url: "/gestioneAttivita/inserisciSport",
            data: {nomeSport},
            success: function () {
                $("#inserisciSportOK").click();
                $("#bodyTabellaSport").append(`
                        <tr>
                            <td class="rigaTabellaSport col-2 text-center" style="vertical-align: middle">${nomeSport}</td>
                        </tr>
                `);
            },
            error: function () {
                $("#inserisciSportKO").click();
            }
        });
    });
}

function eliminaSport() {
    $("#eliminaSport").click(function () {
        let nomeSport = $("#sport").val();

        $.ajax({
            type: "POST",
            url: "/gestioneAttivita/eliminaSport",
            data: {nomeSport},
            success: function () {
                $("#eliminaSportOK").click();
                for (let sport of $(".rigaTabellaSport")) {
                    if (sport.innerHTML == nomeSport) {
                        sport.parentNode.remove();
                    }
                }
                if ($(".rigaTabellaSport").length == 0) {
                    $("#tabellaSport").addClass("d-none");
                }
            },
            error: function () {
                $("#eliminaSportKO").click();
            }
        });
    });
}

function inserisciDisabilita() {
    $("#inserisciDisabilita").click(function () {
        let nomeDisabilita = $("#disabilita").val();
        $("#tabellaDisabilita").removeClass("d-none");

        $.ajax({
            type: "POST",
            url: "/gestioneAttivita/inserisciDisabilita",
            data: {nomeDisabilita},
            success: function () {
                $("#inserisciDisabilitaOK").click();
                $("#bodyTabellaDisabilita").append(`
                        <tr>
                            <td class="rigaTabellaDisabilita col-2 text-center" style="vertical-align: middle">${nomeDisabilita}</td>
                        </tr>
                `);
            },
            error: function () {
                $("#inserisciDisabilitaKO").click();
            }
        });
    });
}

function eliminaDisabilita() {
    $("#eliminaDisabilita").click(function () {
        let nomeDisabilita = $("#disabilita").val();

        $.ajax({
            type: "POST",
            url: "/gestioneAttivita/eliminaDisabilita",
            data: {nomeDisabilita},
            success: function () {
                $("#eliminaDisabilitaOK").click();
                for (let disabilita of $(".rigaTabellaDisabilita")) {
                    if (disabilita.innerHTML == nomeDisabilita) {
                        disabilita.parentNode.remove();
                    }
                }
                if ($(".rigaTabellaDisabilita").length == 0) {
                    $("#tabellaDisabilita").addClass("d-none");
                }
            },
            error: function () {
                $("#eliminaDisabilitaKO").click();
            }
        });
    });
}*/

function inserisciAssociazione() {
    $("#inserisciAssociazione").click(function () {
        $(".overlay-wrapper").append(`
                <div class="overlay">
                    <i class="mr-3 fas fa-3x fa-sync-alt fa-spin"></i>
                        <div class="text-bold pt-2">Attendi il caricamento...</div>
                </div>
            `);

        let username = $("#username").val();
        let partitaIva = $("#partitaIva").val();
        let durataAccount = $("#durataAccount").val();
        // let durataAccount = $("#durataAccount option:selected").text(); // QUESTA OPERAZIONE VA ESEGUITA PER AVERE IL VALORE SCRITTO DELLA OPTION E NON IL VALUE, QUALORA I DUE NON FOSSERO UGUALI
        // let attivazioneAccount = moment().format("DD/MM/YYYY"); // VARIABILE DI TIPO STRING DA UTILIZZARE IN GRAFICA
        // let attivazioneAccount = moment().toDate(); // VARIABILE DI TIPO OBJECT (DATE) DA USARE PER LA MANIPOLAZIONE DEI DATI E IL SALVATAGGIO IN DB. RITORNA LA DATA ATTUALE
        let scadenzaAccount = calcolaScadenzaAccount(durataAccount, false);

        $.ajax({
            type: "POST",
            url: "/creaUtenteAssociazione",
            data: {username, partitaIva, scadenzaAccount},
            success: function () {
                $(".overlay").remove();
                $("#creaAssociazioneOK").click();
            },
            error: function () {
                $(".overlay").remove();
                $("#creaAssociazioneKO").click();
            }
        });

        username = $("#username").val("");
        partitaIva = $("#partitaIva").val("");
    });
}

function listaAssociazioni() {
    let isAttiva;
    
    $("#buttonAssociazioniAttive").click(function () {
        isAttiva = true;
        $.ajax({
            type: "POST",
            url: "/listaRowAssociazioni",
            data: {isAttiva},
            success: function (data) {
                creaTabellaAssociazioni(data);
            }
        });
    });
    $("#buttonAssociazioniDisattivate").click(function () {
        isAttiva = false;
        $.ajax({
            type: "POST",
            url: "/listaRowAssociazioni",
            data: {isAttiva},
            success: function (data) {
                creaTabellaAssociazioni(data);
            }
        });
    });
    
    //$("#buttonAssociazioniAttive").click();
}

function creaTabellaAssociazioni(data) {
    let logoBase64 = "";
    let nomeAssociazione = "";
    $("#bodyTabellaAssociazioni").empty(); //Se ci sono associazioni le tolgo
    $("#tabellaAssociazioni").removeClass("d-none");
    $("#messaggioTabellaVuota").remove(); // Se non ci sono associazioni elimino il messaggio
    $("#messaggioIniziale").remove(); // Entrambi i casi elimino il messaggio
    if (data.length > 0) {
        console.log(data);
        for (let rowAssociazione of data) {
            //console.log(rowAssociazione);
            if (rowAssociazione.logo != null) {
                logoBase64 = "data:image/jpg;base64," + rowAssociazione.logo.dataBase64;
            } else {
                logoBase64 = "../../images/logo_associazione_placeholder.png";
            }
            if (rowAssociazione.nome != null) {
                nomeAssociazione = rowAssociazione.nome;
            } else {
                nomeAssociazione = "//";
            }

            $("#bodyTabellaAssociazioni").append(`
                            <tr class="rigaTabellaAssociazione" id="${rowAssociazione.id}">
                                <td id="cellaLogo" class="col-2" style="vertical-align: middle">
                                    <div class="image imageDiv">
                                        <img alt="logo" src="${logoBase64}" class="img-circle elevation-2 mx-auto d-block" height="70px" width="70px">
                                    </div>
                                </td>
                                <td class="col-2 text-center cellaNomeAssociazione" style="vertical-align: middle">${nomeAssociazione}</td>
                                <td class="col-2 text-center cellaUsername" style="vertical-align: middle">${rowAssociazione.utente.username}</td>
                                <td class="col-2 text-center cellaPartitaIVA" style="vertical-align: middle">${rowAssociazione.partitaIva}</td>
                                <td class="col-2 text-center cellaScadenzaAccount" style="vertical-align: middle">${moment(rowAssociazione.utente.scadenzaAccount).format("DD/MM/YYYY")}</td>
                                <td class="col-2 cellaGestisciAssociazione" style="vertical-align: middle">
                                    <div class="row justify-content-center">
                                        <div><i class="fas fa-info-circle mr-3 text-secondary infoAssociazione" onmouseenter="$(this).addClass('text-info');" onmouseleave="$(this).removeClass('text-info');"></i></div>
                                        <div><i class="fas fa-pencil-alt mr-3 text-secondary modificaAssociazione" onmouseenter="$(this).addClass('text-warning');" onmouseleave="$(this).removeClass('text-warning');"></i></div>
                                        <div><i class="fas fa-times-circle text-secondary eliminaAssociazione" onmouseenter="$(this).addClass('text-danger');" onmouseleave="$(this).removeClass('text-danger');"></i></div>
                                    </div>
                                </td>
                            </tr>
                `);
        }
        
        modificaAssociazionePage();
        eliminaAssociazione();
        getInfoAssociazione();
    } else {
        $("#tabellaAssociazioni").addClass("d-none");
        
        $("#bodyCardListaAssociazioni").append(`
                            <div id="messaggioTabellaVuota" class="d-flex flex-wrap justify-content-center align-items-center h-100">
                                <p class="font-italic">Nessuna associazione presente nel database.</p>
                            </div>
                        `);
    }
}

function getInfoAssociazione() {
    setTimeout(function () {
        $(".infoAssociazione").click(function () {
            let associazioneId = $(this).parents(".rigaTabellaAssociazione").attr("id");
            
            $("#associazioneId").val(associazioneId);
            $("#formAssociazioneId").attr("action", "/getInfoAssociazione");
            $("#formAssociazioneId").submit();
        });
    }, 100);
}

function eliminaAssociazione() {
    setTimeout(function () {
        $(".eliminaAssociazione").click(function () {
            let associazioneId = $(this).parents(".rigaTabellaAssociazione").attr("id");
            $.ajax({
                type: "POST",
                url: "/eliminaAssociazione",
                data: {associazioneId},
                success: function () {
                    $("#eliminaAssociazioneOK").click();
                    $("#" + associazioneId).remove();

                    if ($(".rigaTabellaAssociazione").length == 0) {
                        $("#tabellaAssociazioni").addClass("d-none");
                        $("#bodyCardListaAssociazioni").append(`
                                    <div id="messaggioTabellaVuota" class="d-flex flex-wrap justify-content-center align-items-center h-100">
                                        <p class="font-italic">Tutte le associazioni sono state eliminate.</p>
                                    </div>
                                 `);
                    }
                }
            });
        });
    }, 100);
}

function modificaAssociazionePage() {
    setTimeout(function () {
        $(".modificaAssociazione").click(function () {
            let associazioneId = $(this).parents(".rigaTabellaAssociazione").attr("id");
            console.log(associazioneId);

            $("#associazioneId").val(associazioneId);
            $("#formAssociazioneId").attr("action", "/modificaAssociazione");
            $("#formAssociazioneId").submit();
        });
    }, 100);         
}

function getValoriAssociazione() {
    let associazioneId = $("#associazioneId").val();

    if (associazioneId != 0) {
        setTimeout(function () {
            $.ajax({
                type: "POST",
                url: "/getAssociazioneById",
                data: {associazioneId},
                success: function (data) {
                    console.log(data);

                    $("#username").val(data.utente.username);
                    $("#partitaIva").val(data.partitaIva);
                    $("#scadenzaAccount").val(data.utente.scadenzaAccount);
                }
            });
        }, 10);
    }
}

function modificaAssociazione() {
    $("#modificaAssociazione").click(function () {
        let associazioneId = $("#associazioneId").val();
        let nuovoUsername = $("#username").val();
        let nuovaPartitaIva = $("#partitaIva").val();
        let nuovaDurataAccount;
        let nuovaScadenzaAccount;

        if ($(".nonRinnovare").prop("checked")) {
            nuovaDurataAccount = "";
        } else {
            nuovaDurataAccount = $("#durataAccount").val();
            nuovaScadenzaAccount = calcolaScadenzaAccount(nuovaDurataAccount, true);
        }

        $.ajax({
            type: "POST",
            url: "/cambiaValoriAssociazione",
            data: {associazioneId, nuovoUsername, nuovaPartitaIva, nuovaScadenzaAccount},
            success: function () {
                $("#modificaAssociazioneOK").click();
                $(".overlay-wrapper").append(`
                        <div class="overlay">
                        <i class="mr-3 fas fa-3x fa-sync-alt fa-spin"></i>
                            <div class="text-bold pt-2">Attendi il caricamento...</div>
                        </div>
                      `);
                setTimeout(function () {
                    window.location = "/listaAssociazioni";
                }, 3000);
            },
            error: function () {
                $("#modificaAssociazioneKO").click();
            }
        });
    });
}

function calcolaScadenzaAccount(durataAccount, isModifica) {
    let scadenzaAccount;
    let dataDiPartenza = moment();

    if (isModifica && moment($("#scadenzaAccount").val()).isSameOrAfter(dataDiPartenza)) {
        dataDiPartenza = moment($("#scadenzaAccount").val());
    }

    console.log(dataDiPartenza.format("DD/MM/YYYY"));

    switch (durataAccount) {
        case "1 mese":
            scadenzaAccount = dataDiPartenza.add(1, "M");
            //console.log("Account mensile = " + scadenzaAccount.format("DD/MM/YYYY"));
            break;
        case "3 mesi":
            scadenzaAccount = dataDiPartenza.add(3, "M");
            //console.log("Account trimestrale = " + scadenzaAccount.format("DD/MM/YYYY"));
            break;
        case "6 mesi":
            scadenzaAccount = dataDiPartenza.add(6, "M");
            //console.log("Account semestrale = " + scadenzaAccount.format("DD/MM/YYYY"));
            break;
        case "1 anno":
            scadenzaAccount = dataDiPartenza.add(1, "y");
            //console.log("Account annuale = " + scadenzaAccount.format("DD/MM/YYYY"));
            break;
    }

    console.log(scadenzaAccount.format("DD/MM/YYYY"));
    return scadenzaAccount.toDate();
}

/*function listaSport() {
 $("#bodyTabellaSport").empty();
 
 $.ajax({
 type: "GET",
 url: "/attivita/listaSport",
 success: function (data) {
 if (data != 0) {
 for (let sport of data) {
 $("#bodyTabellaSport").append(`
 <tr>
 <td class="rigaTabellaSport col-2 text-center" style="vertical-align: middle">${sport.nomeSport}</td>
 </tr>
 `);
 }
 } else {
 $("#tabellaSport").addClass("d-none");
 }
 }
 });
 }
 
 function listaDisabilita() {
 $("#bodyTabellaDisabilita").empty();
 
 $.ajax({
 type: "GET",
 url: "/attivita/listaDisabilita",
 success: function (data) {
 if (data != 0) {
 for (let disabilita of data) {
 $("#bodyTabellaDisabilita").append(`
 <tr>
 <td class="rigaTabellaDisabilita col-2 text-center" style="vertical-align: middle">${disabilita.nomeDisabilita}</td>
 </tr>
 `);
 }
 } else {
 $("#tabellaDisabilita").addClass("d-none");
 }
 }
 });
 }*/

function gestoreCheckboxRinnovoAccount() {
    $(".nonRinnovare").change(function () {
        checkRinnovoAccount();
    });

}

function checkRinnovoAccount() {
    if ($(".nonRinnovare").prop("checked")) {
        $("#divDurataAccount").addClass("invisible");
    } else {
        $("#divDurataAccount").removeClass("invisible");
    }
}