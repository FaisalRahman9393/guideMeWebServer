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
            <p>Please populate the form about the new department</p>
            <center>
                <form name="facilitiesFormEditUpdate" method="post" action="Facilities">
                    <% String name=(String)request.getAttribute("name"); %>
                    <% String loca=(String)request.getAttribute("location"); %>
                    <% String hour=(String)request.getAttribute("hour"); %>
                    <% String info=(String)request.getAttribute("info"); %>

                    <input type="hidden" name="pagename" value="facilitiesFormEditUpdate"/>
                    Facility Name: <%=name%><br />
                    Facility Location:          <input style="color: #0b0b0b" size="35" type = "text" name = "facilitiesLocation" value= "<%=loca%>"/><br />
                    Facility Opening hours:    <input style="color: #0b0b0b" size="35" type = "text" name = "facilitiesHours" value= "<%=hour%>"/><br />
                    Facility Information: <input style="color: #0b0b0b" size="35" type = "text" name = "facilitiesInfo" value= "<%=info%>"/><br/>
                    <button class="btn btn-primary btn-xl page-scroll" type="submit">Save Changes</button><br />
                </form>
                <br>
            </center>
        </div>
    </div>
</header>
</body>
</html>