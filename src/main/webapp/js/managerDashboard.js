// $(document).ready(function() {

//     // Initialize FullCalendar
//     $('#managerCalendar').fullCalendar({
//         header: {
//             left: 'prev,next today',
//             center: 'title',
//             right: 'month,agendaWeek,listWeek'
//         },
//         defaultDate: moment().format("YYYY-MM-DD"),
//         eventLimit: true,
//         events: fetchManagerLeaves,
//         eventRender: function(event, element) {
//             if (event.status === 'pending') {
//                 element.css('background-color', 'orange');
//             } else if (event.status === 'accepted') {
//                 element.css('background-color', 'green');
//             } else if (event.status === 'rejected') {
//                 element.css('background-color', 'red');
//             }
//             // Show employee email in tooltip
//             element.attr('title', 'Employee: ' + event.employeeEmail);
//         }
//     });

//     // Fetch stats and events
//     function fetchManagerLeaves(start, end, timezone, callback) {
//         $.ajax({
//             url: '/leave_management_system/ManagerServlet', // servlet for manager
//             type: 'GET',
//             dataType: 'json',
//             success: function(response) {
//                 // response example:
//                 // { stats: { employees:5, total:10, pending:3, accepted:4, rejected:3 }, leaves: [...] }

//                 // Update stats
//                 $('#employeeCount').text(response.stats.employees);
//                 $('#totalRequests').text(response.stats.total);
//                 $('#pendingRequests').text(response.stats.pending);
//                 $('#acceptedRequests').text(response.stats.accepted);
//                 $('#rejectedRequests').text(response.stats.rejected);

//                 // Filter leaves based on checkbox
//                 var filters = {
//                     pending: $('#pending').is(':checked'),
//                     accepted: $('#accepted').is(':checked'),
//                     rejected: $('#rejected').is(':checked')
//                 };

//                 var events = response.leaves.filter(function(leave) {
//                     if (!filters.pending && !filters.accepted && !filters.rejected) return true; // show all if none checked
//                     return (filters.pending && leave.status === 'pending') ||
//                            (filters.accepted && leave.status === 'accepted') ||
//                            (filters.rejected && leave.status === 'rejected');
//                 }).map(function(leave) {
//                     return {
//                         id: leave.id,
//                         title: leave.employeeEmail + " - " + leave.status,
//                         start: leave.start,
//                         end: leave.end,
//                         status: leave.status,
//                         employeeEmail: leave.employeeEmail
//                     };
//                 });

//                 callback(events);
//             },
//             error: function() {
//                 alert('Failed to fetch manager leave events.');
//             }
//         });
//     }

   
//     $('.filterStatus').change(function() {
//         $('#managerCalendar').fullCalendar('refetchEvents');
//     });

// });


$(document).ready(function() {

    // Initialize FullCalendar
    $('#managerCalendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,listWeek'
        },
        defaultDate: moment().format("YYYY-MM-DD"),
        eventLimit: true,
        events: fetchManagerLeaves,
        eventRender: function(event, element) {
            if (event.status === 'pending') {
                element.css('background-color', 'orange');
            } else if (event.status === 'accepted') {
                element.css('background-color', 'green');
            } else if (event.status === 'rejected') {
                element.css('background-color', 'red');
            }
            // Custom HTML: employee email on first line, status on second line
            element.html(
                '<div style="font-weight:bold;">' + event.employeeEmail + '</div>' +
                '<div>Status: ' + event.status + '</div>'
            );
        }
    });

    // Fetch static stats and events
    function fetchManagerLeaves(start, end, timezone, callback) {
        // Static data
        var response = {
            stats: {
                employees: 5,
                total: 10,
                pending: 3,
                accepted: 4,
                rejected: 3
            },
            leaves: [
                { id: 1, employeeEmail: "emp1@example.com", start: "2025-10-22", end: "2025-10-23", status: "pending" },
                { id: 2, employeeEmail: "emp2@example.com", start: "2025-10-25", end: "2025-10-26", status: "accepted" },
                { id: 3, employeeEmail: "emp3@example.com", start: "2025-10-28", end: "2025-10-29", status: "rejected" },
                { id: 4, employeeEmail: "emp4@example.com", start: "2025-10-30", end: "2025-10-31", status: "pending" }
            ]
        };

        // Update stats
        $('#employeeCount').text(response.stats.employees);
        $('#totalRequests').text(response.stats.total);
        $('#pendingRequests').text(response.stats.pending);
        $('#acceptedRequests').text(response.stats.accepted);
        $('#rejectedRequests').text(response.stats.rejected);

        // Filter leaves based on checkbox
        var filters = {
            pending: $('#pending').is(':checked'),
            accepted: $('#accepted').is(':checked'),
            rejected: $('#rejected').is(':checked')
        };

        var events = response.leaves.filter(function(leave) {
            if (!filters.pending && !filters.accepted && !filters.rejected) return true; // show all if none checked
            return (filters.pending && leave.status === 'pending') ||
                   (filters.accepted && leave.status === 'accepted') ||
                   (filters.rejected && leave.status === 'rejected');
        }).map(function(leave) {
            return {
                id: leave.id,
                title: leave.employeeEmail + " - " + leave.status,
                start: leave.start,
                end: leave.end,
                status: leave.status,
                employeeEmail: leave.employeeEmail
            };
        });

        callback(events);
    }

    // Handle filter checkboxes
    $('.filterStatus').change(function() {
        $('#managerCalendar').fullCalendar('refetchEvents');
    });

});
