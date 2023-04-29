
<%@page import="app.biokudi.model.EcoPlaces"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Base de datos</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/table.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        List<EcoPlaces> listPlaces = (List<EcoPlaces>) request.getAttribute("listPlaces");
    %>
    <body>
        <h1>Tabla de registros</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th style="width:3.6%">ID</th>
                        <th style="width:13%">Nombre</th>
                        <th style="width:10.1%">Dirección</th>
                        <th style="width:10.1%">Coordenadas</th>
                        <th style="width:13%">Actividad</th>
                        <th style="width:20%">Descripción</th>
                        <th style="width:20%">Información</th>
                        <th style="width:10.2%">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (listPlaces != null) {
                            for (EcoPlaces place : listPlaces) {%>
                    <tr>
                        <td><%= place.getIdPlace()%></td>
                        <td><%= place.getName()%></td>
                        <td><%= place.getAddress()%></td>
                        <td><%= place.getCoordinate()%></td>
                        <td><%= place.getActivity()%></td>
                        <td><%= place.getDescription()%></td>
                        <td><%= place.getInformation()%></td>
                        <td>
                            <div class="hacer">
                                <form action="Servlet" method="POST">
                                    <input type="hidden" name="instruction" value="delete">
                                    <input type="hidden" name="idPlace" value="<%=place.getIdPlace()%>">
                                    <button type="submit">Eliminar</button>
                                </form>
                                <form action="Servlet" method="POST">
                                    <input type="hidden" name="instruction" value="edit">
                                    <input type="hidden" name="idPlace" value="<%=place.getIdPlace()%>">
                                    <button type="submit">Editar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td colspan="8">No hay lugares para mostrar</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <form class="boton" action="form.html">
            <button type="submit"> Volver a inicio</button>
        </form>
    </body>
</html>