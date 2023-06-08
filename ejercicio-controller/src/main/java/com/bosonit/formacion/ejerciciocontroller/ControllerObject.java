package com.bosonit.formacion.ejerciciocontroller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ControllerObject {
    private List<String> paths;
    private List<Map<String, String>> querys;
    private List<Map<String, String>> headers;
    private String urlOrigen;

    public ControllerObject(String path, Map<String, String> queryParams, Map<String, String> headers,HttpServletRequest request) {
        // Set paths
        String[] pathSegments = path.split("/");
        List<String> paths = new ArrayList<>();
        for (String segment : pathSegments) {
            if (!segment.isEmpty()) {
                paths.add(segment);
            }
        }
        this.paths = paths;

        // Set query parameters
        List<Map<String, String>> queryParameters = new ArrayList<>();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            Map<String, String> parameter = new HashMap<>();
            parameter.put("name", name);
            parameter.put("value", value);
            queryParameters.add(parameter);
        }
        this.querys = queryParameters;

        // Set headers
        List<Map<String, String>> requestHeaders = new ArrayList<>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            Map<String, String> header = new HashMap<>();
            header.put("name", name);
            header.put("value", value);
            requestHeaders.add(header);
        }
        this.headers = requestHeaders;

        // Set the origin URL
        this.urlOrigen = request.getRemoteAddr();
    }
}