# 11. Spring Web (Avanzado)

## 11.1 CORS

#### **Nombre proyecto Maven:** block11-cors (modificar block07-02-crud-validation)

#### **Tiempo estimado:** 2 horas

#

Permitir realizar peticiones entre dominios modificando el módulo del ejercicio CRUD con validación
para la ruta "/person".

Para probar ir a la página: https://codepen.io/de4imo/pen/VwMRENP 

Actualizar el back para que funcionen estas llamadas:

**Alta:** Tipo. POST - Ruta: http://localhost:8080/addperson

Objeto mandado:

    'usuario': document.getElementById('username').value,
    'password': document.getElementById('passwd').value,
    'name': document.getElementById('name').value,
    'surname': document.getElementById('surname').value,
    'company_email': document.getElementById('emailcomp').value,
    'personal_email': document.getElementById('emailpers').value,
    'city': document.getElementById('city').value,
    'active': document.getElementById('active').checked,
    'created_date': document.getElementById('created_date').value,
    'imagen_url': document.getElementById('imagen_url').value,
    'termination_date': document.getElementById('finish_date').value,})

**Consulta:** http://localhost:8080/getall

List<Person> (Mismos campos que en el add)

#

#


El código JavaScript de la página proporcionada en el ejercicio debe ser modificado
para que coincidan las variables de las funciones JS Submit con las variables Dto.

https://codepen.io/de4imo/pen/VwMRENP

## **JS**

    const formulario = document.getElementById('formulario');
    const body = document.body;

    var listaPersonas = [];

    //Mostrar la primera persona
    var index = 0;
    //Si no hay ninguna persona se recibe un CustomError*
    getPersons();

    function submitPersona(evento){
        console.log(evento);
        fetch('http://localhost:8080/persona/addperson',{
        method:'post',
        headers:{"Content-Type": "application/json"},
        body:JSON.stringify({
            'usuario': document.getElementById('username').value,
            'password': document.getElementById('passwd').value,
            'name': document.getElementById('name').value,
            'surname': document.getElementById('surname').value,
            'companyEmail': document.getElementById('emailcomp').value,
            'personalEmail': document.getElementById('emailpers').value,
            'city': document.getElementById('city').value,
            'active': document.getElementById('active').checked,
            'created_date': document.getElementById('created_date').value,
            'imagenUrl': document.getElementById('imagen_url').value,
            'termination_date': document.getElementById('finish_date').value,
        })
    })
    .then(function(response){ return response.json()})
    .then( evento.preventDefault())

    resetForm();
    }

    function getPersons(){

        fetch('http://localhost:8080/persona/getall')
    .then(function(resp){
        return resp.json();
    })
    .then(function(data){

        // Se recibe un custom error si no ha encontrado personas *
        console.log(data)
        listaPersonas = data;

        if(listaPersonas[0] != undefined){
            showPerson(0);
        }

    })
    }

    function nextPerson () {
        if (index < listaPersonas.length-1){
            index++;
            showPerson(index);
        }
    }
    function previousPerson () {
        if (index > 0){
            index--;
            showPerson(index);
        }
    }

    function showPerson(index){
        var persA = listaPersonas[index];

        document.getElementById('username').value = persA.usuario;
        document.getElementById('passwd').value = persA.password;
        document.getElementById('name').value = persA.name;
        document.getElementById('surname').value = persA.surname;
        document.getElementById('emailcomp').value = persA.companyEmail;
        document.getElementById('emailpers').value = persA.personalEmail;
        document.getElementById('city').value = persA.city;
        document.getElementById('active').checked = persA.active;
        document.getElementById('created_date').value = persA.createdDate;
        document.getElementById('imagen_url').value = persA.imagenUrl;
        document.getElementById('finish_date').value = persA.terminationDate;
    }

    function resetForm(){
        document.getElementById('formulario').reset();
    }
