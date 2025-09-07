package entities;

import java.io.Serializable;

public class Client implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long id;
    private String name;
    private String document;
    private String email;

    public Client(){
    }

    public Client(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public Client(Long id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public void displayDetails(){
        System.out.println("*****-------------------------------------------******");
        System.out.println("Client ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Document: " + document);
        System.out.println("Email: " + email);
       System.out.println("*****-------------------------------------------******");
    }
}
