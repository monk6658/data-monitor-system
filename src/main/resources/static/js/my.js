if ("WebSocket" in window) {
    // 打开一个 web socket
    var ws = new WebSocket("ws://127.0.0.1:9094/websocket");

    ws.onopen = function()
    {
        // Web Socket 已连接上，使用 send() 方法发送数据
        ws.send("发送数据");
    };

    ws.onmessage = function (evt)
    {
        var received_msg = evt.data;
        preDealData(received_msg);
        console.log(received_msg);
    };
    //  连接关闭
    ws.onclose = function() {
        alert("连接已关闭...");
    };

}else{
    // 浏览器不支持 WebSocket
    alert("您的浏览器不支持 WebSocket!");
}

/**
 * 预处理数据
 */
function preDealData(data) {

    var jsonData = JSON.parse(data);
    //选择
    var choice = jsonData.type;

    switch (choice) {
        case 1:
            appendWarnTableTr(jsonData); //应用系统预警
            break;
        case 2:
            changeDayMoney(jsonData.money);//每日交易额
            break;
        case 3:
            break;

    }


}

/**
 * 拼接系统预警显示表格信息
 * @param jsonData 预警内容
 */
function appendWarnTableTr(jsonData) {
    var warnTable = $("#warnTable");
    var content = "<tr>" +
        "<td>" + changeWarnLevel(jsonData.warnLevel) + "</td>" +
        "<td>" + jsonData.warnApp + "</td>" +
        "<td>" + jsonData.createTime + "</td>" +
        "</tr>";

    if(warnTable.find("tr").length >= 3){
        warnTable.find("tr:first").remove();
    }
    warnTable.append(content);
}

/**
 * 预警级别转换
 */
function changeWarnLevel(warnLevel){
    switch (warnLevel) {
        //应用系统预警
        case "1":
            return "特一级";
        case "2":
            return "特二级";
        case "3":
            return "特三级";
        default:
            return "默认";
    }

}

/**
 * 每日交易额转换
 */
function changeDayMoney(money) {
    $("#dayMoney").text(money);

}


