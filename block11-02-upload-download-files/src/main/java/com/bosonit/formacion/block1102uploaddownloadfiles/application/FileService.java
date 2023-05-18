package com.bosonit.formacion.block1102uploaddownloadfiles.application;

import com.bosonit.formacion.block1102uploaddownloadfiles.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    String upload(MultipartFile multipartFile, String type) throws IOException;

    List<File> showAllFiles();

    Object showContentFileById(Integer id);

    Object showContentFileByName(String name);

    String fileDownloadById(Integer id) throws IOException;

    String fileDownloadByName(String name) throws IOException;
}
