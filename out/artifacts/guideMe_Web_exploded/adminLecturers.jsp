<%--
  Accessable at: 10.32.169.61:8081 (private network:port) OR the (local address: port)
  Created by IntelliJ IDEA.
  User: Faze
  Date: 20/02/2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<!-- Latest compiled and minified CSS -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>GuideMe - Log in form</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/creative.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body id="page-top">


<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            </button>
            <a class="navbar-brand page-scroll" href="#page-top">GuideME</a>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>



<header>
    <div class="header-content">
        <div class="header-content-inner">
            <h1 id="homeHeading"> Welcome Admin</h1>
            <hr>
            <p>You can use the form below to add new academics</p>

            <center>
                <form name="adminForm" method="post" action="Profile">
                    <input type="hidden" name="pagename" value="adminForm"/>
                    Academic name:          <input style="color: #0b0b0b" size="35" type = "text" name = "academicName" value= ""/><br>
                    Academic Role:          <input style="color: #0b0b0b" size="35" type = "text" name = "academicRole" value= ""/><br>
                    Academic Department:    <input style="color: #0b0b0b" size="35" type = "text" name = "academicDepartment" value= ""/><br>
                    Academic Email Address: <input style="color: #0b0b0b" size="35" type = "text" name = "academicEmail" value= ""/><br>
                    Academic Office:        <input style="color: #0b0b0b" size="35" type = "text" name = "academicOffice" value= ""/><br>
                    Academic Number:        <input style="color: #0b0b0b" size="35" type = "text" name = "academicNumber" value= ""/><br>
                    Academic Availability:  <textarea style="color: #0b0b0b" rows="4" cols="50" name = "academicAvailability"></textarea><br>
                    <button class="btn btn-primary btn-xl page-scroll" type="submit">Save Changes</button><br />
                </form>

                <!--to go back or sign out-->
                <script>
                    function goBack() {
                        window.history.back()
                    }
                </script>
                <br><br><button class="btn btn-primary btn-xl page-scroll" onclick="goBack()">Go Back</button>
                <br><br><form action="index.jsp">
                <input class="btn btn-primary btn-xl page-scroll" type="submit" value="Log out" />
            </form>
            </center>
        </div>
    </div>
</header>
</body>
</html>
