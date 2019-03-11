function findImageBlob(parent, tagname)
{
	$("#rBlob").val($('#'+parent+' '+tagname)[0]['currentSrc']);
}