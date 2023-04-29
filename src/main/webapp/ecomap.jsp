<%@page import="app.biokudi.model.EcoPlaces"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Mapa Interactivo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css" integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />
        <link href="style/map.css" rel="stylesheet" type="text/css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>

    <body>
        <div id="map"></div>
        <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
        <script src="script/map.js"></script>
        <div class="iframeContainer">
            <iframe id="infoFrame" frameborder="0" style="width: 100%; height: 100%;" src="info.html"></iframe>
        </div> 

        <script>
            function showLugarInfo(idPlace) {
                $.post({
                    url: "MapServlet",
                    data: {idPlace: idPlace},
                    success: function (data) {
                        $('#infoFrame').contents().find('body').html(data);
                    }
                });
            }
        </script>

        <c:forEach var="place" items="${listPlaces}">
            <c:set var="coords" value="${place.coordinate.split(',')}"/>
            <script>
                var coords = [<c:out value="${coords[0]}"/>, <c:out value="${coords[1]}"/>];
                var marker = L.marker(coords).addTo(map);
                marker.bindPopup('<h3><c:out value="${place.name}"/></h3>').on('click', function () {
                    showLugarInfo('<c:out value="${place.idPlace}"/>');
                });
            </script>
        </c:forEach>

    </body>

</html>
