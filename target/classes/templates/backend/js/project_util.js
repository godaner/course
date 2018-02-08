var page = {
    start: 0,
    size: 10,
    orderBy: "create_time",
    orderType: "desc"

};
var query = {};


var tableBuilder = {
    DEFAULT_COL_WIDTH: 2,
    buildTable: function (args) {
        var table = args.table;
        var list = args.list;
        var fields = args.fields;
        var updateBtnTarget = args.updateBtnTarget;
        var deleteBtnTarget = args.deleteBtnTarget;

        //clear table

        table.empty();

        //add titles
        var tableHeader = this.makeTableHeader();
        $.each(fields, function (index, item) {
            var title = item.title;
            tableHeader.append(this.makeCol(title));
        }.bind(this));
        tableHeader.append(this.makeCol("操作"));

        //add list

        var tableBody = this.makeTableBody();

        $.each(list, function (index, item) {
            var row = this.makeRow();
            $.each(fields, function (index, field) {
                var name = field.name;
                var converter = field.converter;

                var text = item[name];
                if (!isUndefined(converter)) {
                    text = converter(text);
                }
                var col = this.makeCol(text);
                row.append(col);
            }.bind(this));
            //add model btn
            var col = this.makeCol("");
            var updateBtn = this.makeUpdateModelBtn(updateBtnTarget)
            col.append(updateBtn);
            var deleteBtn = this.makeDeleteModelBtn(deleteBtnTarget)
            col.append(deleteBtn);
            row.append(col);

            tableBody.append(row);
        }.bind(this));

        //integrate
        table.append(tableHeader);
        table.append(tableBody);
    },
    makeTableBody:function () {
      return $("<div class='tablebody'></div>");
    },
    makeTableHeader:function () {
        return $("<div class='row tableHeader'></div>");
    },
    makeCol: function (text, colWidth) {
        var realColWidth = this.DEFAULT_COL_WIDTH;
        if (!isUndefined(colWidth)) {
            realColWidth = colWidth;
        }
        return $("<div class='col-xs-" + realColWidth + " '>" + text + "</div>");
    },
    makeRow: function () {
        return $("<div class='row'></div>")
    },
    makeUpdateModelBtn: function (target) {
        return $("<button class='btn btn-success btn-xs' data-toggle='modal' data-target='" + target + "'>修改</button>");
    },
    makeDeleteModelBtn: function (target) {
        return $("<button class='btn btn-danger btn-xs' style='margin-left: 4px;' data-toggle='modal' data-target='" + target + "'>删除</button>");
    }
}


var CODE_OF_REQUEST_SUCCESS = 1;


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

                if (!isUndefined(args.onSuccess)) {
                    args.onSuccess(result);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (!isUndefined(args.onError)) {
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


