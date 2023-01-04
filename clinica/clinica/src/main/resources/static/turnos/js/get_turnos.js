window.addEventListener('load', function () {


    //1. Get de todos los odontólogos para insertarlos en el Select
    const urlOd = '/odontologos';
    const settingsOd = {
        method: 'GET'
    }

    fetch(urlOd, settingsOd)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        let selectOdontologos = document.getElementById('selectOdontologos');
        let selectOdontologosUpdate = document.getElementById('selectOdontologos_update');

        console.log("SELECT ODONTOLOGOS:")
        console.log(selectOdontologos)
        console.log("SELECT ODONTOLOGOS UPDATE:")
        console.log(selectOdontologosUpdate)
        

        for(odontologo of data){
        let option = `<option value="${odontologo.id}" id="odontologo${odontologo.id}">${odontologo.nombre} ${odontologo.apellido} </option>`
        console.log("TAG OPTION: " + option)
        selectOdontologos.innerHTML += option;
        console.log(option)
        }

        for(odontologo of data){
            let option = `<option value="${odontologo.id}" id="odontologoOption_update${odontologo.id}">${odontologo.nombre} ${odontologo.apellido} </option>`
            console.log("TAG OPTION: " + option)
            selectOdontologosUpdate.innerHTML += option;
            console.log(option)
        }


    })

    //2. Get de todos los pacientes para insertarlos en el Select
    const urlP = '/pacientes';
    const settingsP = {
        method: 'GET'
    }

    fetch(urlP, settingsP)
    .then(response => response.json())
    .then(data => {
        console.log(data);

        let selectPacientes = document.getElementById('selectPacientes');
        let selectPacientesUpdate = document.getElementById('selectPacientes_update');
        console.log(selectPacientes);

        for(paciente of data){

        let option = `<option value="${paciente.id}" id="paciente${paciente.id}">${paciente.nombre} ${paciente.apellido} </option>`

        console.log("TAG OPTION: " + option)
        selectPacientes.innerHTML += option;

        console.log(selectPacientes)
        }

        for(paciente of data){

            let option = `<option value="${paciente.id}" id="pacienteOption_update${paciente.id}">${paciente.nombre} ${paciente.apellido} </option>`
    
            console.log("TAG OPTION: " + option)
            selectPacientesUpdate.innerHTML += option;
    
            console.log(selectPacientes)
        }




    })



    // 3. Desarrollo del método get para los turnos, ahora que
    // se cuenta con los datos necesarios de los pacientes y odontólogos


    const url = '/turnos';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
    .then(response => response.json())
    .then(data => {
        console.log("TURNOS: ");
        console.log(data);


        for(turno of data){
            //por cada turno se arma una fila, que
            //tendrá un id para poder borrarla si se elimina el turno
            let table = document.getElementById("turnoTable");
            let turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            //por cada turno, se crea un botón delete que se agrega en cada fila 
            //para poder eliminar la misma,
            //dicho botón invocará a la función deleteBy, que se encargará
            //de llamar a la API para eliminar el turno
            let deleteButton = '<button' +
                                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                        ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                        '&times' +
                                        '</button>';

            //por cada turno, se crea un botón que muestra el id y que, al hacerle click, invocará
            //a la función de java script findBy (DECLARADA EN update_turno.js) que se encargará de buscar el turno a modificar, para luego mostrar los datos del mismo.
            let updateButton = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                            ' type="button" onclick="findByTurno('+turno.id+')" class="btn btn-info btn_id">' +
                                            turno.id +
                                            '</button>';

            //Se arma cada columna de la fila:
            //como primer columna, se coloca el botón modificar,
            //luego los datos del turno,
            //y como última columna el botón eliminar
            turnoRow.innerHTML = 
                            '<td>' + updateButton + '</td>' +
                                '<td class=\"td_fecha\">' + turno.fecha.split('-').reverse().join('/') + '</td>' +
                                '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + " " + turno.paciente.apellido.toUpperCase() + '</td>' +
                                '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + " " + turno.odontologo.apellido.toUpperCase() + '</td>' +
                            '<td>' + deleteButton + '</td>';
            }
        })
    })