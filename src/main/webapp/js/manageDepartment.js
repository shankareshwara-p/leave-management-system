 $(document).ready(function() {
      // Open modal
      $('.try').click(function() {
        $('.modal-overlay').fadeIn(200);
        $('.modal-box').fadeIn(200);
      });

      // Close modal on overlay or button click
      $('.close-btn, .modal-overlay').click(function() {
        $('.modal-overlay, .modal-box').fadeOut(200);
      });

      // Form submit action
      $('#deptForm').submit(function(e) {
        e.preventDefault();
        const deptName = $('#deptName').val();
        const deptCode = $('#deptCode').val();

        alert(`Department Added:\nName: ${deptName}\nCode: ${deptCode}`);
        $('.modal-overlay, .modal-box').fadeOut(200);
      });
    });