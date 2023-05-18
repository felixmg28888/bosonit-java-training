package com.bosonit.formacion.block1102uploaddownloadfiles.application;

import com.bosonit.formacion.block1102uploaddownloadfiles.domain.File;
import com.bosonit.formacion.block1102uploaddownloadfiles.exception.EntityNotFoundException;
import com.bosonit.formacion.block1102uploaddownloadfiles.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1102uploaddownloadfiles.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public String upload(MultipartFile multipartFile, String type) throws IOException {

        if (multipartFile.isEmpty()) throw new UnprocessableEntityException("El archivo a subir está vacío");

        //Construyo un file vacío
        File file = new File();

        //Seteo las variables excepto el id y createDate
        //Por parametro le paso un objeto multipartFile y extraigo los datos con sus métodos.
        file.setName(multipartFile.getOriginalFilename());
        file.setBytes(multipartFile.getBytes());
        file.setType(multipartFile.getContentType());

        fileRepository.save(file);

        return "File subido con éxito.";
    }

    @Override
    public List<File> showAllFiles() {
        //Creo una lista con todos los File que hay en la base de datos.

        List<File> allFilesB11List = fileRepository.findAll();

        //Si hago un showAll mandaría el archivo tal cual con toda la lína de bytes(muy  larga)
        //Con este método se acorta la lína de byte de los file de la lista para mejorar la legibilidad.
        allFilesB11List.forEach(file -> {
            String originalField = Arrays.toString(file.getBytes());
            String shortenedField = originalField.substring(0, 10); // Ejemplo para reducir los primeros 10 caracteres del campo
            file.setBytes(shortenedField.getBytes());
        });


        return allFilesB11List;
    }

    @Override
    public Object showContentFileById(Integer id) {
        File file = fileRepository.findById(id).orElseThrow();

        return file.getBytes();
    }

    @Override
    public Object showContentFileByName(String name) {
        File file = fileRepository.findByName(name).orElseThrow();
        return file.getBytes();
    }

    @Override
    public String fileDownloadById(Integer id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Archivo no encontrado."));;

        byte[] fileBytesfile = file.getBytes();
        String fileName = file.getName();

        //Creación de la carpeta Downloads en el caso de no existir.
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        Path path4Dir = Path.of(currentDirectory + Paths.get("/downloads"));
        Files.createDirectories(path4Dir);

        //Copia fichero en la carpeta
        Path path4File =  Paths.get("downloads", fileName);
        Files.write(path4File, fileBytesfile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, file.getType());

        return "Fichero guardado en carpeta downloads";
    }


    @Override
    public String fileDownloadByName(String name) throws IOException {
        File file = fileRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("File no encontrado."));;

        byte[] fileBytes = file.getBytes();
        String fileName = file.getName();

        //Creación de la carpeta Downloads en el caso de no existir.
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        Path path4Dir = Path.of(currentDirectory + Paths.get("/downloads"));
        Files.createDirectories(path4Dir);

        //Copia fichero en la carpeta
        Path path4File =  Paths.get("downloads", fileName);
        Files.write(path4File, fileBytes);

        return "Fichero guardado en carpeta downloads";
    }
}
