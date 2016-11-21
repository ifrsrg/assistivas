function rename(nome){
	var res =  nome.replace(new RegExp(" ", 'g'), "_");
	return res;
}