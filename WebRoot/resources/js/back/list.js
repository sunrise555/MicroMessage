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
 * 显示新增/修改页面
 */
 function showChildPage() {
	 var a = document.getElementById("addItem");
	 var b = document.getElementById("showMask");
	 b.style.display = "block";
	 a.style.display = "block";
	 
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
 
 /**
  * 在在新增页面中向表中添加行
  */
 function addRow(id) {
	 var table = document.getElementById(id);
	 //var trs = table.getElementsByTagName("tr");	
	 //行数
	 var rows = table.rows.length;
//	 for (var i = 1; i < rows; i++) {
//		table.rows[i].cells[2].innerHTML= '<a href="javascript:void(0)" onclick="deleteRow(this)">-</a>';;
//	}
	 //列数
	 //var cells = document.getElementById(id).rows.item(0).cells.length;
	 //在表的尾部增加新的行
	 var x=table.insertRow(rows - 1);
	 x.insertCell(0).innerHTML = rows - 1;
	 x.insertCell(1).innerHTML = '<textarea name="newContents" wrap="virtual" rows="" cols="20"></textarea>';
	 x.insertCell(2).innerHTML = '<a href="javascript:void(0)" onclick="deleteRow(this)">-</a>';	 
 }
 /**
  * 在新增页面中删除表中的行
  * @param id
  */
 function deleteRow(r) {
	 var index=r.parentNode.parentNode.rowIndex;
	 document.getElementById('add_table').deleteRow(index);
	 var table = document.getElementById('add_table');
	 var rows = table.rows.length;
	 for (var i = index; i < rows - 1; i++) {
		 table.rows[i].cells[0].innerHTML -= 1;
	}
 }
 
 /**
  * 取消
  * @param basePath
  */
 function cancel(basePath) {
	 $("#mainForm").attr("action", basePath + "List.action");
	 $("#mainForm").submit();
 }
 
 /**
  * 填写新增内容后确认提交
  * @param basePath
  */
 function confirmAdd_modify(basePath,flag) {
	 if(flag==1) {
		 $("#mainForm").attr("action", basePath + "AddNewItem.action"); 
		 }
	 if(flag==0) {
		 window.location.href="ModifyItem.action?id="+id; 
	 }
	 $("#mainForm").submit();
 }
 function addItem() {
	 document.getElementById("flag").value = 1;
	 showChildPage();
 }
 function modifyItem(id) {
	 document.getElementById("flag").value = 0;
	 showChildPage();
 }
 
