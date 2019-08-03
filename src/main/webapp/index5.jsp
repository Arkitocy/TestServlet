<%--
  Created by IntelliJ IDEA.
  User: zsj55
  Date: 2019/7/22
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ page import="Service.HomeworkService" %>
<%@ page import="Bean.Homework" %>
<%@ page import="Bean.HomeworkDetail" %>
<%@ page import="javafx.scene.control.Alert" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
          content="Bootstrap 是世界上最受欢迎的前端框架，用于构建响应式、移动设备优先的网站。快速了解 Bootstrap 、使用 BootCDN 以及熟悉初学者模板页面。">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.7.0">

    <title>作业管理系统</title>

    <!-- Bootstrap core CSS -->

    <style class="anchorjs"></style>
    <link href="%E7%AE%80%E4%BB%8B%20%C2%B7%20Bootstrap_files/bootstrap.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <!-- Documentation extras -->

    <link href="%E7%AE%80%E4%BB%8B%20%C2%B7%20Bootstrap_files/docsearch.css" rel="stylesheet">

    <link href="%E7%AE%80%E4%BB%8B%20%C2%B7%20Bootstrap_files/docs.css" rel="stylesheet">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://v4.bootcss.com/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="https://v4.bootcss.com/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="https://v4.bootcss.com/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="https://v4.bootcss.com/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="https://v4.bootcss.com/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="https://v4.bootcss.com/favicon.ico">
    <meta name="msapplication-config" content="/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">


    <!-- Twitter -->
    <meta name="twitter:card" content="summary">
    <meta name="twitter:site" content="@getbootstrap">
    <meta name="twitter:creator" content="@getbootstrap">
    <meta name="twitter:title" content="简介">
    <meta name="twitter:description"
          content="Bootstrap 是世界上最受欢迎的前端框架，用于构建响应式、移动设备优先的网站。快速了解 Bootstrap 、使用 BootCDN 以及熟悉初学者模板页面。">
    <meta name="twitter:image" content="https://v4.bootcss.com/assets/brand/bootstrap-social-logo.png">

    <!-- Facebook -->
    <meta property="og:url" content="https://v4.bootcss.com/docs/4.0/getting-started/introduction/">
    <meta property="og:title" content="简介">
    <meta property="og:description"
          content="Bootstrap 是世界上最受欢迎的前端框架，用于构建响应式、移动设备优先的网站。快速了解 Bootstrap 、使用 BootCDN 以及熟悉初学者模板页面。">
    <meta property="og:type" content="website">
    <meta property="og:image" content="http://v4.bootcss.com/assets/brand/bootstrap-social.png">
    <meta property="og:image:secure_url" content="https://v4.bootcss.com/assets/brand/bootstrap-social.png">
    <meta property="og:image:type" content="image/png">
    <meta property="og:image:width" content="1200">
    <meta property="og:image:height" content="630">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="./Navbar · Bootstrap_files/popper.min.js.下载" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script><script src="./Navbar · Bootstrap_files/bootstrap.min.js.下载" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script><script src="./Navbar · Bootstrap_files/docsearch.min.js.下载"></script><script src="./Navbar · Bootstrap_files/docs.min.js.下载"></script>

</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    String rs=(String)request.getAttribute("rs");
    String type = (String)session.getAttribute("permission");


%>

<script type="text/javascript">

    var msg='<%=rs%>';
    //js 中，如果变量的值是-1，null, "" 会自动的默认是false

    if(msg!="null"){
        alert(msg);
    }

</script>
<a id="skippy" class="sr-only sr-only-focusable" href="https://v4.bootcss.com/docs/4.0/components/navbar/#content">
    <div class="container">
        <span class="skiplink-text">Skip to main content</span>
    </div>
</a>

