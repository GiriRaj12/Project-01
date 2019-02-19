<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Organic Store</title>
</head>
<style>
.header{
  font-family:sans-serif;;
  background-color:MediumSeaGreen;
  box-shadow: 0 0 18px #6699ff;
  opacity:1;
  padding:5px;
  }
 .Company{
   float:left;
   text-decoration:none;
   font-size:30px;
   margin-left:20px;
   margin-top:10px;
  }
.list {
  float:right;
  }
.list a,.Company a{
  text-decoration:none;
  color:white;
  margin-right:10px;
    }
li{
  display:inline-block;
  margin-left:0px;
  margin-right:0px;
  color:#ffff;
  }
.clearfix::after{
    content:'';
    display:block;
    clear:both;
  }
.Fruits{
    position:relative;
    float:left;
    margin-left:60px;
    padding:15px;
    display:inline-block;
    width:100%;
    }p{
    font-size:35px;
    color:#ffff;
    font-family:sans-serif;
    }
.Vegetables,.medicinals{
    positon:relative;
    float:left;
    padding:15px;
    display:inline-block;
    margin-left:50px;
    width:100%;
    }p{
    font-size:35px;
    color:#ffff;
    font-family:sans-serif;
    }
.button{
    width:100%;
    float:left;
    padding:20px;
    margin-left:50px;
    margin-right:0px;
    }
    .button button{
    font-size:20px;
    margin-left:150px;
    }
body{
    background-image:url("http://www.ascendskills.com/data/archive/img/938560745.jpeg");
    background-size:cover;
    repeat:none;
    }
  
  </style>
  <header>
    <div class="header clearfix">
      <div class="Company">
        <a href="index.html">Organic Store</a>
      </div>
      <ul class="list">
      <li><a href="Products.jsp">Home</a></li>
      <li><form action="logout">
      <button>Logout</button>
      </form></li>
      </ul>
      </div>
      </header>
  <body>
  <%
HttpSession ses=request.getSession(false);
 String userc=(String)ses.getAttribute("User");
 String pass=(String)ses.getAttribute("Pass");
 if(userc==null||pass==null){
	 response.sendRedirect("Validate.jsp");
 }
%>
    <div class="Fruits">
        <p>Fruits:</p>
      <img src="https://i.imgur.com/SOarLFi.jpg" height="200px" style="margin-left:20px">
      <img src="http://pureoilguide.com/wp-content/uploads/2015/10/Oranges.jpg" height="200px" style="margin-left:20px">
      <img src="https://ripeme.com/wp-content/uploads/RF-10012-RIPE-ORGANIC-ORGANIC-BANANAS.jpg" height="200px" style="margin-left:20px">
      <img src="https://5.imimg.com/data5/XE/PC/MY-18928303/fresh-kiwi-fruit-500x500.jpg" height="200px" style="margin-left:20px">
      <img src="https://5.imimg.com/data5/KI/YJ/MY-26818540/fresh-mango-500x500.jpg" height="200px" style="margin-left:20px">
      </div>
      <div class="button">
      <div class="b1">
        <form action="Kart.jsp" method="post">
        <button name="fruit" value="Apple">Apple</button>
        <button name="fruit" value="Orange">Orange</button>
        <button name="fruit" value="Banana">Banana</button>
        <button name="fruit" value="Kiwi">Kiwi</button>
        <button name="fruit" value="Mango">Mango</button>
        </form>
      </div>
      </div>
      <div class="Vegetables">
      <p>Vegetables:</p>
        <img src="https://timedotcom.files.wordpress.com/2018/10/cabbage.jpg" height="160px" style="margin-left:20px">
        <img src="http://www.indian30.in/wp-content/uploads/2017/12/carrot.jpg" height="160px" style="margin-left:20px">
        <img src="https://cdn1.medicalnewstoday.com/content/images/articles/277/277432/beetroot-on-a-white-background.jpg" height="160" style="margin-left:20px"> 
        <img src="http://www.koshercrops.com/uploads/9/4/4/1/9441768/s425178637313967570_p8_i1_w1000.jpeg" height="160" style="margin-left:20px">
        <img src="https://4.imimg.com/data4/WV/SR/MY-7344877/organic-big-brinjal-bharit-vangi-500x500.jpg" height="160px" style="margin-left:20px">
        </div>
        <div class="button">
      <div class="b2">
      <form action="Kart.jsp" method="post">
        <button name="veg" value="Cabbage">Cabbage</button>
        <button name="veg" value="Carrot">Carrot</button>
        <button name="veg" value="Beetroot">Beetroot</button>
        <button name="veg" value="Broccoli">Broccoli</button>
        <button name="veg" value="Brinjal">Brinjal</button>
        </form>
        </div>
        </div>
        <p>Medicinal Organics</p>
        <div class="medicinal">
        <img src="https://cdn.firstcrycdn.com/2018/02/223269154-H.jpg" height="180px" style="margin-left:100px">
        <img src="https://tiimg.tistatic.com/fp/1/003/426/athimathuram-288.jpg" height="180px" style="margin-left:100px;">
        </div>
        <div class="button">
        <form action="Kart.jsp" method="post">
        <button name="med" value="Tulsi">Tulsi</button>
        <button name="med" value="Barks">Medcinal Barks</button>
        </form>
        </div>
        <div class="footer">
        <p style="float:left;">Contact:+12345</p><p style="float:right">Organic Store<p>
        </div>
  </body>
</html>