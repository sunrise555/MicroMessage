/**
 * 
 */
 function judgeDelete(id)
  {
       if(confirm("确定要删除吗？"))
   {
          window.location.href="DeleteOne.action?id="+id;
       }
  }
 /**
  * 批量删除，使用jQuery
  */
 function deleteBatch(basePath) {
	 //利用jQuery选择器$("#lastname")，修改属性
	 if(confirm("确定要删除吗？")) {
		 $("#mainForm").attr("action", basePath + "DeleteBatch.action");
		 $("#mainForm").submit();
	 }
 }
/**
 * 修改当前页数
 * @param currentPageIndex
 */ 
 function changeCurrentPageIndex(currentPageIndex) {
	 $("#currentPageIndex").val(currentPageIndex);
	 $("#mainForm").submit();
 }
 
 /**
  * 批量删除
  * 还用ajax提交删除内容，无法刷新
  */
 function getCheckBoxValue(){
	 if(confirm("确定要删除吗？")) {
	 var obj = document.getElementsByName("deleteBatch");
	 var list = new Array();
	 for(var i=0; i<obj.length; i++) {
		 if(obj[i].checked)
			 list.push(obj[i].id);
	 	}
	 }
	
	 $.ajax({
		 	type: "GET",
		    url: "DeleteBatch.action",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async:true,
		    //穿数组时，设置traditional为true，可以防止jQuery深度序列化数组，从而导致后台取到参数后还需要分隔
		    traditional: true,
		    cache:false,
		    data:{
		    		deleteList: list
		    	},    //参数值	
		});
 }
 
