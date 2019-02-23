<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow-x: hidden">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Spectral+SC" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <title>Article</title>
        <link href="styles/read.css" rel="stylesheet">
    </head>
    <body>
    <body>
        <!-- Search and result box-->
        <nav id="slide-menu">
            <!-- result of search -->
            <form method="POST" action="search" target="video-list">            
                <div class="search">
                    <input type="search" name="query" placeholder="Search whatever you want to know about" />
                </div></form>
            <iframe src="search.jsp" name = "video-list" style="text-align:center" class="iframe1" width="100%" frameborder="0"></iframe>
        </nav>
        <!-- Content panel -->
        <div id="content">
            <div class="menu-trigger"><i class="material-icons">search</i></div>
            <a href="index.jsp" class="back-trigger"><i class="material-icons">keyboard_backspace</i></a>
            <div class="container">
                <header class="headbar">
                    <div class="container">
                        <div class="titlebar">
                            <!--<p><a class="button dark-gray" href="">Back to Dashboard</a></p> -->
                            <h1 class="title">Information Retrieval</h1>                              
                        </div>
                    </div>
                </header>
                <iframe src="pdfjs-1/web/viewer.html" name = "pdf" style="text-align:center" class="iframe1" width="100%" height="1000" frameborder="0"></iframe>

            </div>
        </div>
    </body>
    <script  src="scripts/slide.js"></script>

</body>
</html>
