<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BHOOKAD - List of Locations</title>

    <!-- Link to Google Fonts for cartoon-like handwriting -->
    <link href="https://fonts.googleapis.com/css2?family=Comic+Neue:wght@700&display=swap" rel="stylesheet">

    <style>
        /* General styles */
        body {
            font-family: "Lilita One", sans-serif;
            font-weight: 400;
            font-style: normal;
            margin: 0;
            padding: 0;
            background-color: #f0f8ff; /* Light blue background */
            color: #333;
        }

        .navbar {
            background-color: #ff6347;
            overflow: hidden;
            text-align: center;
            border-bottom: 4px solid #ffcccb;
        }

        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 16px 20px;
            text-decoration: none;
            font-size: 18px;
            transition: 0.3s;
        }

        .navbar a:hover {
            background-color: #ffa07a;
            color: white;
        }

        .navbar a.active {
            background-color: #ff4500;
            color: white;
        }

        .heading {
            font-family: 'Comic Neue', cursive;
            font-size: 90px;
            color: #ff4500;
            text-align: center;
            margin-top: 50px;
            letter-spacing: 3px;
            text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.2);
        }

        .main {
            text-align: center;
            padding: 50px;
        }

        h1 {
            color: #333;
        }

        .content {
            margin: 20px auto;
            padding: 20px;
            max-width: 1000px;
            background-color: white;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            border-radius: 12px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #ff6347;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .footer {
            background-color: #ff6347;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

    <!-- Fun Cartoon-like Heading -->
    <div class="heading">BHOOKAD</div>

    <!-- Navigation Bar -->
 	<jsp:include page="AdminNavigation.jsp"></jsp:include>


    <!-- Main Content Section -->
    <div class="main">
        <h1>List of Locations</h1>
        <div class="content">
            <!-- Table to display the list of locations -->
            <table>
                <thead>
                    <tr>
                        <th>Location ID</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>City Name</th>
                        <th>Area Name</th>
                        <th>Timings</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Food Type</th>
                         <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Using JSTL to iterate through the list of locations -->
                    <c:forEach items="${locations}" var="location">
                        <tr>
                            <td>${location.locationId}</td>
                            <td>${location.title}</td>
                            <td>${location.category}</td>
                            <td>${location.cityName}</td>
                            <td>${location.areaName}</td>
                            <td>${location.timings}</td>
                            <td>${location.latitude}</td>
                            <td>${location.longitude}</td>
                            <td>${location.foodType}</td>
                            <td>
                <a href="updateLocation?id=${location.locationId}" style="color: #ff6347; text-decoration: none;">Update</a> |
                 <a href="deleteUserbyId?id=${user.userId}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


    <!-- Footer Section -->
    <div class="footer">
        &copy; 2024 Bhookad | All Rights Reserved
    </div>

</body>
</html>