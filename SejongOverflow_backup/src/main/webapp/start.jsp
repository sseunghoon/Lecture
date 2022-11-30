<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO" %>   
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>세종대학교 소프트웨어융합대학</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/mainpage.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/Backstretch.js" type="text/javascript"></script>
    <script src="js/mainpage.js"></script>
  </head>
  <body>
     <script>
      $.backstretch(["imgs/drop_1.jpg","imgs/drop_2.jpg","imgs/drop_3.jpg","imgs/drop_4.jpg","imgs/drop_5.jpg",
         "imgs/drop_6.jpg","imgs/drop_7.jpg","imgs/drop_7.jpg","imgs/start_button.jpg"],
         {duration:-10, fade: 900});
  
      $(window).on("backstretch.before", function (e, instance, index) {
         // If we wanted to stop the slideshow after it reached the end
         if (index === instance.images.length - 1) {
           instance.pause();
         };
       });
      $(".backstretch").on("click", function () {
         document.location.href = "index.jsp";
       });

    </script>
  </body>