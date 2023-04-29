<%@page import="app.biokudi.model.EcoPlaces"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Editar lugar</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/acces.css" rel="stylesheet" type="text/css" />
    </head>
    <%
        EcoPlaces editPlace = (EcoPlaces) request.getAttribute("editPlace");
    %>
    <body>
        <h1>Actualizar registro</h1>
        <form action="Servlet" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="instruction" value="update">
            <input type="hidden" name="idPlace" value="<%=editPlace.getIdPlace()%>">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="name" value="<%=editPlace.getName()%>" /></td>
                </tr>
                <tr>
                    <td>Direccion</td>
                    <td><input type="text" name="address" value="<%=editPlace.getAddress()%>" /></td>
                </tr>
                <tr>
                    <td>Coordenadas</td>
                    <td><input type="text" name="coordinate" value="<%=editPlace.getCoordinate()%>" /></td>
                </tr>
                <tr>
                    <td>Actividad</td>
                    <td><textarea name="activity"><%=editPlace.getActivity()%></textarea></td>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td><textarea name="description"><%=editPlace.getDescription()%></textarea></td>
                </tr>
                <tr>
                    <td>Informacion</td>
                    <td><textarea name="information"><%=editPlace.getInformation()%></textarea></td>
                </tr>
            </table>
            <div class="boton">
                <button type="submit">Actualizar</button>
        </form>
        <form action="Servlet" method="GET">
            <button type="submit">Cancelar</button>
        </form>
    </div>
</body>
</html>