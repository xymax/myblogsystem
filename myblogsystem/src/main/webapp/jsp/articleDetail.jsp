<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>博客详情</title>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- HTTP 1.1 -->
	<meta http-equiv="pragma" content="no-cache">
	<!-- HTTP 1.0 -->
	<meta http-equiv="cache-control" content="no-cache">
	<!-- Prevent caching at the proxy server -->
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<!-- jQuery -->
	<script type="text/javascript" src="static/jquery/jquery-1.12.4.min.js"></script>
	<!-- bootstrap -->
	<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<!-- bootstrap validator -->
	<link href="static/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	
	<link rel="stylesheet" href="static/font-awesome-4.7.0/css/font-awesome.css">
	
	<!-- UEditor -->
    <script type="text/javascript" src="static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="static/ueditor/ueditor.all.js"></script>
    
    <link href="static/css/app.css?v=1" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="articleForm">
			<input id="articleId" name="id" type="hidden">
			<div class="form-group" style="margin: 50px auto 0 auto; padding: 0;">
				<label for="article_title"><label style="color: red;">*</label>标题</label>
				<input type="text" class="form-control" id="article_title" name="title" 
					placeholder="文章标题" data-bv-notempty="true" data-bv-notempty-message="不能为空">
			</div>
			
			<div class="form-group">
				<script id="ueditor_content" name="content" type="text/plain"></script>
				<div id="contentError" class="form-group has-feedback has-error">
				   <small class="help-block" data-bv-validator="notEmpty" data-bv-for="content" data-bv-result="INVALID" style="">
				       <span id="contentErrorInfo"></span>                                
				   </small>
				</div>
			</div>

			<div class="modal fade"  id="tip_success" role="dialog" data-backdrop="false"  aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <p class="text-center mb-0">
			                <i class="fa fa-check-circle text-success mr-1" aria-hidden="true"></i>
			                提交成功
			            </p>
			        </div>
			    </div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="tip_failed" tabindex="-1" role="dialog"
				aria-labelledby="tip_failed_label">
				<div class="modal-dialog" role="document" style="display: width: 600px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="tip_failed_label">错误提示</h4>
						</div>
						<div class="modal-body">
				            <p id="tip_failed_message" class="text-center mb-0">
				                <i class="fa fa-check-circle text-danger mr-1" aria-hidden="true"></i>
	                	提交失败
				            </p>
							<div id="tip_failed_dev_div">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#tip_failed_trace" aria-expanded="false"
									aria-controls="tip_failed_trace">查看错误信息</a>
								<div class="collapse" id="tip_failed_trace" style="word-break:break-all; word-wrap:break-all;"></div>
							</div>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
			<div class="btn-group" role="group">
				<button id="btn_clear" type="button" class="btn btn-danger">清除</button>
				<button id="btn_submit" type="submit" class="btn btn-success">提交</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
var devToken = '<%=request.getParameter("devToken")%>';
var devUri = "";
if (typeof devToken === 'string'  &&  devToken.length  >  0){
	devUri = "devToken="+devToken;
}
var ue = UE.getEditor('ueditor_content', {
    initialFrameHeight: 400, //设置编辑器高度
    UEDITOR_HOME_URL: "static/ueditor/",
    scaleEnabled: true
});
var domUtils = UE.dom.domUtils;
ue.addListener('ready',function(){
	domUtils.on(ue.body,"keyup",function(){
		var content = ue.getContent().trim();
		if (content.length > 0){
			$('#articleForm').bootstrapValidator('disableSubmitButtons', false);
			$("#contentErrorInfo").text("");
		}
	});
});

var articleId = '<%=request.getParameter("id")%>';
var articleType = '<%=request.getParameter("type")%>';
$(function() {
	
	if(articleType == "add"){
		$("title").html("新增博客文章");
	}else if(articleType == "update"){
		$("title").html("修改博客文章");
		// 获取文章数据 TODO
	}
	
	ue.ready(function () {
		ue.setContent("开始修改文章了哟");
	});
				
	$("#btn_clear").click(function(e){
		ue.setContent('');
	});
	
	$("#articleForm").submit(function(ev){
		ev.preventDefault();
		$('#articleForm').bootstrapValidator('disableSubmitButtons', false);
	});
	$("#btn_submit").click(function(){
		
	});
	
// 	$('#articleForm').bootstrapValidator();
	
});
</script>
</html>