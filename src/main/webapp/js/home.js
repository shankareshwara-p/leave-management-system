$(document).ready(function () {
  const $content = $("#content");

  function navigate(path) {
    $.get(path + ".html", function (data) {
      $content.html(data);
      // Update URL hash (for refresh support)
      window.location.hash = path;
    });
  }

  // When clicking any sidebar link
  $("a[data-link]").on("click", function (e) {
    e.preventDefault();
    e.stopPropagation();

    const path = $(this).attr("href").replace("#", ""); // remove '#' symbol
    navigate(path);
  });

  // 🧩 Load page from hash when refreshed
  const hash = window.location.hash.replace("#", "");
  if (hash) {
    navigate(hash);
  } else {
    navigate("employeeDashboard"); // default page
  }
  
  
  
      $('#userMenuButton').on('click', function (e) {
        e.stopPropagation();
        $('#userDropdown').toggleClass('hidden');
      });

      // Hide dropdown if clicking outside
      $(document).on('click', function () {
        $('#userDropdown').addClass('hidden');
      });
   

  
});
