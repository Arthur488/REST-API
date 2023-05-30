function showModal(id) {
    $("#yesButton").attr("href", "products/delete/" + id);
    $("#confirmText").text("Are you sure to delete this product with ID: " + id)
    $("#exampleModal").modal();
}

function makeAJAXCall(url) {
    $.ajax({
        url: url,
        type: "GET",
        success: function (response) {
            $("#edit_content").html(response);
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}






