window.addEventListener('load', function () {

    //Al cargar la página, se busca y obtiene el formulario donde estarán
    //los datos del nuevo paciente que el usuario cargará 
    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario, se ejecutará la siguiente funcion:
    formulario.addEventListener('submit', function (event) {

        
        event.preventDefault();

       //Se crea un JSON que tendrá los datos del nuevo paciente
        const formData = {
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
        //Se invoca la API con el método POST, que guardará
        //el paciente enviado en formato JSON
        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningún error se muestra un mensaje diciendo que el paciente
                 //se agregó correctamente

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
                    icon: 'success',
                    title: 'Paciente agregado con éxito'
                })

                console.log(data);

                resetUploadForm();


            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el paciente
                    //no se pudo guardar y se intente nuevamente


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
                        title: 'No se pudo guardar el paciente. Reintente por favor.'
                    })

                    console.error(error);

                    resetUploadForm();
                    })
    });


    //Función para el reseteo del formulario

    function resetUploadForm(){
        document.querySelector('#nombre').value="";
        document.querySelector('#apellido').value="";
        document.querySelector('#dni').value="";
        document.querySelector('#email').value="";
        document.querySelector('#fecha_ingreso').value="";

        document.querySelector('#calle').value="";
        document.querySelector('#nro_calle').value="";
        document.querySelector('#localidad').value="";
        document.querySelector('#provincia').value="";
    }

});