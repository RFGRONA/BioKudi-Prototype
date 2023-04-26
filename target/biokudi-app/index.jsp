<%@page import="app.biokudi.model.Lugares"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Lugares BioKudi</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css" integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />
        <link href="style/mapa.css" rel="stylesheet" type="text/css" />
    </head>

    <% List<Lugares> lugares = (List<Lugares>) request.getAttribute("listaLugares");
    String lugaresJson = new Gson().toJson(lugares);%>
    
    <body onload="addMarkers(map, JSON.parse(document.getElementById('lugares').value))">
        <div id="map"></div>
        <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
        <script src="script/mapa.js"></script>
        <div>
            <form class="boton" action="form.html" method="GET">
                <button type="submit">Tabla</button>
            </form>
        </div>
    </body>

</html>
