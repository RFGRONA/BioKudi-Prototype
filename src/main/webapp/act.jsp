<%@page import="app.biokudi.model.EcoPlaces"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Biokudi</title>
        <link rel="icon" type="image/x-icon" href="style/icon.svg">
        <link href="style/index.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Source+Serif+Pro:wght@400;600;700&display=swap"
              rel="stylesheet">
        <script src="https://kit.fontawesome.com/fc3ff67d7b.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <header>
            <div class="title">
                <h3> <i class="fa fa-compass" aria-hidden="true"></i> Biokudi</h3>
            </div>

            <nav>
                <ul>
                    <li>
                        <form action="Servlet" method="post">
                            <input type="hidden" name="instruction" value="activity">
                            <button class="button-header" type="submit">Actividades</button>
                        </form>
                    </li>

                    <li>
                        <form action="MapServlet" method="get">
                            <button class="button-header" type="submit">Mapa</button>
                        </form>
                    </li>
                </ul>
            </nav>
            <form action="form.html" method="get">
                <button class="button-header" type="submit"><i class="fa-solid fa-table-list"></i></button>
            </form>

        </header>
        <div class="table">
            <div style="text-align:center;">
                <c:forEach var="place" items="${listPlaces}">
                    <table>
                        <tr>
                        <div>
                            <td class="name"><c:out value="${place.name}"/></td>
                            <td rowspan="4" class="description"><c:out value="${place.description}"/></td>
                        </tr>
                        <tr>
                            <td class="address"><c:out value="${place.address}"/></td>
                        </tr>
                        <tr>
                            <td class="information"><c:out value="${place.information}"/></td>
                        </tr>
                    </table>
                </c:forEach>
            </div>

        </div>

        <footer>
            <p> Universidad de Cundinamarca - Extensión Facatativá </p>
            <p> Ingenieria de sistemas y computación - Programación 2 </p>
            <p> IPA - 2023 </p>
        </footer>
    </body>

</html>