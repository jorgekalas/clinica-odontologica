window.addEventListener('load', function () {


    //Se busca y obtiene el formulario donde están
    //los datos que el usuario desea modificar
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {

        let odontologoId = document.querySelector('#odontologo_id').value;
        //Se crea un JSON que tendrá los datos del odontólogo
        //enviándose el id para poder identificarlo y modificarlo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        //Se invoca la API con el método PUT 
        //que modificará el odontólogo, que será enviado en formato JSON
        
        const url = '/odontologos';
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
                title: 'No se pudo actualizar el odontólogo. Reintente por favor.'
            })
        })
        });
    })


    //Esta es la funcion que se invoca cuando se hace click sobre el 
    //id de un odontologo del listado,
    //y se encarga de llenar el formulario con los datos del odóntologo
    //que se desea modificar
    function findBy(id) {
        const url = '/odontologos'+"/"+id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;
            document.querySelector('#matricula').value = odontologo.matricula;
            //el formulario por default está oculto y, al editar, se habilita
            let divUpdate = document.querySelector('#div_odontologo_updating').classList.toggle("displayHidden");


        }).catch(error => {
            Swal.fire({
                icon: 'error',
                title: 'No se pudo encontrar el odontólogo'
            })
        })
    }