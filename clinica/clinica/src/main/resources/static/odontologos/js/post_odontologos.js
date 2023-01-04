window.addEventListener('load', function () {

//Al cargar la página, se busca y obtiene el formulario donde estarán
//los datos del nuevo odontólogo que el usuario cargará 
const formulario = document.querySelector('#add_new_odontologo');

//Ante un submit del formulario se ejecutará la siguiente funcion
formulario.addEventListener('submit', function (event) {

    
    event.preventDefault();

    //Se crea un JSON que tendrá los datos del nuevo odontólogo
    const formData = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        matricula: document.querySelector('#matricula').value,

    };
    //Se invoca la API con el método POST, que guardará
    //el odontólogo que será enviado en formato JSON
    const url = '/odontologos';
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
                //Si no hay ningún error, se muestra un mensaje diciendo que el odontólogo
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
                title: 'Odontólogo agregado con éxito'
            })

            console.log(data);

            resetUploadForm();

        })
        .catch(error => {
                //Si hay algún error, se muestra un mensaje diciendo que el odontólogo
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
                    title: 'No se pudo guardar el odontólogo. Reintente por favor.'
                })

                console.error(error);

                    //se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
                resetUploadForm();
                })

    });


    //Función para resetear los campos del formulario
    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";

    }


});