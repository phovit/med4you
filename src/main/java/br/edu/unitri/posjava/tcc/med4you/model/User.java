package br.edu.unitri.posjava.tcc.med4you.model;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;

import javax.persistence.*;

/**
 * Created by edufratari on 18/07/18.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String identity;
    private String birthDate;

    @OneToOne(mappedBy = "user")
    private Address address;

    private String phone;
    private String email;
    private String cellPhone;
    private String username;
    private String password;

    @ManyToOne
    private User responsableUser;

    public User() {
        super();
    }

    public User getResponsableUser() {
        return responsableUser;
    }

    public void setResponsableUser(User responsableUser) {
        this.responsableUser = responsableUser;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User fromDTO(UserDTO dto){
        this.setAddress(dto.getAddress());
        this.setBirthDate(dto.getBirthDate());
        this.setCellPhone(dto.getCellPhone());
        this.setCpf(dto.getCpf());
        this.setEmail(dto.getEmail());
        this.setIdentity(dto.getIdentity());
        this.setName(dto.getName());
        this.setUsername(dto.getUsername());
        this.setPhone(dto.getPhone());
        return this;
    }
    public UserDTO toDTO(){
        UserDTO dto = new UserDTO();

        dto.setAddress(this.getAddress());
        dto.setBirthDate(this.getBirthDate());
        dto.setCellPhone(this.getCellPhone());
        dto.setCpf(this.getCpf());
        dto.setEmail(this.getEmail());
        dto.setIdentity(this.getIdentity());
        dto.setName(this.getName());
        dto.setUsername(this.getUsername());
        dto.setPhone(this.getPhone());

        return dto;
    }
}
