function showMessage(msg) {
   alert(msg);
};

function deleteEmployee(id){
	 $.ajax({
	        url: '/company/api/v1/delete_employee/'+id,
	        method: 'DELETE',
	        success: function (result) {
	        	window.location.replace("/company/api/v1/employees");
	        },
	        error: function (error) {
	            alert(error);
	        }
	  })
}
function claimsEmployee(id){
	window.location.replace('/company/api/v1/claims/employee/'+id);
}




function detailsEmployee(id){
	window.location.replace("/company/api/v1/details_employees/"+id);
}

function updateEmployee(){
	var id = $("#id").val();
	var name = $("#name").val();
	var surname = $("#surname").val();
 	var employee = {name:name,surname:surname};
	 
 	$.ajax({
	        url: '/company/api/v1/employees/'+id,
	        contentType : 'application/json; charset=utf-8',
	        dataType : 'json',
	        data: JSON.stringify(employee),
	        method: 'PUT',
	        success: function (result) {
	        	console.log(result);
	        },
	        error: function (error) {
	            console.log(error);
	        }
	  })
}

