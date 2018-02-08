var page = {
    start: 0,
    size: 10,
    orderBy: "create_time",
    orderType: "desc"

};
var query = {};

function buildTable(tableContainer, list, fields) {
    $.each(list, function (index, item) {
        var row = makeRow();
        $.each(fields, function (index, field) {
            var name = field.name;
            var converter = field.converter;

            var text = item[name];
            if (!isUndefined(converter)) {
                text = converter(text);
            }
            var col = makeCol(text);
            row.append(col);
        });
        tableContainer.append(row);
    });
}
function makeCol(text, colWidth) {
    var realColWidth = 2;
    if (!isUndefined(colWidth)) {
        realColWidth = colWidth;
    }
    return $("<div class='col-xs-" + realColWidth + " '>" + text + "</div>");
}
function makeRow() {
    return $("<div class='row'></div>")
}
function makeModelBtn(text, target) {
    return $("<button class='btn btn-success btn-xs' data-toggle='modal' data-target='" + target + "'>" + text + "</button>");
}





var CODE_OF_REQUEST_SUCCESS=1;


function fail(code) {
    return code < 0;
}

function success(code) {
    return CODE_OF_REQUEST_SUCCESS == code;
}

/**
 * json用data传递-后台使用@RequestBody;<br/>
 * 非json用url拼接-后台使用@RequestParam
 * @type {{ajaxError: string, requestServerSuccess: ajax.requestServerSuccess, requestServerError: ajax.requestServerError, ajax: ajax.ajax, get: ajax.get, put: ajax.put, post: ajax.post, contentType: {TEXT: string, JSON: string}}}
 */
var ajax = {
    ajax: function (args) {
        $.ajax({
            url: args.url,
            //配合@requestBody
            data: args.data,
            type: args.type,
            success: function (result) {

                if(!isUndefined(args.onSuccess)){
                    args.onSuccess(result);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if(!isUndefined(args.onError)){
                    args.onError();
                }
            }
        });
    },
    get: function (args) {
        args.type = "GET";
        this.ajax(args);
    },
    put: function (args) {
        args.type = "PUT";
        this.ajax(args);
    },
    post: function (args) {
        args.type = "POST";
        this.ajax(args);
    }
};


