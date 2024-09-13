
// OPZIONE 1
/*window.onresize = window.onload = function() {
 gestoreVisibilitaSidebar();
 };*/

$(document).ready(function () {

    gestoreEspansioneButton();
    gestoreVisibilitaSidebar();
    gestoreIconaTopBarAdmin();
    gestoreFileLogo();
    gestoreAlertAssociazioneOK();
    gestoreAlertAnimati();
    inviaMessaggioAssistenza();

    $('[data-mask]').inputmask();
});

function gestoreEspansioneButton() {
    $("#espansioneButton").click(function() {
        if($(this).children("i").hasClass("fa-expand-arrows-alt")) {
            $(this).children("span").text("Riduci");
        } else {
            $(this).children("span").text("Espandi");
        }
    });
}

function gestoreVisibilitaSidebar() {
    // OPZIONE 1
    /*let checkVisibilita = $("body").hasClass("sidebar-closed sidebar-collapse");
     if(checkVisibilita) {
     $("#bottoneMostraSidebar").removeClass("d-none");
     } else {
     $("#bottoneMostraSidebar").addClass("d-none");
     }*/

    // OPZIONE 2
    setInterval(function () {
        let checkVisibilita = $("body").hasClass("sidebar-closed sidebar-collapse");
        if (checkVisibilita) {
            $("#bottoneMostraSidebar").removeClass("d-none");
        } else {
            $("#bottoneMostraSidebar").addClass("d-none");
        }
    }, 100);
}

function gestoreIconaTopBarAdmin() {
    setInterval(function() {
        if($("#opzioniTopBarAdmin").hasClass("show")) {
            $("#iconaTopBarAdmin").removeClass("fa-door-closed").addClass("fa-door-open");
        } else {
            $("#iconaTopBarAdmin").removeClass("fa-door-open").addClass("fa-door-closed");
        }
    }, 50);
}

function gestoreFileLogo() {
    $("#gestoreFileLogo").on({
        click: function () {
            $("#logo").trigger("click");
        }
    });

    $("#logo").change(function () {
        let percorsoLogo = $(this).val();
        $("#gestoreFileLogo").attr("placeholder", percorsoLogo.substr(12));
    });
}

function gestoreAlertAssociazioneOK() {
    $("#associazioneInfoOK").click(function () {
        $("#alertAssociazioneInfoOK").addClass("show");

        setTimeout(function () {
            $("#alertAssociazioneInfoOK").removeClass("show");
        }, 3000);
    });
}

function gestoreAlertAnimati() {
    var Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 8000
    });

    $('#inserisciAttivitaOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'attività è stata creata correttamente ed è stata aggiunta alla lista."
        });
    });
    $('#inserisciAttivitaKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nell'inserimento dell'attività."
        });
    });
    $('#modificaAttivitaOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'attività è stata modificata correttamente e i suoi valori sono cambiati all'interno della lista."
        });
    });
    $('#modificaAttivitaKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nella modifica dell'attività."
        });
    });
    $('#eliminaAttivitaOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'attività è stata eliminata correttamente ed è stata tolta dalla lista."
        });
    });
    $('#dimensioniLogoOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: 'Il logo inserito è delle dimensioni richieste. Inserire le altre informazioni ed inviare il form.'
        });
    });
    $('#associazioneInfoKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! Le informazioni richieste non sono state inserite correttamente."
        });
    });
    $('#dimensioniLogoKO').click(function () {
        Toast.fire({
            icon: 'warning',
            title: "Il logo inserito non è delle dimensioni richieste. L'invio del form non andrà a buon fine."
        });
    });
    $('#linkSidebarBloccati').click(function () {
        Toast.fire({
            icon: 'warning',
            title: "Le funzionalità del gestionale non saranno disponibili finchè non verranno inserite le informazioni richieste."
        });
    });
    $('#invioMessaggioOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "Il messaggio è stato inviato correttamente. La ringraziamo per la sua collaborazione."
        });
    });
    $('#invioMessaggioKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nell'invio del messaggio. Compilare tutti i campi e riprovare."
        });
    });
    $('#inserisciSportOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "Lo sport inserito è stato aggiunto correttamente alla lista. Ora è selezionabile durante l'inserimento di un'attività."
        });
    });
    $('#inserisciSportKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nell'inserimento dello sport. Verificare che non sia già presente nella lista e riprovare."
        });
    });
    $('#eliminaSportOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "Lo sport inserito è stato rimosso correttamente dalla lista. Ora non è più selezionabile durante l'inserimento di un'attività."
        });
    });
    $('#eliminaSportKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nella rimozione dello sport. Non è stato trovato nessuno sport con quel nome."
        });
    });
    $('#inserisciDisabilitaOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "La disabilità inserita è stata aggiunta correttamente alla lista. Ora è selezionabile durante l'inserimento di un'attività."
        });
    });
    $('#inserisciDisabilitaKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nell'inserimento della disabilità. Verificare che non sia già presente nella lista e riprovare."
        });
    });
    $('#eliminaDisabilitaOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "La disabilità inserita è stata rimossa correttamente dalla lista. Ora non è più selezionabile durante l'inserimento di un'attività."
        });
    });
    $('#eliminaDisabilitaKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nella rimozione della disabilità. Non è stata trovata nessuna disabilità con quel nome."
        });
    });
    $('#creaAssociazioneOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'associazione inserita è stata creata correttamente. Potrà essere visibile nella lista delle associazioni."
        });
    });
    $('#creaAssociazioneKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nella creazione dell'associazione. Compilare tutti i campi e riprovare."
        });
    });
    $('#modificaAssociazioneOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'associazione è stata modificata correttamente e i suoi valori sono cambiati all'interno della lista."
        });
    });
    $('#modificaAssociazioneKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore nella modifica dell'associazione."
        });
    });
    $('#eliminaAssociazioneOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "L'associazione è stata eliminata correttamente ed è stata tolta dalla lista."
        });
    });
    $('#impostaPasswordOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "La password inserita è stata impostata correttamente. Bisognerà inserire la nuova password dal prossimo accesso in poi."
        });
    });
    $('#impostaPasswordKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! C'è stato un errore durante l'impostazione della nuova password. Inserirne un'altra e riprovare."
        });
    });
    $('#passwordDimenticataOK').click(function () {
        Toast.fire({
            icon: 'success',
            title: "Controllare la casella di posta elettronica. Se non viene visualizzata nessuna mail, controllare nella cartella \"Spam\"."
        });
    });
    $('#passwordDimenticataKO').click(function () {
        Toast.fire({
            icon: 'error',
            title: "Ops! L'email inserita non corrisponde a nessun account. Inserirne un'altra e riprovare."
        });
    });
}

function inviaMessaggioAssistenza() {
    $("#inviaMessaggioButton").click(function () {
        let messaggio = $("#formContattaciMessaggio").val();

        $.ajax({
            type: "POST",
            url: "/assistenza/inviaMessaggio",
            data: {messaggio},
            success: function () {
                $("#invioMessaggioOK").click();
            },
            error: function () {
                $("#invioMessaggioKO").click();
            }
        });

        messaggio = $("#formContattaciMessaggio").val("");
    });
}