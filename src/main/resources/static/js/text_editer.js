


var qTextarea = document.getElementById("questionTextarea");
var aTextareas = document.querySelectorAll(".answerTextarea");

// Số dòng tối đa bạn muốn giới hạn
var qMaxRows = 30;
var aMaxRows = 10;

// Bắt sự kiện khi người dùng nhập liệu
qTextarea.addEventListener("input", function () {
    // Tách văn bản thành mảng dòng
    var lines = qTextarea.value.split("\n");

    // Kiểm tra số dòng
    if (lines.length > qMaxRows) {
        // Nếu vượt quá số dòng tối đa, cắt bớt dòng cuối cùng
        qTextarea.value = lines.slice(0, qMaxRows).join("\n");
    }
});
aTextareas.forEach(function (textarea) {
    textarea.addEventListener("input", function () {
        // Tách văn bản thành mảng dòng
        var lines = textarea.value.split("\n");

        // Kiểm tra số dòng
        if (lines.length > aMaxRows) {
            // Nếu vượt quá số dòng tối đa, cắt bớt dòng cuối cùng
            textarea.value = lines.slice(0, aMaxRows).join("\n");
        }
    });
});
document.addEventListener("DOMContentLoaded", () => {
    ClassicEditor
        .create( document.querySelector( '#questionTextarea' ) )
        .then( editor => {
            window.editor = editor;
        } )
        .catch( error => {
            console.error( 'There was a problem initializing the editor.', error );
        } );
});

// function convertToParagraphs() {
//     var textarea = document.getElementById('questionTextarea');
//     var content = textarea.value;
//
//     var lines = content.split('\n'); // Split by line breaks
//
//     var newContent = '';
//     for (var i = 0; i < lines.length; i++) {
//         newContent += '<p>' + lines[i] + '</p>';
//     }
//
//     textarea.value = ''; // Clear the textarea
//     textarea.insertAdjacentHTML('beforebegin', newContent); // Insert &lt;p&gt; elements before &lt;textarea&gt;
// }