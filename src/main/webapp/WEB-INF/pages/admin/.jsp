<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>

        <link type="text/css" rel="stylesheet" href="UserHome_Css.css">
    </head>

    <body>

        <header>
            <h1 class="hd">National Ticket Booking Spot</h1>

            <!-- Home Hyperlik -->
            <div class="home">
                <p class="menu">
                    <a href="AdminHome.html">Home</a>
                </p>
            </div>

            <!-- View All trains Hyperink-->
            <div class="home">
                <p class="menu">
                    <a href="${pageContext.request.contextPath}/admin/viewAllTrains">View All Trains</a>
                </p>
            </div>

            <!-- Search a train using train Number -->
            <div class="home">
                <p class="menu">
                    <a href="${pageContext.request.contextPath}/admin/searchTrainByNumber">Search By Train Number</a>
                </p>
            </div>


            <!--Welcome message for the admin-->
            <div class="tab">
                <p class="menu">
                    Hey, Admin ! Welcom to our new ITRTC Website
                </p>
            </div>

            <div>
                <!-- Search Trains by Number - form-->
                <form action="adminSearchTrain" class="tab red" method="post">
                    TrainNumber: <input type="number" name="trainNumber">
                    <br>
                    <input type="submit" value="SEARCH TRAIN">
                </form>
            </div>
        </header>

    </body>

    </html>