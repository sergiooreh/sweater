package org.example.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Table
@Entity(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;

  //  @Transient                                                                          // indicate that a field is not to be persisted or ignore fields to save in the database
   // private String password2;                                                           //password validation
    private boolean active;

    @Email(message = "Email is not correct")                                            //Validation
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private String activationCode;


    //LAZY - подгрузит когда User обратится к полю(хорошо когда много данных) EAGER - при запросе User будет подгружать(хорошо когда мало данных)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)                //формирует новую таблицу для хранения enum. Fetch - как данные будут подгружатся относительно основной сущности
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))   //создаёт отдельную таблицу для даного поля(Role),для которой мы не описывали mapping
    @Enumerated(EnumType.STRING)                                                        //Enum хранить в виде String
    private Set<Role> roles;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, fetch = FetchType.LAZY)          //обратная сторона связи @ManyToOne в Messages. Будем получать все сообщения созданные userom
    private Set<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isAdmin(){ return roles.contains(Role.ADMIN); }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
