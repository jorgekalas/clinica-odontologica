function deleteBy(id)
{
    //Se invoca a la API con el método DELETE, pasándole el id en la URL
    const url = '/odontologos/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(response => response.json())

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
        title: 'Odontólogo eliminado con éxito'
    })

    //Se borra la fila del odontologo eliminado
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
}