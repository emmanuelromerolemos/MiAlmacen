

    /*funcion que agrega datos a la tabla*/
    function agregar(id) {
    
      var producto = document.getElementById(id).value;
      var table = document.getElementById("mytable");

      var row = table.insertRow(-1);
      var cell1 = row.insertCell(0);
      var cell2 = row.insertCell(1);
      var cell3 = row.insertCell(2);
      var cell4 = row.insertCell(3);
      var cell5 = row.insertCell(4);

      /*modificacion de las celdas asignando los datos obtenidos del formulario*/
      cell1.innerHTML = '<p name="producto">'+producto+'</p>';
      cell2.innerHTML = '<input type="number" name="cantidad"></input>';      
      cell3.innerHTML = '<input type="number" name="precio"></input>';
     
      /*ultima columna con boton borrar, dentro del llamado se envia el elemento HTML button con this*/
      cell4.innerHTML = '<button type="button" name="remove" onclick="borrar(this)" class="btn btn-danger">Quitar</button>';

    }//fin funcion agregar

    /*funcion que borra datos de la tabla*/
    function borrar(r) {

       //obtengo del DOM el indice de la fila con rowIndex
       var i = r.parentNode.parentNode.rowIndex;

       /*en la consola podremos observar cada uno de los elementos que obtenemos con el selector "parentNode" (elemento padre o elemento superior)*/

       /*la funcion borrar recibe el button, el parametro es r, linea 40*/
       console.log(r)//<button type="button" name="remove" onclick="borrar(this)" class="btn btn-danger">Quitar</button>

       /*con el primer parentNode obtenemos el <td>*/
       console.log(r.parentNode)//<td></td>

       /*con el segundo parentNode obtenemos el <tr>*/
       console.log(r.parentNode.parentNode)//<tr></tr>

       /*borramos la fila de la tabla con deleteRow pasandole el indice obtenido en la linea 43*/
       document.getElementById("mytable").deleteRow(i);

     }//fin funcion borrar


     