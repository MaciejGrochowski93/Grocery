        var email = document.querySelector('input[class = "form-control mb-4 col-4"]');
        number.oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                if (e.target.value.length == 0) {
                    e.target.setCustomValidity("Please, insert your email.");
                } else if (e.target.length > 30) {
                    e.target.setCustomValidity("Please, insert valid email.");
                } else if (e.target.length < 5) {
                    e.target.setCustomValidity("Please, insert a number between 1 and 100000.");
                } else
                    e.target.setCustomValidity("Please, insert valid email.");
            }
        };
        // VisualStudio?