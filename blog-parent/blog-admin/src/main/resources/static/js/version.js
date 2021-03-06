function showVersion(id){
    var url = "/moneymateManagement/getVersionInfoById";
    var fn = function (callResult) {
        var versionModel = callResult.result;
        $("#version_VersionId").text(versionModel.versionId);
        $("#version_VersionUrl").text(versionModel.updateUrl);
        $("#version_VersionDesc").text(versionModel.updateDesc);
        $("#version_Versionstatus").text(versionModel.status);
        $("#version_countryName").text(versionModel.countryName);
        $("#version_VersionCode").text(versionModel.versionCode);
        $("#version_targetVersion").text(versionModel.targetVersion);
    };
    var data = "id="+id;
    var dataType = "json";
    $.get(url,data,fn,dataType);
}

function editVersion(id){
    var url = "/moneymateManagement/getVersionInfoById";
    var fn = function (callResult) {
        var versionModel = callResult.result;
        $("#edit_versionId").val(versionModel.versionId);
        $("#edit_id").val(versionModel.id);
        $("#edit_versionUrl").val(versionModel.updateUrl);
        $("#edit_versionDesc").val(versionModel.updateDesc);
        $("#edit_versionLanguage").val(versionModel.language);
        $("#edit_versionCode").val(versionModel.versionCode);
        $("#edit_versionTarget").val(versionModel.targetVersion);
        $(".edit_versionStatus[value='0']").removeAttr("checked");
        $(".edit_versionStatus[value='"+versionModel.versionStatus+"']").prop('checked','true');
    };
    var data = "id="+id;
    var dataType = "json";
    $.get(url,data,fn,dataType);
}


function saveEditVersionInfo() {
    var versionId = $("#edit_versionId").val();
    var updateUrl = $("#edit_versionUrl").val();
    var updateDesc = $("#edit_versionDesc").val();
    var language = $("#edit_versionLanguage").val();
    var versionCode = $("#edit_versionCode").val();
    var targetVersion = $("#edit_versionTarget").val();
    var versionStatus = $(".edit_versionStatus:checked").val();
    var id = $("#edit_id").val();
    // ?????????????????????
    String.prototype.trim = function() {
        return this.replace(/(^\s*)|(\s*$)/g, '');
    };

    if (versionId==null || versionId.trim()==""){
        alert("?????????????????????????????????");
        return;
    }
    if (updateUrl==null || updateUrl.trim()==""){
        alert("????????????????????????");
        return;
    }
    if (updateDesc==null || updateDesc.trim()==""){
        alert("????????????????????????");
        return;
    }
    if (language==null || language.trim()==""){
        alert("????????????????????????");
        return;
    }
    if (versionCode==null || versionCode.trim()==""){
        alert("???????????????????????????");
        return;
    }
    if (targetVersion==null || targetVersion.trim()==""){
        alert("???????????????????????????");
        return;
    }
    if (id==null || id.trim()==""){
        alert("??????ID????????????");
        return;
    }
    if (versionStatus==null || versionStatus.trim()==""){
        alert("????????????????????????");
        return;
    }

    var url = "/moneymateManagement/editVersion";
    var successStr = "????????????";
    var failStr = "????????????";
    if (id == -1){
        url = "/moneymateManagement/addVersion";
        successStr = "????????????";
        failStr = "????????????";
    }
    var data = {"id":id,"versionId":versionId,"updateUrl":updateUrl,"updateDesc":updateDesc,
        "language":language,"targetVersion":targetVersion,"versionCode":versionCode,"versionStatus":versionStatus};
    var dataType = "json";
    var fn = function(data){
        if(data.result == true){
            alert(successStr);
        }else{
            alert(failStr);
        }
        window.location.href="/moneymateManagement/showVersion"
    }
    $.post(url,data,fn,dataType);
}





function delVersion(id){
    var url = "/moneymateManagement/delVersionById";
    var fn = function (data) {
        if(data.result == true){
            alert("????????????");
        }else{
            alert("????????????");
        }
        window.location.href="/moneymateManagement/showVersion"
    };
    var data = "id="+id;
    var dataType = "json";
    $.get(url,data,fn,dataType);
}

function delByQuery(){
    var url = "/moneymateManagement/delVersionsByQuery";
    var versionIds = [];
    //$('input:checkbox:checked') ????????? $('input[type=checkbox]:checked')
    //???????????????????????????checkbox
    $.each($('input:checkbox:checked'),function(){
        var value = $(this).val();
        if(value != "on"){
            versionIds.push(value);
        }
    });
    var fn = function (data) {
        if(data.result == true){
            alert("????????????");
        }else{
            alert("????????????");
        }
        window.location.href="/moneymateManagement/showVersion"
    };
    var data = {versionIds:versionIds};
    var dataType = "json";
    $.post(url,data,fn,dataType);
}

function selectAll(){
    if($("#select_all").get(0).checked){
        $("[name=version_select_checkbox]").attr("checked",true);
    } else{
        $("[name=version_select_checkbox]").attr("checked",false);
    }

}


function searchVersionByVersionId() {
    var versionId = $("#searchVersionId").val();
    window.location.href = "/moneymateManagement/searchVersionByVersionId?versionId="+versionId;
}

