$(document).ready(function () {
  // Add Tailwind class to heading
  $("#h1").addClass("text-blue-500");

  // Accept button click
  $("#accept").click(function () {
    sendAction("accept");
  });

  // Reject button click
  $("#reject").click(function () {
    sendAction("reject");
  });

  // Function to send AJAX call
  function sendAction(actionType) {
    $.ajax({
      url: "http://localhost:8080/leave_management_system/ManagerServlet",
      type: "POST",
      data: { action: actionType,
            leaveId : 1,
       },
      success: function (response) {
        alert("Action '" + actionType + "' sent successfully!\nResponse: " + response);
      },
      error: function (xhr, status, error) {
        alert("Error sending " + actionType + " request: " + error);
      }
    });
  }
});
