<%@page import="app.biokudi.model.Lugares"%>
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
        List<Lugares> listaLugares = (List<Lugares>) request.getAttribute("listaLugares");
    %>
    <body>
        <h1>Tabla de registros</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Dirección</th>
                        <th>Coordenadas</th>
                        <th>Actividad</th>
                        <th>Descripción</th>
                        <th>Información</th>
                        <th style="width:130px">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (listaLugares != null) {
                            for (Lugares lugar : listaLugares) {%>
                    <tr>
                        <td><%= lugar.getIdLugar()%></td>
                        <td><%= lugar.getNombre()%></td>
                        <td><%= lugar.getDireccion()%></td>
                        <td><%= lugar.getCoordenada()%></td>
                        <td><%= lugar.getActividad()%></td>
                        <td><%= lugar.getDescripcion()%></td>
                        <td><%= lugar.getInformacion()%></td>
                        <td>
                            <div class="hacer">
                                <form action="Servlet" method="POST">
                                    <input type="hidden" name="instruccion" value="eliminar">
                                    <input type="hidden" name="idLugar" value="<%=lugar.getIdLugar()%>">
                                    <button type="submit">Eliminar</button>
                                </form>
                                <form action="Servlet" method="POST">
                                    <input type="hidden" name="instruccion" value="editar">
                                    <input type="hidden" name="idLugar" value="<%=lugar.getIdLugar()%>">
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
        <form class="boton" action="index.html">
            <button type="submit"> Volver a inicio</button>
        </form>
    </body>
</html>
