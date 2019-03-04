/**
 * 鼠标移到上面事件
 * @param obj
 * @returns
 */
function mouseOver(obj){
	obj.style.backgroundColor = "#eee";
}
/**
 * 鼠标移开事件
 * @param obj
 * @returns
 */
function mouseOut(obj){
	obj.style.backgroundColor = "";
}
/**
 * 根据主键删除记录
 * @param id
 * @param sp
 * @returns
 */
function deleteById(id,sp,qname,qusername,qsex){
	if(confirm("确认要删除编号为【"+id+"】数据吗？")){
		location.href = "deleteById?id="+id+"&currentPage="+sp+"&qname="+qname+"&qusername="+qusername+"&qsex="+qsex;
	}
}
function deleteMore(sp,qname,qusername,qsex){
	var num = $("input[type=checkbox]:gt(0):checked").length;
	if(num==0){
		alert("请选择要删除的记录");
		return;
	}
	if(confirm("您确定要删除这【"+num+"】条记录吗？")){
		var ids = "";
		var array = $("input[type=checkbox]:gt(0):checked");
		for(var i=0;i<array.length;i++){
			ids += array[i].value+",";
		}
		ids = ids.substring(0,ids.length-1);
		location.href = "deleteMore?ids="+ids+"&currentPage="+sp+"&qname="+qname+"&qusername="+qusername+"&qsex="+qsex;
	}
}
$(function(){

	$("input[type=checkbox]:eq(0)").change(function(){
		var state = $(this).attr("checked");
		if(state){
			$("input[type=checkbox]:gt(0)").attr("checked",true);
		}else{
			$("input[type=checkbox]:gt(0)").attr("checked",false);
		}
	});
	$("input[type=checkbox]:gt(0)").change(function(){
		if($("input[type=checkbox]:gt(0):checked").length==$("input[type=checkbox]:gt(0)").length){
			$("input[type=checkbox]:eq(0)").attr("checked",true);
		}else{
			$("input[type=checkbox]:eq(0)").attr("checked",false);
		}
	});
});