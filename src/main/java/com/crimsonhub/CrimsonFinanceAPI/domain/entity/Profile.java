package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profile")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 12)
    private String password;

    @Column(name = "role", nullable = false)
    private RoleType roleType = RoleType.USER;

    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    @Column(name = "preferred_name", length = 60)
    private String preferredName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "nationality", length = 12)
    private String nationality;

    @Column(name = "identification_number", unique = true, nullable = false, length = 14)
    private String identificationNumber;

    @Column(name = "cep", length = 8)
    private String cep;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roleType == RoleType.ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("MOD"),
                    new SimpleGrantedAuthority("USER"));
        else if (this.roleType == RoleType.MOD)
            return List.of(
                    new SimpleGrantedAuthority("MOD"),
                    new SimpleGrantedAuthority("USER"));
        else
            return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
