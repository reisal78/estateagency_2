function searchContracts(page, size) {
    $('#page').val(page);
    $('#size').val(size);
    $('#filters').submit();
}

function showClientsList() {
    searchClients();
    $('#clientsSearch').removeClass('hidden');
}

function searchClients(page, size) {
    this.page = page;
    this.size = size;
    var search = $("#search").val();
    if (search === '') {
        $('#clearSearch').addClass('hidden');
    } else {
        $('#clearSearch').removeClass('hidden');
    }
    $.ajax({
        url: '/clients/api/search',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: ({
            'search': search,
            'page': page,
            'size': size
        }),
        success: function (data) {
            $("#searchResult > tbody").empty();
            for (var i = 0; i < data.content.length; i++) {
                $("#searchResult > tbody:last-child")
                    .append(
                        '<tr onclick="selectClientFromContract(' + data.content[i].id + ')" style="cursor: pointer">' +
                        '<td>' + (i + 1 + (data.number * data.size)) + '</td>' +
                        '<td>' +
                        data.content[i].lastName +
                        ' ' + data.content[i].firstName +
                        ' ' + data.content[i].surName +
                        '</td>' +
                        '<td>' + data.content[i].passport + '</td>' +
                        '</tr>'
                    )
                ;
            }

            $("#pagination").empty();

            $("#pagination").append(
                '<li' + ((data.first) ? ' class="disabled"' : '') + ' style="display:inline">' +
                (data.first ? '<span>&laquo;</span>' : '<a href="javascript:void(0)" onclick="searchClients(' +
                    0 + ',' + data.size + ')">' + '&laquo;' + '</a>') +
                '</li>'
            );
            for (var i = data.start; i <= data.finish; i++) {
                $("#pagination").append(
                    '<li' + (i == data.number ? ' class="active"' : '') + ' style="display:inline">' +
                    (i == data.number ? '<span>' + (i + 1) + '</span>' : '<a href="javascript:void(0)" onclick="searchClients(' +
                        i + ',' + data.size + ')">' + (i + 1) + '</a>') +
                    '</li>'
                );
            }
            $("#pagination").append(
                '<li' + ((data.last) ? ' class="disabled"' : '') + ' style="display:inline">' +
                (data.last ? '<span>&raquo;</span>' : '<a href="javascript:void(0)" onclick="searchClients(' +
                    (data.totalPages - 1) + ',' + data.size + ')">' + '&raquo;' + '</a>') +
                '</li>'
            );


        }
    });

}


function closeClientsList() {
    $('#clientsSearch').addClass('hidden');
}

function clearClientSearch() {
    $("#search").val('');
    searchClients();
}

function selectClientFromContract(id) {
    $.ajax({
        url: '/clients/api/getOne',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: ({
            'id': id
        }),
        success: function (data) {
            $("#clientId").val(data.id);
            $("#client").val(data.lastName + " " + data.firstName + " " + data.surName)
        }
    });
}