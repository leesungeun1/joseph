<html> 
<head> 
<script type="text/javascript"> 
function wrapWindowByMask() {
    //화면의 높이와 너비를 구한다.
    var maskHeight = $(document).height(); 
    var maskWidth = window.document.body.clientWidth;
    var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';
    loadingImg += "<div id='loadingImg' style='position:absolute; left:50%; top:40%; display:none; z-index:10000;'>";
    loadingImg += " <img src='/crawl/loading_logofinal_by_zegerdon-d60eb1v.gif'/>";
    loadingImg += "</div>";  
    //화면에 레이어 추가
    $('body')
        .append(mask)
        .append(loadingImg)
    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
    $('#mask').css({
            'width' : maskWidth
            , 'height': maskHeight
            , 'opacity' : '0.3'
    }); 
    //마스크 표시
    $('#mask').show();   
    //로딩중 이미지 표시
    $('#loadingImg').show();
}
// 출처: http://hunchori.tistory.com/3 [Hunchori]
</script>
<!-- 출처: http://zent.tistory.com/86 [Z] -->
</head> 
<body> 
<!-- 로딩전 보여지는 요소  -->
<!-- <div id="loading">  -->
<!-- now loading  -->
<!-- <img src="/crawl/loading_logofinal_by_zegerdon-d60eb1v.gif">  -->
<!-- </div>  -->
<!-- 테스트를 위한 큰 그림  -->

<!-- <div id="loading"><img id="loading-image" src="/crawl/loading_logofinal_by_zegerdon-d60eb1v.gif" alt="Loading..." /></div> -->
<!-- <div> -->
<!-- 됐다. -->
<!-- </div> -->

	<button type="button" onclick="wrapWindowByMask()">기달.</button>

<!-- <script language="javascript" type="text/javascript">    -->
<!-- // $(window).load(function() {      -->
<!-- //  $('#loading').hide();    -->
<!-- // });  -->
</body> 
</html> 