<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="https://v4.bootcss.com/" aria-label="Bootstrap">
        <svg class="d-block" width="36" height="36" viewBox="0 0 612 612" xmlns="http://www.w3.org/2000/svg"
             focusable="false"><title>Bootstrap</title>
            <path fill="currentColor"
                  d="M510 8a94.3 94.3 0 0 1 94 94v408a94.3 94.3 0 0 1-94 94H102a94.3 94.3 0 0 1-94-94V102a94.3 94.3 0 0 1 94-94h408m0-8H102C45.9 0 0 45.9 0 102v408c0 56.1 45.9 102 102 102h408c56.1 0 102-45.9 102-102V102C612 45.9 566.1 0 510 0z"></path>
            <path fill="currentColor"
                  d="M196.77 471.5V154.43h124.15c54.27 0 91 31.64 91 79.1 0 33-24.17 63.72-54.71 69.21v1.76c43.07 5.49 70.75 35.82 70.75 78 0 55.81-40 89-107.45 89zm39.55-180.4h63.28c46.8 0 72.29-18.68 72.29-53 0-31.42-21.53-48.78-60-48.78h-75.57zm78.22 145.46c47.68 0 72.73-19.34 72.73-56s-25.93-55.37-76.46-55.37h-74.49v111.4z"></path>
        </svg>
    </a>

    <div class="navbar-nav-scroll">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
                <a class="nav-link " href="https://v4.bootcss.com/"
                   onclick="ga(&#39;send&#39;, &#39;event&#39;, &#39;Navbar&#39;, &#39;Community links&#39;, &#39;Bootstrap&#39;);">首页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="rank.jsp"
                   onclick="ga(&#39;send&#39;, &#39;event&#39;, &#39;Navbar&#39;, &#39;Community links&#39;, &#39;Docs&#39;);">积分排行榜</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="https://v4.bootcss.com/docs/4.0/examples/"
                   onclick="ga(&#39;send&#39;, &#39;event&#39;, &#39;Navbar&#39;, &#39;Community links&#39;, &#39;Examples&#39;);">实例</a>
            </li>
            <!-- <li class="nav-item">
              <a class="nav-link" href="https://themes.getbootstrap.com" onclick="ga('send', 'event', 'Navbar', 'Community links', 'Themes');" target="_blank" rel="noopener">Themes</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="https://jobs.getbootstrap.com" onclick="ga('send', 'event', 'Navbar', 'Community links', 'Jobs');" target="_blank" rel="noopener">Jobs</a>
            </li> -->
            <li class="nav-item">
                <a class="nav-link" href="http://www.youzhan.org/"
                   onclick="ga(&#39;send&#39;, &#39;event&#39;, &#39;Navbar&#39;, &#39;Community links&#39;, &#39;Expo&#39;);"
                   target="_blank" rel="noopener">优站精选</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://blog.getbootstrap.com/"
                   onclick="ga(&#39;send&#39;, &#39;event&#39;, &#39;Navbar&#39;, &#39;Community links&#39;, &#39;Blog&#39;);"
                   target="_blank" rel="noopener">Blog</a>
            </li>
        </ul>
    </div>

    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">

        <li class="nav-item">
            <p class="navbar-brand">欢迎 <%=username %>
            </p>
        </li>
    </ul>

    <a class="btn btn-bd-download d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="login.html">退出</a>
</header>


<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="col-12 col-md-3 col-xl-2 bd-sidebar">
            <form class="bd-search d-flex align-items-center">
                <span class="algolia-autocomplete"
                      style="position: relative; display: inline-block; direction: ltr;"><input type="search"
                                                                                                class="form-control ds-input"
                                                                                                id="search-input"
                                                                                                placeholder="Search..."
                                                                                                autocomplete="off"
                                                                                                spellcheck="false"
                                                                                                role="combobox"
                                                                                                aria-autocomplete="list"
                                                                                                aria-expanded="false"
                                                                                                aria-owns="algolia-autocomplete-listbox-0"
                                                                                                style="position: relative; vertical-align: top;"
                                                                                                dir="auto"><pre
                        aria-hidden="true"
                        style="position: absolute; visibility: hidden; white-space: pre; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;; font-size: 16px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: optimizelegibility; text-transform: none;"></pre><span
                        class="ds-dropdown-menu"
                        style="position: absolute; top: 100%; z-index: 100; display: none; left: 0px; right: auto;"
                        role="listbox" id="algolia-autocomplete-listbox-0"><div
                        class="ds-dataset-1"></div></span></span>
                <button class="btn btn-link bd-search-docs-toggle d-md-none p-0 ml-3" type="button"
                        data-toggle="collapse" data-target="#bd-docs-nav" aria-controls="bd-docs-nav"
                        aria-expanded="false" aria-label="Toggle docs navigation">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 30 30" width="30" height="30"
                         focusable="false"><title>Menu</title>
                        <path stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-miterlimit="10"
                              d="M4 7h22M4 15h22M4 23h22"></path>
                    </svg>
                </button>
            </form>
            <% if(type.equals("teacher")){%>
            <jsp:include page="leftside.jsp"></jsp:include>
            <% }else if(type.equals("student")){
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "index.jsp");
            %>
            <jsp:include page="leftside2.jsp"></jsp:include>
            <%
                }
            %>
            <!-- 左边导航结束 -->

            <main class="col-12 col-md-9 col-xl-8 py-md-3 pl-md-5 bd-content" role="main">
                    <h1 class="bd-title" id="content">添加作业</h1>
                    <form  action="HomeWorkAddServlet">
                    <div class="form-group">
                        <label >作业名:</label>
                        <input type="text" class="form-control" id="title" name="title">
                    </div>
                    <div class="form-group">
                        <label>日期:</label>
                        <input type="date" class="form-control" name="seldate">
                    </div>
                    <div class="form-group">
                        <label form="pwd">作业类型:</label>
                        <select class="form-control" name="type">
                            <option value='竞赛'>竞赛</option>
                            <option value='结对'>结对</option>
                        </select>
                    </div>
                        <div class="form-group">
                            <label >题目:</label>
                            <textarea type="text" class="form-control" id="details" name="details"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </form>
            </main>
        </div>
    </div>
</div>
</body>
</html>
