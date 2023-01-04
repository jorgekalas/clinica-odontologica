window.addEventListener("load", function () {
    //Se busca y obtiene el formulario donde estarán
    //los datos que el usuario cargará del nuevo turno
    const formulario = document.querySelector("#add_new_turno");

    formulario.addEventListener("submit", function (event) {
        event.preventDefault();


        //Se obtienen los datos del paciente
        let idPaciente = document.querySelector("#selectPacientes").value;
        console.log("ID PACIENTE");
        console.log(idPaciente);
        
        let idOdontologo = document.querySelector("#selectOdontologos").value;
        console.log("ID ODONTOLOGO");
        console.log(idOdontologo);

        const urlP = "/pacientes" + "/" + idPaciente;
        const settingsP = {
        method: "GET",
        };
        fetch(urlP, settingsP)
        .then((response) => response.json())
        .then((dataPaciente) => {

            console.log("DATA PACIENTE");
            console.log(dataPaciente);


            //Se obtienen los datos del odontólogo
            const urlO = "/odontologos" + "/" + idOdontologo;
            const settingsO = {
            method: "GET",
            };
            fetch(urlO, settingsO)
            .then((response) => response.json())
            .then((dataOdontologo) => {

                console.log("DATA ODONTOLOGO")
                console.log(dataOdontologo)


                //Comienza el desarrollo del método POST del turno

                //Se crea un objeto que tendrá los datos del nuevo turno
                const formData = {
                fecha: document.querySelector("#fecha").value,
                odontologo: dataOdontologo,
                paciente: dataPaciente
                };

                console.log("FORM DATA: DEBE ESTAR TODO EN JS, NADA EN JSON");
                console.log(formData); //ok!!

                //Se invoca la API con el método POST, que guardará
                //el turno enviado en formato JSON
                const url = "/turnos";
                const settings = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
                };

                console.log("PAYLOAD/SETTINGS");
                console.log(settings);

                fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    console.log("DATA ENVIADA EN EL POST:");
                    console.log(data);
                });

                const Toast = Swal.mixin({
                toast: true,
                position: "top-end",
                showConfirmButton: false,
                timer: 4000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener("mouseenter", Swal.stopTimer);
                    toast.addEventListener("mouseleave", Swal.resumeTimer);
                },
                });

                Toast.fire({
                icon: "success",
                title: "Turno agregado con éxito",
                });

                });
            });
        });
    });

    //Función de reseteo de los campos del formulario

    function resetUploadForm() {
        document.querySelector("#fecha").value = "";
        document.querySelector("#selectPacientes").value = "";
        document.querySelector("#selectOdontologos").value = "";
    }
