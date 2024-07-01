document.addEventListener('DOMContentLoaded', function () {
    // Lấy các phần tử cần sử dụng
    var filterDropdown = document.getElementById('filter');
    var filterDropdown2 = document.getElementById('filter2');
    var numInput = document.getElementById('filter3');

    var numQ = document.getElementById('numQ');
    var topicInput = document.getElementById('topicId');
    var levelInput = document.getElementById('levelId');
    var dataTable = document.getElementById('dataTable');
    var tableRows = dataTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');


    // Xử lý sự kiện khi dropdown Topics thay đổi
    filterDropdown.addEventListener('change', handleDropdownChange);
    // Xử lý sự kiện khi dropdown Levels thay đổi
    filterDropdown2.addEventListener('change', handleDropdownChange);
    // filterDropdown3.addEventListener('change', handleDropdownChange);
    numInput.addEventListener('change', handleDropdownChange);

    // Hàm xử lý sự kiện thay đổi của cả hai dropdown
    function handleDropdownChange() {
        var selectedValueTopic = filterDropdown.value;
        var selectedValueLevel = filterDropdown2.value;
        var selectedValueNum = numInput.value;

        topicInput.value = selectedValueTopic;
        levelInput.value = selectedValueLevel;
        numInput.value = selectedValueNum;
        numQ.value = selectedValueNum;
        var count = 0;
        // Lặp qua các dòng của bảng và ẩn/hiện dựa trên giá trị được chọn
        for (var i = 0; i < tableRows.length; i++) {
            var row = tableRows[i];
            var cellTopic = row.getElementsByTagName('td')[3]; // Chỉnh số index dựa trên cột cần lọc Topics
            var cellLevel = row.getElementsByTagName('td')[4]; // Chỉnh số index dựa trên cột cần lọc Levels

            if ((selectedValueTopic === '' || cellTopic.textContent.includes(selectedValueTopic)) &&
                (selectedValueLevel === '' || cellLevel.textContent.includes(selectedValueLevel)) &&
                (selectedValueNum === '' || count < selectedValueNum)){
                count++;
                row.style.display = ''; // Hiện dòng
            } else {
                row.style.display = 'none'; // Ẩn dòng
            }
        }
    }
});