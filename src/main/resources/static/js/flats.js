$('#browseImage').click(function () {
    $('input[type=file]').trigger('click');
});

$('input[type=file]').change(function () {
    var fileName = $(this).val().split('\\').pop();
    $('#imageName').val(fileName);
    if (fileName !== '') {
        $('#uploadImage').removeClass('disabled');
        $('#uploadImage').prop('disabled', false);
    } else {
        $('#uploadImage').prop('disabled', true);
    }
});

function uploadFile() {
    $('#uploadForm').submit();
}

function removeImage(imageId) {
    $.get("/flats/images/remove", {'id': imageId}).done(function (data) {
        $('#' + imageId).remove();
    });
}

function searchFlats(page, size) {
    $('#page').val(page);
    $('#size').val(size);
    $('#filters').submit();
}


function showOwnersList() {
    searchOwners();
    $('#ownersSearch').removeClass('hidden');

}

function clearOwnerSearch() {
    $("#search").val('');
    searchOwners();
}

function selectOwnerFromFlat(id) {
    $.ajax({
        url: '/owners/api/getOne',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: ({
            'id': id
        }),
        success: function (data) {
            $("#ownerId").val(data.id);
            $("#owner").val(data.lastName + " " + data.firstName + " " + data.surName)
        }
    });
}

function closeOwnersList() {
    $('#ownersSearch').addClass('hidden');
}

function searchOwners(page, size) {
    this.page = page;
    this.size = size;
    var search = $("#search").val();
    if (search === '') {
        $('#clearSearch').addClass('hidden');
    } else {
        $('#clearSearch').removeClass('hidden');
    }
    $.ajax({
        url: '/owners/api/search',
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
                        '<tr onclick="selectOwnerFromFlat(' + data.content[i].id + ')" style="cursor: pointer">' +
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
                (data.first ? '<span>&laquo;</span>' : '<a href="javascript:void(0)" onclick="searchOwners(' +
                    0 + ',' + data.size + ')">' + '&laquo;' + '</a>') +
                '</li>'
            );
            for (var i = data.start; i <= data.finish; i++) {
                $("#pagination").append(
                    '<li' + (i == data.number ? ' class="active"' : '') + ' style="display:inline">' +
                    (i == data.number ? '<span>' + (i + 1) + '</span>' : '<a href="javascript:void(0)" onclick="searchOwners(' +
                        i + ',' + data.size + ')">' + (i + 1) + '</a>') +
                    '</li>'
                );
            }
            $("#pagination").append(
                '<li' + ((data.last) ? ' class="disabled"' : '') + ' style="display:inline">' +
                (data.last ? '<span>&raquo;</span>' : '<a href="javascript:void(0)" onclick="searchOwners(' +
                    (data.totalPages - 1) + ',' + data.size + ')">' + '&raquo;' + '</a>') +
                '</li>'
            );


        }
    });

}