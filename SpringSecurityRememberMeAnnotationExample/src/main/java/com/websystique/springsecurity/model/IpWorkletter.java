package com.websystique.springsecurity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="ip_workletter")
public class IpWorkletter {

  @Id
  @GenericGenerator(name="systemUUID",strategy="uuid")
  @GeneratedValue(generator="systemUUID")
  @Column(name = "id", insertable = true, updatable = true, nullable = false)
  private String id;

  @Column(name="server_name")
  private String serverName;

  @Column(name="link")
  private String link;

  @Column(name="ip")
  private String ip;

  @Column(name="port")
  private String port;

  @Column(name="username")
  private String username;

  @Column(name="password")
  private String password;

}
