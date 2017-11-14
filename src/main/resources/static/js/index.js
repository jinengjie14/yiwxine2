function register(z){
	/*获取register.html的form表单的所有数据*/
	
	var form = $(z).parent().parent();
	var form_ser = form.serialize();
	
	 /*用post提交到/register地址，把表单数据传进后台，成功就会执行success函数，进入login页面,{function(e)中e代表后台return的数据}如果不成功就会返回后台的错误信息*/
	$.ajax({
		url:"/register",
		type:"post",
		data:form_ser,
		success:function(e){
			if(e === "success"){
				location.href="/login";
			}
			//将后台传来的字符串转成json
			var json = $.parseJSON(e);
			//获取json的第一个下标数据
			var json0 =  json[0];
			//找到json返回错误的 位置 再把错误信息 赋给 错误的位置
			$("#"+json0.field+"_error")[0].innerHTML = json0.message;
		}
	})
}

function loginz(z){
	
	var form = $(z).parent().parent();
	
	var form_ser = form.serialize();
	
	$.ajax({
		url:"/login",
	    type:"post",
	    data:form_ser,
	    success:function(e){
	    	if(e === "success"){
	    		location.href="/addnote";
	    	}
	    	 if(e === "account_not_exit"){
		        	$("#account-pro").append("账号不存在");
		        }
	    	
	        if(e === "passwd_error"){
	        	$("#passwd-pro").append("密码错误");
	        }
	    	
	    }
	    
	})
}

function del(dl){
	var id = dl.name;
	var url = "/note/"+id+"/delete";
	$.ajax({
		url : url,
		type:"post",
		data:{},
		success:function(e){
			if(e === "success"){
				$(dl).parent().parent().hide("slow");
				 setTimeout(function() {
				     $(dl).parent().parent().remove();
				    }, 1000);
						    
			}
		}
		
	})
}

function det(n){
	var id = n.name;
	location.href="/note/"+id;
}

function up(n){
	var id = n.name;
	location.href="/note/"+id+"/update";
}

function upn(n){
	console.log(n.name);
    var form = $(n).parent();
	var form_ser = form.serialize();
	console.log(form);
	console.log(form_ser);
	var id = n.name;
	var url = "/note/"+id+"/update";
	$.ajax({
		url : url,
		type:"post",
		data:form_ser,
		success:function(e){
			if(e === "success"){
				console.log("后台启用");
				location.href="/note/"+id;
			}
		}
			
	})
}


