function verificaAviso(){
	var element = document.getElementsByClassName("control-label")[0];
	alert(element.innerHTML);
	if (element.innerHTML == "") {
		element.style.display = "none";
	}
}
