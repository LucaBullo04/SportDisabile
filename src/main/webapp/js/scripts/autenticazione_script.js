$(document).ready(function () {
    $(".submitButton").addClass("btn btn-block text-light bg-info");
    $(".componenteForm").addClass("form-control border border-dark");

    controllaMostraPassword();
    resettaMostraPassword();
    passwordDimenticata();
});

function impostaPassword() {
    $("#impostaPasswordButton").click(function () {
        let passwordNuova = $("#passwordNuova").val();
        $.ajax({
            type: "POST",
            url: "/impostaPasswordUtente",
            data: {passwordNuova},
            success: function () {
                if ($(".mostraPassword").prop("checked")) {
                    $(".mostraPassword").prop("checked", false);
                    $("#password, #passwordNuova").attr("type", "password");
                }

                $("#impostaPasswordOK").click();
                $("#impostaPasswordButton").prop("disabled", true);
                $(".overlay-wrapper").append(`
                        <div class="overlay">
                        <i class="mr-3 fas fa-3x fa-sync-alt fa-spin"></i>
                            <div class="text-bold pt-2">Attendi il caricamento...</div>
                        </div>
                      `);
                setTimeout(function () {
                    window.location = "/associazione/modificaAssociazione";
                }, 3000);
            },
            error: function () {
                $("#impostaPasswordKO").click();
            }
        });
    });
}

function controllaMostraPassword() {
    $(".mostraPassword").change(function () {
        if ($(this).prop("checked")) {
            $("#password, #passwordNuova").attr("type", "text");
        } else {
            $("#password, #passwordNuova").attr("type", "password");
        }
    });
}

function resettaMostraPassword() {
    if ($(".mostraPassword").prop("checked")) {
        $(".mostraPassword").change();
    }
}

function passwordDimenticata() {
    $(".submitButton").click(function() {
        let email = $("#email").val();
        
        $.ajax({
            type: "POST",
            url: "/passwordDimenticata",
            data: {email},
            success: function() {
                $("#passwordDimenticataOK").click();
            },
            error: function() {
                $("#passwordDimenticataKO").click();
            }
        });
    });
}