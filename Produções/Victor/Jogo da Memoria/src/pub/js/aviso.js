function verificaAviso(){
	var element = document.getElementsByClassName("control-label")[0];
	if (element.innerHTML == "") {
		element.style.display = "none";
	}
}
