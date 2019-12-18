package com.jbuelow.injurycounter.data.entity;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class ClientInfo implements Serializable {

  private final static long serialVersionUID = -8924020007103032582L;

  private String method;

  private String url;

  private String host;

  private String protocol;

  private Map<String, String> headers = new HashMap<>();

  private Map<String, String[]> parameters;

  public ClientInfo(HttpServletRequest request) {
    this.method = request.getMethod();
    this.url = String.valueOf(request.getRequestURL());
    this.host = request.getRemoteHost();
    this.protocol = request.getProtocol();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String element = headerNames.nextElement();
      this.headers.put(element, request.getHeader(element));
    }
    this.parameters = request.getParameterMap();
  }
}
