window.addEventListener('load', function () {


    //Se busca y obtiene el formulario donde están
    //los datos que el usuario desea modificar
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {

        let pacienteId = document.querySelector('#paciente_id').value;
        //Se crea un JSON que tendrá los datos del paciente
        //enviándose el id para poder identificarlo y modificarlo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            email: document.querySelector('#email').value,
            fechaIngreso: document.querySelector('#fecha_ingreso').value,
            domicilio:{
                calle: document.querySelector('#calle').value,
                numero:document.querySelector('#nro_calle').value,
                localidad: document.querySelector('#localidad').value,
                provincia:document.querySelector('#provincia').value
                }
            };

        //Se invoca la API con el método PUT,
        //que modificará el paciente que se envía en formato JSON
        
        const url = '/pacientes';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {

            console.log(data)


        }).catch(error=>{
            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 4000,
                timerProgressBar: true,
                didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })
            
            Toast.fire({
                icon: 'error',
                title: 'No se pudo actualizar el paciente. Reintente por favor.'
            })
        })

    })
})


    //Esta es la funcion que se invoca cuando se hace click sobre el 
    //id de un paciente del listado,
    //y se encarga de llenar el formulario con los datos del paciente
    //que se desea modificar
    function findBy(id) {
        const url = '/pacientes'+"/"+id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#paciente_id').value = paciente.id;

            document.querySelector('#nombre').value=paciente.nombre;
            document.querySelector('#apellido').value=paciente.apellido;
            document.querySelector('#dni').value=paciente.dni;
            document.querySelector('#email').value=paciente.email;
            document.querySelector('#fecha_ingreso').value=paciente.fechaIngreso;

            document.querySelector('#calle').value=paciente.domicilio.calle;
            document.querySelector('#nro_calle').value=paciente.domicilio.numero;
            document.querySelector('#localidad').value=paciente.domicilio.localidad;
            document.querySelector('#provincia').value=paciente.domicilio.provincia;


            //el formulario por default está oculto y, al editar, se habilita
            document.querySelector('#div_paciente_updating').classList.toggle("displayHidden");


        }).catch(error => {
            Swal.fire({
                icon: 'error',
                title: 'No se pudo encontrar el paciente'
            })
        })
    }