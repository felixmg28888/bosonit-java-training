package com.bosonit.formacion.block1201criteriabuilder.inicio;

import com.bosonit.formacion.block1201criteriabuilder.persona.domain.Persona;
import com.bosonit.formacion.block1201criteriabuilder.persona.repository.PersonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
public class ComponenteInicio {
    @Autowired
    PersonaRepository personaRepository;

    @PostConstruct
    public void cargaDatos() throws ParseException {
        Random random = new Random();

        for (int i = 1; i < 22; i++) {
            int year = random.nextInt(24) + 2000;
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(30) + 1;
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date fechaAleatoria = calendar.getTime();


            String variable = String.format("Person_%03d",i);

            Persona persona = new Persona();
            persona.setUsuario(variable);
            persona.setPassword("Pass" + variable);
            persona.setName(variable);
            persona.setSurname(variable);
            persona.setCompanyEmail(variable + "@example.com");
            persona.setPersonalEmail(variable + "@gmail.com");
            persona.setCity("Ciudad" + variable);
            persona.setActive(true);
            persona.setImagenUrl("http://example.com/images/" + variable + ".jpg");
            persona.setCreatedDate(fechaAleatoria);
            personaRepository.save(persona);

        }


//        Persona persona1 = new Persona();
//        persona1.setUsuario("AFicticia1");
//        persona1.setPassword("PassFicticia1");
//        persona1.setName("BFicticia1");
//        persona1.setSurname("CFicticia1 Ficticia1");
//        persona1.setCompanyEmail("Ficticia1@example.com");
//        persona1.setPersonalEmail("Ficticia1@gmail.com");
//        persona1.setCity("Ciudad Ficticia1");
//        persona1.setActive(true);
//        persona1.setImagenUrl("http://example.com/images/Ficticia1.jpg");
//        date=dateFormat.parse("23-11-2015");
//        persona1.setCreatedDate(date);
//        personaRepository.save(persona1);
//
//
//        Persona persona2 = new Persona();
//        persona2.setUsuario("FJicticia2");
//        persona2.setPassword("PassFicticia2");
//        persona2.setName("KFicticia2");
//        persona2.setSurname("RFicticia2 Ficticia2");
//        persona2.setCompanyEmail("Ficticia2@example.com");
//        persona2.setPersonalEmail("Ficticia2@gmail.com");
//        persona2.setCity("Ciudad Ficticia2");
//        persona2.setActive(true);
//        persona2.setImagenUrl("http://example.com/images/Ficticia2.jpg");
//        date = dateFormat.parse("23-11-2018");
//        persona2.setCreatedDate(date);
//        personaRepository.save(persona2);
//
//
//        Persona persona3 = new Persona();
//        persona3.setUsuario("SFicticia3");
//        persona3.setPassword("PassFicticia3");
//        persona3.setName("YFicticia3");
//        persona3.setSurname("ZFicticia3 Ficticia3");
//        persona3.setCompanyEmail("Ficticia3@example.com");
//        persona3.setPersonalEmail("Ficticia3@gmail.com");
//        persona3.setCity("Ciudad Ficticia3");
//        persona3.setActive(true);
//        persona3.setImagenUrl("https//example.com/images/Ficticia3.jpg");
//        date = dateFormat.parse("23-11-2020");
//        persona3.setCreatedDate(date);
//        personaRepository.save(persona3);
//
//
//        Persona persona4 = new Persona();
//        persona4.setUsuario("ZFicticia4");
//        persona4.setPassword("PassFicticia4");
//        persona4.setName("XFicticia4");
//        persona4.setSurname("YFicticia4 Ficticia4");
//        persona4.setCompanyEmail("Ficticia4@example.com");
//        persona4.setPersonalEmail("Ficticia4@gmail.com");
//        persona4.setCity("Ciudad Ficticia4");
//        persona4.setActive(true);
//        persona4.setImagenUrl("http://example.com/images/Ficticia4.jpg");
//        date = dateFormat.parse("23-11-2022");
//        persona4.setCreatedDate(date);
//        personaRepository.save(persona4);
//
//
//        Persona persona5 = new Persona();
//        persona5.setUsuario("IFicticia5");
//        persona5.setPassword("PassFicticia5");
//        persona5.setName("OFicticia5");
//        persona5.setSurname("PFicticia5 Ficticia5");
//        persona5.setCompanyEmail("Ficticia5@example.com");
//        persona5.setPersonalEmail("Ficticia5@gmail.com");
//        persona5.setCity("Ciudad Ficticia5");
//        persona5.setActive(true);
//        persona5.setImagenUrl("http://example.com/images/Ficticia5.jpg");
//        date = dateFormat.parse("15-02-2019");
//        persona5.setCreatedDate(date);
//        personaRepository.save(persona5);
//
//
//        Persona persona6 = new Persona();
//        persona6.setUsuario("BFicticia6");
//        persona6.setPassword("PassFicticia6");
//        persona6.setName("UFicticia6");
//        persona6.setSurname("OFicticia6 Ficticia6");
//        persona6.setCompanyEmail("Ficticia6@example.com");
//        persona6.setPersonalEmail("Ficticia6@gmail.com");
//        persona6.setCity("Ciudad Ficticia6");
//        persona6.setActive(true);
//        persona6.setImagenUrl("http://example.com/images/Ficticia6.jpg");
//        date = dateFormat.parse("13-05-2020");
//        persona6.setCreatedDate(date);
//        personaRepository.save(persona6);
//
//
//        Persona persona7 = new Persona();
//        persona7.setUsuario("AFicticia7");
//        persona7.setPassword("PassFicticia7");
//        persona7.setName("BFicticia7");
//        persona7.setSurname("CCFicticia7 Ficticia7");
//        persona7.setCompanyEmail("Ficticia7@example.com");
//        persona7.setPersonalEmail("Ficticia7@gmail.com");
//        persona7.setCity("Ciudad Ficticia7");
//        persona7.setActive(true);
//        persona7.setImagenUrl("http://example.com/images/Ficticia7.jpg");
//        date = dateFormat.parse("22-09-2019");
//        persona7.setCreatedDate(date);
//        personaRepository.save(persona7);
//
//
//        Persona persona8 = new Persona();
//        persona8.setUsuario("AFicticia8");
//        persona8.setPassword("PassFicticia8");
//        persona8.setName("HFicticia8");
//        persona8.setSurname("JFicticia8 Ficticia8");
//        persona8.setCompanyEmail("Ficticia8@example.com");
//        persona8.setPersonalEmail("Ficticia8@gmail.com");
//        persona8.setCity("Ciudad Ficticia8");
//        persona8.setActive(true);
//        persona8.setImagenUrl("http://example.com/images/Ficticia8.jpg");
//        date = dateFormat.parse("08-07-2021");
//        persona8.setCreatedDate(date);
//        personaRepository.save(persona8);
//
//
//
//
//        Persona persona9 = new Persona();
//        persona9.setUsuario("Kicticia9");
//        persona9.setPassword("PassFicticia9");
//        persona9.setName("LFicticia9");
//        persona9.setSurname("OFicticia9 Ficticia9");
//        persona9.setCompanyEmail("Ficticia9@example.com");
//        persona9.setPersonalEmail("Ficticia9@gmail.com");
//        persona9.setCity("Ciudad Ficticia9");
//        persona9.setActive(true);
//        persona9.setImagenUrl("http://example.com/images/Ficticia9.jpg");
//        date = dateFormat.parse("17-11-2018");
//        persona9.setCreatedDate(date);
//        personaRepository.save(persona9);
//
//
//        Persona persona10 = new Persona();
//        persona10.setUsuario("YFicticia");
//        persona10.setPassword("PassFicticia10");
//        persona10.setName("DFicticia");
//        persona10.setSurname("FFicticia10 Ficticia10");
//        persona10.setCompanyEmail("Ficticia10@example.com");
//        persona10.setPersonalEmail("Ficticia10@gmail.com");
//        persona10.setCity("Ciudad Ficticia10");
//        persona10.setActive(true);
//        persona10.setImagenUrl("http://example.com/images/Ficticia10.jpg");
//        date = dateFormat.parse("09-12-2020");
//        persona10.setCreatedDate(date);
//        personaRepository.save(persona10);
//
//
//        Persona persona11 = new Persona();
//        persona11.setUsuario("VFicticia1");
//        persona11.setPassword("PassFicticia11");
//        persona11.setName("NFicticia11");
//        persona11.setSurname("SFicticia11 Ficticia11");
//        persona11.setCompanyEmail("Ficticia11@example.com");
//        persona11.setPersonalEmail("Ficticia11@gmail.com");
//        persona11.setCity("Ciudad Ficticia11");
//        persona11.setActive(true);
//        persona11.setImagenUrl("http://example.com/images/Ficticia11.jpg");
//        date = dateFormat.parse("23-11-2019");
//        persona11.setCreatedDate(date);
//        personaRepository.save(persona11);
//
//
//        Persona persona12 = new Persona();
//        persona12.setUsuario("RFicticia1");
//        persona12.setPassword("PassFicticia12");
//        persona12.setName("TFicticia12");
//        persona12.setSurname("HFicticia12 Ficticia12");
//        persona12.setCompanyEmail("Ficticia12@example.com");
//        persona12.setPersonalEmail("Ficticia12@gmail.com");
//        persona12.setCity("Ciudad Ficticia12");
//        persona12.setActive(true);
//        persona12.setImagenUrl("http://example.com/images/Ficticia12.jpg");
//        date = dateFormat.parse("12-05-2019");
//        persona12.setCreatedDate(date);
//        personaRepository.save(persona12);
//
//
//        Persona persona13 = new Persona();
//        persona13.setUsuario("QFicticia1");
//        persona13.setPassword("PassFicticia13");
//        persona13.setName("WFicticia13");
//        persona13.setSurname("RFicticia13 Ficticia13");
//        persona13.setCompanyEmail("Ficticia13@example.com");
//        persona13.setPersonalEmail("Ficticia13@gmail.com");
//        persona13.setCity("Ciudad Ficticia13");
//        persona13.setActive(true);
//        persona13.setImagenUrl("http://example.com/images/Ficticia13.jpg");
//        date = dateFormat.parse("15-02-2016");
//        persona13.setCreatedDate(date);
//        personaRepository.save(persona13);
//
//        Persona persona14 = new Persona();
//        persona14.setUsuario("SFicticia5");
//        persona14.setPassword("PassFicticia14");
//        persona14.setName("XFicticia14");
//        persona14.setSurname("VFicticia14 Ficticia14");
//        persona14.setCompanyEmail("Ficticia14@example.com");
//        persona14.setPersonalEmail("Ficticia14@gmail.com");
//        persona14.setCity("Ciudad Ficticia14");
//        persona14.setActive(true);
//        persona14.setImagenUrl("http://example.com/images/Ficticia14.jpg");
//        date = dateFormat.parse("11-06-2017");
//        persona14.setCreatedDate(date);
//        personaRepository.save(persona14);
//
//        Persona persona15 = new Persona();
//        persona15.setUsuario("BFicticia5");
//        persona15.setPassword("PassFicticia15");
//        persona15.setName("NFicticia15");
//        persona15.setSurname("MFicticia15 Ficticia15");
//        persona15.setCompanyEmail("Ficticia15@example.com");
//        persona15.setPersonalEmail("Ficticia15@gmail.com");
//        persona15.setCity("Ciudad Ficticia15");
//        persona15.setActive(true);
//        persona15.setImagenUrl("http://example.com/images/Ficticia15.jpg");
//        date = dateFormat.parse("24-09-2018");
//        persona15.setCreatedDate(date);
//        personaRepository.save(persona15);
//
//        Persona persona16 = new Persona();
//        persona16.setUsuario("MFicticia6");
//        persona16.setPassword("PassFicticia16");
//        persona16.setName("BFicticia16");
//        persona16.setSurname("TFicticia16 Ficticia16");
//        persona16.setCompanyEmail("Ficticia16@example.com");
//        persona16.setPersonalEmail("Ficticia16@gmail.com");
//        persona16.setCity("Ciudad Ficticia16");
//        persona16.setActive(true);
//        persona16.setImagenUrl("http://example.com/images/Ficticia16.jpg");
//        date = dateFormat.parse("08-12-2019");
//        persona16.setCreatedDate(date);
//        personaRepository.save(persona16);
//
//        Persona persona17 = new Persona();
//        persona17.setUsuario("RFicticia7");
//        persona17.setPassword("PassFicticia17");
//        persona17.setName("YFicticia17");
//        persona17.setSurname("UFicticia17 Ficticia17");
//        persona17.setCompanyEmail("Ficticia17@example.com");
//        persona17.setPersonalEmail("Ficticia17@gmail.com");
//        persona17.setCity("Ciudad Ficticia17");
//        persona17.setActive(true);
//        persona17.setImagenUrl("http://example.com/images/Ficticia17.jpg");
//        date = dateFormat.parse("22-05-2020");
//        persona17.setCreatedDate(date);
//        personaRepository.save(persona17);
//
//
//        Persona persona18 = new Persona();
//        persona18.setUsuario("IFicticia8");
//        persona18.setPassword("PassFicticia18");
//        persona18.setName("OFicticia18");
//        persona18.setSurname("PFicticia18 Ficticia18");
//        persona18.setCompanyEmail("Ficticia18@example.com");
//        persona18.setPersonalEmail("Ficticia18@gmail.com");
//        persona18.setCity("Ciudad Ficticia18");
//        persona18.setActive(true);
//        persona18.setImagenUrl("http://example.com/images/Ficticia18.jpg");
//        date = dateFormat.parse("22-07-2018");
//        persona18.setCreatedDate(date);
//        personaRepository.save(persona18);
//
//
//        Persona persona19 = new Persona();
//        persona19.setUsuario("LFicticia9");
//        persona19.setPassword("PassFicticia19");
//        persona19.setName("LFicticia19");
//        persona19.setSurname("RFicticia19 Ficticia19");
//        persona19.setCompanyEmail("Ficticia19@example.com");
//        persona19.setPersonalEmail("Ficticia19@gmail.com");
//        persona19.setCity("Ciudad Ficticia19");
//        persona19.setActive(true);
//        persona19.setImagenUrl("http://example.com/images/Ficticia19.jpg");
//        date = dateFormat.parse("12-07-2018");
//        persona19.setCreatedDate(date);
//        personaRepository.save(persona19);
//
//
//        Persona persona20 = new Persona();
//        persona20.setUsuario("KFicticia");
//        persona20.setPassword("PassFicticia20");
//        persona20.setName("SFicticia20");
//        persona20.setSurname("YFicticia20 Ficticia20");
//        persona20.setCompanyEmail("Ficticia20@example.com");
//        persona20.setPersonalEmail("Ficticia20@gmail.com");
//        persona20.setCity("Ciudad Ficticia20");
//        persona20.setActive(true);
//        persona20.setImagenUrl("http://example.com/images/Ficticia20.jpg");
//        date = dateFormat.parse("12-09-2018");
//        persona20.setCreatedDate(date);
//        personaRepository.save(persona20);
//
//
//        Persona persona21 = new Persona();
//        persona21.setUsuario("YFicticia9");
//        persona21.setPassword("PassFicticia21");
//        persona21.setName("YFicticia21");
//        persona21.setSurname("TFicticia21 Ficticia21");
//        persona21.setCompanyEmail("Ficticia21@example.com");
//        persona21.setPersonalEmail("Ficticia21@gmail.com");
//        persona21.setCity("Ciudad Ficticia21");
//        persona21.setActive(true);
//        persona21.setImagenUrl("http://example.com/images/Ficticia21.jpg");
//        date = dateFormat.parse("23-11-2022");
//        persona21.setCreatedDate(date);
//        personaRepository.save(persona21);

    }

}
