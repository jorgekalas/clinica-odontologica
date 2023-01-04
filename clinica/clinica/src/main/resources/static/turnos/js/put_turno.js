
    function findByTurno(id) {
        const url = '/turnos'+"/"+id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;

            console.log(data)

            document.querySelector('#turno_id_update').value = turno.id;
            document.querySelector('#fecha_update').value=turno.fecha;


        //Se obtienen los datos de los pacientes
        const urlp = '/pacientes'
        const settingsp = {
            method: 'GET'
        }
        fetch(urlp,settingsp)
        .then(response => response.json())
        .then(dataPacientes => {
            console.log(dataPacientes)
            let pacientes = dataPacientes;

            for(paciente of pacientes){
                let selectPacientesUpdate = document.getElementById('selectPacientes_update');
                let option = `<option value="${paciente.id}" id="pacienteOption_update${paciente.id}">${paciente.nombre} ${paciente.apellido} </option>`
                console.log("TAG OPTION: " + option)
                selectPacientesUpdate.innerHTML += option;
                console.log(option)
            }
    
    //Se obtienen los datos de los odontólogos
        const urlo = '/odontologos'
        const settingso = {
            method: 'GET'
        }
        fetch(urlo,settingso)
        .then(response => response.json())
        .then(dataOdontologos => {
            let odontologos = dataOdontologos;

            for(odontologo of odontologos){
                let selectOdontologosUpdate = document.getElementById('selectOdontologos_update');
                let option = `<option value="${odontologo.id}" id="odontologoOption_update${odontologo.id}">${odontologo.nombre} ${odontologo.apellido} </option>`
                console.log("TAG OPTION: " + option)
                selectOdontologosUpdate.innerHTML += option;
                console.log(option)
            }


            //el formulario por default está oculto y, al editar, se habilita
            document.querySelector('#div_turno_updating').classList.toggle("displayHidden");


            //Inicio del método PUT para actualizar los datos del turno

            const formulario = document.querySelector('#update_turno_form');

            formulario.addEventListener('submit', function (event) {

            event.preventDefault();


            let fechaUpdate = document.querySelector('#fecha_update').value
            let idPacienteUpdate = document.querySelector("#selectPacientes_update").value;
            let idOdontologoUpdate = document.querySelector("#selectOdontologos_update").value;
        

        //Se obtienen los datos del paciente a actualizar
        const urlP = "/pacientes" + "/" + idPacienteUpdate;
        const settingsP = {
        method: "GET",
        };
        fetch(urlP, settingsP)
        .then((response) => response.json())
        .then((dataPaciente) => {
        

            //Se obtienen los datos del odontólgo a actualizar
            const urlO = "/odontologos" + "/" + idOdontologoUpdate;
            const settingsO = {
            method: "GET",
            };
            fetch(urlO, settingsO)
            .then((response) => response.json())
            .then((dataOdontologo) => {
                


            const formData = {
                id: turno.id,
                fecha: fechaUpdate,
                paciente: dataPaciente,
                odontologo: dataOdontologo
                };

                console.log("DATA A ENVIAR EN FORMDATA")
                console.log(formData)




                
            

                 //Se invoca la API con el método PUT 
                //que modificará el turno enviado en formato JSON
        
                const url2 = '/turnos';
                const settings2 = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                }


                fetch(url2,settings2)
                .then(response => response.json())
                .then(data => {
                    console.log("DATA DESDE EL PUT")
                    console.log(data)



                resetUploadForm()

                location.replace('turnosLista.html')


                })
            })
        })
    })
    })
    })
    })
}
    

    //Función de reseteo de los campos del formulario
    
    function resetUploadForm(){
        document.querySelector('#fecha_update').value = "";
        document.querySelector("#selectPacientes_update").value = "";
        document.querySelector("#selectOdontologos_update").value = "";

    }