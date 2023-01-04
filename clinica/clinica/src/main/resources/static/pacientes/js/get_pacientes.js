window.addEventListener('load', function(){

//Se llama a la API con GET, obteniendo el JSON de pacientes
const url = '/pacientes';
const settings = {
    method: 'GET'
}

fetch(url, settings)
.then(response => response.json())
.then(data => {
    console.log(data);

    for(paciente of data){
        //por cada paciente, se arma una fila de la tabla.
        //Cada fila tendrá un id que luego permitirá borrar la fila 
        //si se elimina el paciente
        let table = document.getElementById("pacienteTable");
        let pacienteRow = table.insertRow();
        let tr_id = 'tr_' + paciente.id;
        pacienteRow.id = tr_id;

        //por cada paciente, se crea un botón delete que se agrega en cada fila 
        //para poder eliminar la misma
        //dicho botón invocará a la función deleteBy, que se encargará
        //de llamar a la API para eliminar un paciente
        let deleteButton = '<button' +
                                    ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                    ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                    '&times' +
                                    '</button>';

        //por cada paciente, se crea un botón que muestra el id y que, al hacerle click, invocará
        //a la función de java script findBy (DECLARADA EN update_paciente.js) que se encargará de buscar el paciente que se desea modificar
        let updateButton = '<button' +
                                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                        ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                        paciente.id +
                                        '</button>';

        //Se arma cada columna de la fila:
        //como primer columna se coloca el botón modificar,
        //luego los datos del paciente,
        //y como última columna el boton eliminar
        pacienteRow.innerHTML = 
                        '<td>' + updateButton + '</td>' +
                            '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() +
                            '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                            '<td class=\"td_dni\">' + paciente.dni+ '</td>'+
                            '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +
                            '<td class=\"td_fecha_ingreso\">' +paciente.fechaIngreso.split('-').reverse().join('/') + '</td>' +
                            '<td class=\"domicilio\">' + `${paciente.domicilio.calle.toUpperCase()} ${paciente.domicilio.numero}, ${paciente.domicilio.localidad.toUpperCase()} (${paciente.domicilio.provincia.toUpperCase()}) `+ '</td>' +
                        '<td>' + deleteButton + '</td>';
                        
        };
    })
});

