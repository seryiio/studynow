
var idList = ";"
document.getElementById('textbox1').value = idList;

function EnableDisableButton(cb, id) {

	if (cb.checked == 1) {
		idList = idList + id + ";"
		document.getElementById('textbox1').value = idList;
	}

	if (cb.checked == 0) {
		var v;
		v = ";" + id + ";"
		idList = idList.replace(v, ";");
		document.getElementById('textbox1').value = idList;
	}

	if (idList == ";") {
		document.getElementById('button1').disabled = true;
	} else {
		document.getElementById('button1').disabled = false;
	}

}
