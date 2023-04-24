<%@page import="app.biokudi.model.Lugares"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Editar lugar</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/ingreso.css" rel="stylesheet" type="text/css" />
    </head>
    <%
        Lugares editarLugar = (Lugares) request.getAttribute("editarLugar");
    %>
    <body>
        <h1>Actualizar registro</h1>
        <form action="Servlet" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="instruccion" value="actualizar">
            <input type="hidden" name="idLugar" value="<%=editarLugar.getIdLugar()%>">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%=editarLugar.getNombre()%>" /></td>
                </tr>
                <tr>
                    <td>Direccion</td>
                    <td><input type="text" name="direccion" value="<%=editarLugar.getDireccion()%>" /></td>
                </tr>
                <tr>
                    <td>Coordenadas</td>
                    <td><input type="text" name="coordenadas" value="<%=editarLugar.getCoordenada()%>" /></td>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td><textarea name="descripcion"><%=editarLugar.getDescripcion()%></textarea></td>
                </tr>
                <tr>
                    <td>Actividad</td>
                    <td><textarea name="actividad"><%=editarLugar.getActividad()%></textarea></td>
                </tr>
                <tr>
                    <td>Informacion</td>
                    <td><textarea name="informacion"><%=editarLugar.getInformacion()%></textarea></td>
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
