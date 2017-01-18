function approve(element){
	var elemento_jquery = $(element);
	if (!elemento_jquery.parent().hasClass("has-success")) {
		clean(element);
		elemento_jquery.attr("aria-describedby", element.id + "Status");
		var pai = elemento_jquery.parent();
		pai.parent().addClass("has-success");
		pai.parent().addClass("has-feedback");
		pai.append('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>' + 
				   '<span id="'+element.id+'Status" class="sr-only">(success)</span>');
	}
	
}

function error(element){
	var elemento_jquery = $(element);
	if (!elemento_jquery.parent().hasClass("has-error")) {
		clean(element);
		elemento_jquery.attr("aria-describedby", element.id + "Status");
		var pai = elemento_jquery.parent();
		pai.parent().addClass("has-error");
		pai.parent().addClass("has-feedback");
		pai.append('<span id=1 class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>' + 
				   '<span id="'+element.id+'Status" class="sr-only">(success)</span>');
	}
}

function clean(element){
	var elemento_jquery = $(element);
	if (elemento_jquery.attr("aria-describedby") != undefined) {
		elemento_jquery.removeAttr("aria-describedby");
		var pai = elemento_jquery.parent();
		pai.parent().removeClass("has-error");
		pai.parent().removeClass("has-success");
		pai.parent().removeClass("has-feedback");
		pai.find(".glyphicon").remove();
		pai.find(".sr-only").remove();
	}
	
}