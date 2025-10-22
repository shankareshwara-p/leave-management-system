$(document).ready(function() {
    
    // Initialize FullCalendar
    $('#mycalendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,listWeek'
        },
        defaultDate: moment().format("YYYY-MM-DD"),
        eventLimit: true,
        events: fetchLeaves, // initial load
        eventRender: function(event, element) {
            // Color code events based on status
            if (event.status === 'pending') {
                element.css('background-color', 'orange');
            } else if (event.status === 'accepted') {
                element.css('background-color', 'green');
            } else if (event.status === 'rejected') {
                element.css('background-color', 'red');
            }
        }
    });

    // Fetch leaves from server
    function fetchLeaves(start, end, timezone, callback) {
        var params = {
            pending: $('#pending').is(':checked'),
            accepted: $('#accepted').is(':checked'),
            rejected: $('#rejected').is(':checked')
        };
        console.log(params);
        $.ajax({
            url: '/leave_management_system/EmployeeServlet', // your servlet URL
            type: 'GET',
            data: params,
            dataType: 'json',
            success: function(data) {
                // Convert server response if necessary
                var events = data.map(function(leave) {
                    return {
                        id: leave.id,
                        title: leave.title,
                        start: leave.start,
                        end: leave.end ,
                        status: leave.status
                    };
                });
                callback(events);
            },
            error: function() {
                alert('Failed to fetch leave events.');
            }
        });
    }

    // Handle filter checkboxes
    $('.filterStatus').change(function() {
        $('#mycalendar').fullCalendar('refetchEvents'); // reload events with updated filters
    });

});
