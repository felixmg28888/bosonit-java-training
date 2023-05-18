package com.bosonit.formacion.block1201criteriabuilder.persona.application;

import com.bosonit.formacion.block1201criteriabuilder.exception.EntityNotFoundException;
import com.bosonit.formacion.block1201criteriabuilder.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block1201criteriabuilder.persona.domain.Persona;
import com.bosonit.formacion.block1201criteriabuilder.persona.repository.PersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) {
        //Primero realizo un control de excepciones del json del PersonaInputDto
        if (personaInputDto.getUsuario() == null) {
            throw new UnprocessableEntityException(" Usuario no puede ser nulo. ");
        }
        if (personaInputDto.getUsuario().length() < 3) {
            throw new UnprocessableEntityException(" Mínimo 3 caracteres.");
        }
        if (personaInputDto.getUsuario().length() > 10) {
            throw new UnprocessableEntityException(" Máximo 10 caracteres.");
        }
        if (personaInputDto.getPassword() == null) {
            throw new UnprocessableEntityException(" Password no puede ser nulo. ");
        }
        if (personaInputDto.getName() == null) {
            throw new UnprocessableEntityException(" Name no puede ser nulo. ");
        }
        if (personaInputDto.getCompanyEmail() == null) {
            throw new UnprocessableEntityException(" Company_email no puede ser nulo. ");
        }
        if (personaInputDto.getPersonalEmail() == null) {
            throw new UnprocessableEntityException(" Personal_email no puede ser nulo. ");
        }
        if (personaInputDto.getCity() == null) {
            throw new UnprocessableEntityException(" City no puede ser nulo. ");
        }
        if (personaInputDto.getActive() == null) {
            throw new UnprocessableEntityException(" Active no puede ser nulo. ");
        }


        //Una vez el PersonaInputDto ha sido chequeado
        //Instancio persona con el constructor vacío.
        Persona persona = new Persona();
        //Seteo los campos con los datos del inputDto
        persona.setUsuario(personaInputDto.getUsuario());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.getActive());
        persona.setImagenUrl(personaInputDto.getImagenUrl());
        persona.setTerminationDate(personaInputDto.getTerminationDate());


        //Creo una lista de personas con todas las que hay en el repository
        //Con el bucle for valido si ya esxisten los campos unique.
        for (Persona p : personaRepository.findAll()) {
            if (p.getUsuario().equals(persona.getUsuario())) {
                throw new UnprocessableEntityException("Usuario ya registrado");
            }
            if (p.getCompanyEmail().equals(persona.getCompanyEmail())) {
                throw new UnprocessableEntityException("CompanyEmail ya registrado");
            }
            if (p.getPersonalEmail().equals(persona.getPersonalEmail())) {
                throw new UnprocessableEntityException("PersonalEmail ya registrado");
            }
            if (p.getImagenUrl().equals(persona.getImagenUrl())) {
                throw new UnprocessableEntityException("ImagenUrl ya registrada");
            }
        }

        //Una vez comprobado que cumple los requisitos.
        // Se guarda en el repositorio.
        personaRepository.save(persona);

        //Retorna el outPut
        return new PersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaById(Integer id) {
        //Instancio persona dentro de un try .orElseThrow(lanza una excepción)
        //Que será controlada en el catch.
        Persona persona = personaRepository.findById(id).orElseThrow
                (() -> new EntityNotFoundException("Usuario no encontrado."));

        return new PersonaOutputDto(persona);
    }


    @Override
    public List<PersonaOutputDto> getPersonasByName(String name) {
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
        for (Persona p : personaRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."))) {
            personaOutputDtoList.add(new PersonaOutputDto(p));
        }
        return personaOutputDtoList;
    }


    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();

        for (Persona p : personaRepository.findAll()) {
            personaOutputDtoList.add(new PersonaOutputDto(p));
        }
        return personaOutputDtoList;

//-------------------------------------Opción 2----------------------------------------
//    @Override
//    public List<PersonaOutputDto> getAllPersonas() {
//        List<PersonaOutputDto> personaOutputDtoList= new ArrayList<>();
//
////        personaRepository.findAll().forEach(persona -> {
////            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
////            personaOutputDtoList.add(personaOutputDto);


