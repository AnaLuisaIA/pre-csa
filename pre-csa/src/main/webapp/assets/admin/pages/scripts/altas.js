$("#es_colaborador").change(function () {
    cambiosColaborador();
});

function cambiosColaborador() {
    //ES COLABORADOR
    if ($("#es_colaborador").val() == '1') {
 
        $("#username").attr('disabled', "${'true'}");
        $("#password").attr('disabled', "${'true'}");
        $("#email").attr('disabled', "${'true'}");
        $("#nombreUser").attr('disabled', "${'true'}");
        $("#aPaterno").attr('disabled', "${'true'}");
        $("#aMaterno").attr('disabled', "${'true'}");

        $("#inputNumColaborador").removeAttr('disabled');
    }
    //NO ES COLABORADOR
    else {
        $("#inputNumColaborador").attr('disabled', "${'true'}");

        $("#username").removeAttr('disabled');
        $("#password").removeAttr('disabled');
        $("#email").removeAttr('disabled');
        $("#nombreUser").removeAttr('disabled');
        $("#aPaterno").removeAttr('disabled');
        $("#aMaterno").removeAttr('disabled');

    }
};