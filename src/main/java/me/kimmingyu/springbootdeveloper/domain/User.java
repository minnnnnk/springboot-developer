package me.kimmingyu.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { //UserDetails를 상속받아 인증 객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name = "email" , nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    //사용자 이름
    @Column(name = "nickname" , unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자의 id를 반환(고유한값)
    @Override
    public String getUsername() {
        return email;
    }

    //사용자의 패스워드 반환
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        //만료되었는지 확인하는 로직
        return true; //true -> 만료되지 않았음
    }

    //계졍 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        //계정 잠금되었는지 확인하는 로직
        return true; //true -> 잠금되지 않았음
    }

    //패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        //패스워드 만료되었는지 확인하는 로직
        return true; //true -> 만료되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        //계정 사용 가능 여부 확인하는 로직
        return true; //true -> 사용 가능
    }

    //사용자 이름 변경
    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }
}
