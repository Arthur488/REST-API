function showModal(id) {
    $("#yesButton").attr("href", "products/delete/" + id);
    $("#confirmText").text("Are you sure to delete this product with ID: " + id)
    $("#exampleModal").modal();
}

function makeAJAXCall(id) {
    $.ajax({
        url: "products/edit/" + id, // URL-����� ���������� ���������
        type: "GET", // ����� �������
        success: function(response) { // ���������� ��������� ���������� �������
            console.log(response)
            // ���������� ����������� �������� "result"
            $("#edit_content").html(response);
        },
        error: function(xhr, status, error) { // ���������� ������
            console.log(error);
        }
    });
}

