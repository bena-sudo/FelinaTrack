package com.pfc.felinatrack_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.model.dto.LoginUser;
import com.pfc.felinatrack_back.model.dto.Message;
import com.pfc.felinatrack_back.model.enums.RoleName;
import com.pfc.felinatrack_back.security.dto.JwtDto;
import com.pfc.felinatrack_back.security.dto.NewUser;
import com.pfc.felinatrack_back.security.service.JwtService;
import com.pfc.felinatrack_back.security.service.UsuarioService;
import com.pfc.felinatrack_back.src.RoleService;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RoleService rolService;

    @Autowired
    JwtService jwtProvider;

    
    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Datos incorrectos o email inválido"));
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("El nickname del usuario ya existe"));
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("El email del usuario ya existe"));
        UserDb usuarioDb =
                new UserDb(nuevoUsuario.getName(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getPhone());
        Set<RoleDb> rolesDb = new HashSet<>();
        rolesDb.add(rolService.getByRoleName(RoleName.ROL_USUARIO).get());
        if(nuevoUsuario.getRoles().contains("ROL_ADMINISTRADOR"))
            rolesDb.add(rolService.getByRoleName(RoleName.ROL_ADMINISTRADOR).get());
        usuarioDb.setRoles(rolesDb);
        usuarioService.save(usuarioDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Message("Usuario creado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUser loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Datos incorrectos"));
        Authentication authentication =
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }
}