//-------------------------------------Opción 3----------------------------------------
//    List<Persona> personas = personaRepository.findAll();
//        return personas.stream().map(PersonaOutputDto::new).toList();
//-------------------------------------------------------------------------------------
    }


    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) {

        //Creo una persona que será igual a la que ya hay en el repositorio.
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));

        //----------------------------------------------------------------------------

        //Setteo los diferentes campos de esa persona desde el input.
        //Los que no se setean siguen igual... con lo que el id y fechaCreación seguirían igual.
        //Si pasamos un json con menos campos esos campos, en el input van a nulo, y saltaría la excepción.
        //Con estos if si van a nulos no settean, dejando los valores anteriores que ya están controlados en el add.
        //----------------------------------------------------------------------------
        if (personaInputDto.getUsuario() != null) persona.setUsuario(personaInputDto.getUsuario());
        if (personaInputDto.getPassword() != null) persona.setPassword(personaInputDto.getPassword());
        if (personaInputDto.getName() != null) persona.setName(personaInputDto.getName());
        if (personaInputDto.getSurname() != null) persona.setSurname(personaInputDto.getSurname());
        if (personaInputDto.getCompanyEmail() != null)
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        if (personaInputDto.getPersonalEmail() != null)
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        if (personaInputDto.getCity() != null) persona.setCity(personaInputDto.getCity());
        if (personaInputDto.getActive() != null) persona.setActive(personaInputDto.getActive());
        if (personaInputDto.getImagenUrl() != null) persona.setImagenUrl(personaInputDto.getImagenUrl());
        if (personaInputDto.getTerminationDate() != null)
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        //----------------------------------------------------------------------------

        //Guardo los cambios
        personaRepository.save(persona);


        return new PersonaOutputDto(persona);
    }


    @Override
    public String deletePersona(Integer id) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        personaRepository.delete(persona);
        return "La persona con id " + id + " ha sido borrad@.";
    }


    //----------------------------CRITERIA BUILDER-----------------------------------------------------------
    @PersistenceContext//Anotación específica para inyectar EntityManager (Tipo Autowired)
    private EntityManager entityManager;//Interfaz con métodos para realizar operaciones CRUD

    @Override
    public List<PersonaOutputDto> getPersonas(String usuario, String name, String surname, Date createdDate, String dateCondition, String orderBy, Integer page, Integer size) {

        //-------------------------------ELEMENTOS NECESARIOS PARA LA CONSULTA------------------------------------------

        //CriteriaBuilder se utiliza para construir objetos de consulta.
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        //CriteriaQuery representa la consulta completa y se utiliza para realizar la ejecución real de la consulta.
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);

        Root<Persona> root = query.from(Persona.class);//Root representa la entidad que será consultada.
        //------------------------------------------------------------------------------------------------------


        //----------------------------------Gestión de predicados-----------------------------------------------
        //Predicados son las condiciones... WHERE ......
        List<Predicate> predicados = new ArrayList<>();
        Predicate predicado;

        // Agregamos los predicados según los parámetros de búsqueda
        if (usuario != null) {
            //predicado= cb.like(root.get("usuario"), "%" + usuario + "%");
            //predicado = cb.like(cb.lower(root.get("usuario")), "%" + usuario.toLowerCase() + "%"); //Línea con mas filtrados por like
            predicado = cb.equal(root.get("usuario"), usuario);
            predicados.add(predicado);
        }
        if (name != null) {
            predicado = cb.equal(root.get("name"), name);
            predicados.add(predicado);
        }
        if (surname != null) {
            predicado = cb.equal(root.get("surname"), surname);
            predicados.add(predicado);
        }
        if (createdDate != null) {
            if (dateCondition != null && dateCondition.equals("superior")) {
                predicado = cb.greaterThan(root.get("createdDate"), createdDate);
                predicados.add(predicado);
            } else if (dateCondition != null && dateCondition.equals("inferior")) {
                predicado = cb.lessThan(root.get("createdDate"), createdDate);
                predicados.add(predicado);
            } else {
                predicado = cb.equal(root.get("createdDate"), createdDate);
                predicados.add(predicado);
            }
        }


        // Ordenamos el resultado tanto si se especifica como sino
        Order order;
        if (orderBy != null) {
            order = cb.asc(root.get(orderBy));
        } else {
            // Si orderBy es nulo, establecer un campo de ordenamiento predeterminado
            order = cb.asc(root.get("idPersona"));
        }

        query.orderBy(order);//Nos aseguramos de si no hay predicados exista el orden indicado


        //Si la lista de Predicados no está vacía se añade a la query en forma de array.
        if (!predicados.isEmpty()) {
            query.where(predicados.toArray(new Predicate[predicados.size()])).orderBy(order);
        }//Si estuviera vacía...no se añadiría ningún predicado pero quedaría el posible orden.


        //----------------------------------------------------------------------------------------------------

        //Con la query completa se construye una lista de Personas
        List<Persona> personas = entityManager.createQuery(query).getResultList();


        //-----------------------------Paginación + Size-------------------------------------------------------
        // Creamos un PagedListHolder con la lista de personas y establecemos el tamaño de página
        PagedListHolder<Persona> pagedListHolder = new PagedListHolder<>(personas);

        //Y ahora le damos forma a nuetra paginación
        pagedListHolder.setPage(page);//Set del page(Required= default true)
        //pagedListHolder.setPage(page != null ? page : 0);//Si quisiéramos poder poner la page a null...habría que cambiar el controller tambiénn
        pagedListHolder.setPageSize(size != null ? size : 10);


        //Obtenemos la lista de elementos de la página actual
        List<Persona> personasPaginadas = pagedListHolder.getPageList();


        //Y con la lista de personaspaginadas se contruye la lista de PersonaOutPutDto
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
        personasPaginadas.forEach(persona -> {
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            personaOutputDtoList.add(personaOutputDto);
        });


        return personaOutputDtoList;
    }
}








