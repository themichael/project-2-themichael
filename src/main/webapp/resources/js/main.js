function changeDropdown(name) {
	$("#dropdown-type").html(name + ' <span class="caret"></span>');
	$("#rType").val(name);
}

function disableViewButton() {
	$("#view_button").attr("disabled", true);
}

function setReimbursementId(id) {
	$("#view_button").attr("disabled", false);
	$("#rId").val(id);
}

function setEmployeeId(id) {
	$("#view_button").attr("disabled", false);
	$("#eIndex").val(id);
}

function setReimbursementStatus(status) {
	$("#rStatus").val(status);
	$("#submit_form").submit();
}