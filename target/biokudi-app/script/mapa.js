var map = L.map('map').setView([4.863333, -74.052778], 9);
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

document.addEventListener('DOMContentLoaded', function() {
  var lugares = JSON.parse('<%=lugaresJson%>');
  addMarkers(map, lugares);
});

function addMarkers(map, lugares) {
  for (var i = 0; i < lugares.length; i++) {
    var lugar = lugares[i];
    var coords = lugar.coordenadas.split(/,\s*/); // dividir la cadena en dos partes
    var latlng = L.latLng(coords[0], coords[1]); // crear el objeto L.LatLng
    var marker = L.marker(latlng).addTo(map); // añadir el marcador al mapa

    // añadir la información del lugar al marcador como una propiedad personalizada
    marker.lugar = lugar;

    // agregar el evento clic para mostrar la información del lugar
    marker.on('click', function(e) {
      showInfo(e.target.lugar);
    });
  }
}