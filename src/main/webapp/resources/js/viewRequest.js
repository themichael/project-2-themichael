$("#receipt_input").fileinput({
	initialPreview: [
	    $("#imageBlob").val()
	],
	initialPreviewAsData: true,
	initialPreviewShowDelete: false,
	showUpload: false,
	showRemove: false,
	showClose: false,
	showBrowse: false,
	showCaption: false,
	initialPreviewConfig: [
	    {caption: "Proof of Request", width: "120px", key: 1}   
	],
	initialCaption: "Proof of Request"
});