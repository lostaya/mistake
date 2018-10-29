<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.setHeader("Content-Type", "text/html; charset=UTF-8");
    response.setHeader("Access-Control-Allow-Origin","*");
%>
<html>
<head>
    <title>VEDIO CONVERTER</title>
    <base href="<%=basePath%>">

    <script type="text/JavaScript" src="js/jquery-1.7.1.js"></script>
    <script type="text/JavaScript">


        window.onload=function(){

            if(document.getElementById("outputPath").value == null || document.getElementById("outputPath").value == ""){
               document.getElementById("download").href = "javascript:void(0)";
            } else {

               document.getElementById("download").href = document.getElementById("outputPath").value;

            }
            
        };


        //开始转换
        function doConvert() {
            var form=new FormData(document.getElementById("form"));

            $.ajax({
                url:"http://192.168.0.78:8080/download/vedio",
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function (data) {

                    if(data.value != null || data.value !=""){
                        document.getElementById("outputPath").value = data;
//                        document.getElementById("download").href = document.getElementById("outputPath").value;
                        alert("转换成功!");
                    }
                },
                error:function (e) {
                    alert(e.name + ": " + e.message);
                }

            })
        }


        //开始下载
        function doDownload() {

            var dPath =  document.getElementById("outputPath").value;

            /*
             var indexs= dPath.indexOf("output/")+7;
             var ends= $("#outputPath").val().length;
             var filename = dPath.substring(indexs,ends);
             */

            var filename = dPath.substring(dPath.indexOf("/output/")+8,$("#outputPath").val().length);
            alert(filename);

            $.ajax({
                url:"http://192.168.0.78:8080/download/file",
                type:"post",
                data:{
                    path : dPath,
                    filename : filename
                },
                success:function (data) {
                    alert("下载成功!")

                },
                error:function (e) {
                    alert("保存失败;"+XmlHttpRequest.responseText);

                    alert(e.name + ": " + e.message);
                }
            })

        }
    </script>

</head>
<body>

<form id="form">

    <table  border="1"  align="center">
        <tr>
            <td align="center" width="250">选择视频:</td>
            <td width="240"><input type="file" name="file1" id="file1"/></td>
        </tr>

        <tr>
            <td align="right">
                <input type="button" value="合成" onclick="doConvert();"/>
            </td>
            <td align="right" id="downloadTd">
                输出视频地址：
                <input type="text" border="0px" id="outputPath" style="width:250px" value="" disabled/>

                <%--<a id="download" download="finalVideo.mp4" >下载</a>--%>
                <%--<a id="download" download="horse.jpg" href="./artifacts/demo_war_exploded/input/355.jpg" >下载</a>--%>
                <input type="button" value="下载" id="download" onclick="doDownload()"/>
            </td>
        </tr>
    </table>
</form>


 <%--<form id="form" style="width: 480px" >--%>
    <%--<input type="file" id="fileload" style="width: 240px" >--%>
    <%--<input type="button" value="转换" style="width: 240px" onclick="doConvert();" >--%>
     <%--<div>输出视频:</div>--%>
     <%--<input type="text" id="outputPath" style="width:240px" value="" disabled >--%>
     <%--<a id="download" download="done.mp4" style="width: 240px" >下载</a>--%>
     <%--</td>--%>
 <%--</form>--%>

</body>
</html>
