package com.bosonit.formacion.block1102uploaddownloadfiles.repository;

import com.bosonit.formacion.block1102uploaddownloadfiles.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findByName(String name);
}
