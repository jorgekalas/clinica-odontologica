window.addEventListener('load', function(){


//Se llama a la API con GET, obteniendo el JSON de odontólogos
const url = '/odontologos';
const settings = {
    method: 'GET'
}

fetch(url, settings)
.then(response => response.json())
.then(data => {
    console.log(data);

    for(odontologo of data){
        //por cada odontólogo se arma una fila de la tabla
        //cada fila tendrá un id que luego permitirá borrar dicha fila si se elimina el odontólogo
        let table = document.getElementById("odontologoTable");
        let odontologoRow = table.insertRow();
        let tr_id = 'tr_' + odontologo.id;
        odontologoRow.id = tr_id;

        //por cada odontólogo, se crea un botón delete, que será agregado en cada fila 
        //para poder eliminar la misma.
        //Dicho botón, invocará a la funcion de java script deleteBy que se encargará
        //de llamar a la API para eliminar un odontologo
        let deleteButton = '<button' +
                                    ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                    ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                    '&times' +
                                    '</button>';

        //Por cada odontólogo, se crea un botón que muestra el id y que, al hacerle click, invocará a la función de java script findBy (DECLARADA EN update_odontologo.js) que se encargará de buscar el odontólogo que queremos
        //modificar y mostrar los datos del mismo.
        let updateButton = '<button' +
                                    ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                    ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                    odontologo.id +
                                    '</button>';

        //se arma cada columna de la fila
        //como primer columna, se coloca el botón modificar,
        //luego los datos del odontólogo
        //y finalmente el botón eliminar
        odontologoRow.innerHTML = 
                        '<td>' + updateButton + '</td>' +
                            '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() +
                            '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                            '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>'+
                        '<td>' + deleteButton + '</td>';

        };
    })
});