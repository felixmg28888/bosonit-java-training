package com.bosonit.formacion.ejerciciocontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/**")
    public String entryOther() {
        return "He entrado a entryOther";
    }

    @GetMapping(value = {"/", "/one"})
    public String entryOne() {
        return "He entrado a entryOne";
    }

    @GetMapping(value = {"/controllerobject/controllerobject"})
    public ResponseEntity<ControllerObject> controllerobject(@RequestParam Map<String, String> queryParams, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        ControllerObject controllerObject = new ControllerObject(request.getRequestURI(), queryParams, headers, request);
        return ResponseEntity.ok(controllerObject);
    }

    @GetMapping(value = {"/salta"})
    public String entryJump() {
        return "He ido a Jump";
    }
}
