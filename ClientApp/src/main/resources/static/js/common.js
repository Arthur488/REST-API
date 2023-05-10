function showModal(id) {
    $("#yesButton").attr("href", "products/delete/" + id);
    $("#confirmText").text("Are you sure to delete this product with ID: " + id)
    $("#exampleModal").modal();
}

function makeAJAXCall(id) {
    $.ajax({
        url: "products/edit/" + id, // URL-адрес серверного эндпоинта
        type: "GET", // Метод запроса
        success: function(response) { // Обработчик успешного выполнения запроса
            console.log(response)
            // Обновление содержимого элемента "result"
            $("#edit_content").html(response);
        },
        error: function(xhr, status, error) { // Обработчик ошибки
            console.log(error);
        }
    });
}

