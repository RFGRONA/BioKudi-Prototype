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
        <form class="boton" action="form.html">
            <button type="submit"> Volver a inicio</button>
        </form>
    </body>
</html>
