document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('appointmentForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting and refreshing the page

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const doctor = document.getElementById('doctor').value;
        const hospital = document.getElementById('hospital').value;
        const appointmentDate = document.getElementById('appointmentDate').value;
        const appointmentTime = document.getElementById('appointmentTime').value;

        // Create a data object to send to the backend
        const appointmentData = {
            name: name,
            email: email,
            doctor: doctor,
            hospital: hospital,
            appointmentDate: appointmentDate,
            appointmentTime: appointmentTime
        };

        // Send the data to the backend using the fetch API
        fetch('http://localhost:8080/appointments', {  // Replace with your actual backend URL
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appointmentData) // Convert the data to JSON
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse the JSON response from the backend
            })
            .then(data => {
                // Handle the response from the backend
                if (data.success) {
                    // Display a success message
                    alert('Appointment saved successfully!'); // Display an alert
                    messageDiv.textContent = 'Appointment saved successfully!'; // Update the message div (optional)
                } else {
                    // Display an error message
                    alert('Failed to save appointment: ' + data.message); // Display an alert with the error message
                    messageDiv.textContent = 'Failed to save appointment: ' + data.message; // Update the message div (optional)
                }
            })
            .catch(error => {
                // Handle errors
                console.error('There was an error:', error);
                alert('There was an error saving the appointment. Please check the console.'); // Display a generic error message
                messageDiv.textContent = 'There was an error saving the appointment.'; // Update the message div (optional)
            });
    });
});