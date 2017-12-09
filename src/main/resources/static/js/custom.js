var page, size;

function onlyNumbers(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]|\./;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}

function phoneNumber (evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /^[0-9 ()+-]+$/;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}


function onlyLathinic(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /^[a-zA-Z0-9 ]+$/;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}

function onlyLetters(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /^[a-zA-Zа-яА-Яа-щА-ЩЬьЮюЯяЇїІіЄєҐґ`' ]+$/;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}


function changePage(page, size) {
    $('#page').val(page);
    $('#size').val(size);
    $('#filters').submit();
}

function selectRealtor(id) {
    $('#selectRealtorId').val(id);
    $('#filters').submit();
}

function selectOwner(id) {
    $('#selectOwnerId').val(id);
    $('#filters').submit();
}

function selectClient(id) {
    $('#selectClientId').val(id);
    $('#filters').submit();
}

function setActiveMenu() {
    var CURRENT_ = window.location.pathname.split('/')[1];
    $('#' + CURRENT_).addClass('active');

}

$(document).ready(function () {
    setActiveMenu();
});