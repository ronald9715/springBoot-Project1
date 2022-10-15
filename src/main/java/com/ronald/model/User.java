package com.ronald.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_data")
@Entity
public class User {
    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;
    @ManyToOne //Muchos Usuarios tienen un Role (Foreign Key)
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "FK_User_Role"))
    private Role role;
    @Column(length = 50, nullable = false)
    private String userName;
    @Column(length = 60, nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;
}
