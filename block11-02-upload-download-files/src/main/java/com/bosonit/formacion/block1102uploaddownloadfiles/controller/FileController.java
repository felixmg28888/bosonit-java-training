package com.bosonit.formacion.block1102uploaddownloadfiles.controller;

import com.bosonit.formacion.block1102uploaddownloadfiles.application.FileService;
import com.bosonit.formacion.block1102uploaddownloadfiles.domain.File;
import com.bosonit.formacion.block1102uploaddownloadfiles.exception.EntityNotFoundException;
import com.bosonit.formacion.block1102uploaddownloadfiles.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    FileRepository fileRepository;


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String type) throws IOException {//throws Exception eliminado por los ifs de personaServiceImpl
        return ResponseEntity.ok().body((fileService.upload(multipartFile, type)));
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<File>> showAllFiles() {
        return ResponseEntity.ok().body(fileService.showAllFiles());
    }

    @GetMapping("/showById/{id}")
    public ResponseEntity<Object> showContentFileById(@PathVariable Integer id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("File no encontrado."));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(new ByteArrayResource(file.getBytes()));
    }

    @GetMapping("/showByName/{name}")
    public ResponseEntity<Object> showContentFileByName(@PathVariable String name) {
        File file = fileRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("File no encontrado."));
        ;
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(new ByteArrayResource(file.getBytes()));
    }

    @GetMapping("/downloadById/{id}")
    public ResponseEntity<String> downloadById(@PathVariable Integer id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, fileRepository.findById(id).orElseThrow().getType());
        return new ResponseEntity<>(fileService.fileDownloadById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/downloadByName/{name}")
    public ResponseEntity<String> downloadByName(@PathVariable String name) throws IOException {
        return ResponseEntity.ok().body(fileService.fileDownloadByName(name));
    }
}
