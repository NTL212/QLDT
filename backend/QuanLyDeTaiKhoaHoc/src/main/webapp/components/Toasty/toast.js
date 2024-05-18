function showToast(message, backgroundColor) {
	if (message) {
		Toastify({
			text: message,
			duration: 3000,
			gravity: "bottom",
			close: true,
			backgroundColor: backgroundColor || "red"
		}).showToast();
	}
}

function showErrorMessage(message) {
	showToast(message, 'red');
}

function showSuccessMessage(message) {
	showToast(message, 'green');
}