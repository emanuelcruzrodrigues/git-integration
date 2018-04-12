function isNotEmpty(str) {
    return !isEmpty(str);
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function clearCombobox(combobox){
	combobox.options.length = 0;
}

function selectComboboxValue(combobox, value){
	if (isEmpty(value)){
		combobox.selectedIndex = -1;
	}else{
		combobox.value = value;
	}
}

