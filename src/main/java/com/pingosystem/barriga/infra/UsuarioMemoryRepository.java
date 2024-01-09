package com.pingosystem.barriga.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

public class UsuarioMemoryRepository implements UsuarioRepository {
	
	private List<Usuario> users;
	private Long currentId;
	
	

	public UsuarioMemoryRepository() {
		currentId = 0L;
		users = new ArrayList<>();
		
		salvar(new Usuario(null, "User #1", "user1@mail.com", "123456"));
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		Usuario newUser = new Usuario(nextId(), usuario.nome(), usuario.email(), usuario.senha());
		users.add(newUser);
		return newUser;
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		return users.stream()
				.filter(user -> user.email().equalsIgnoreCase(email))
				.findFirst();
	}
	
	public void printUsers() {
		System.out.println(users);
	}

	private Long nextId() {
		return ++currentId;
	}
	
	public static void main(String[] args) {
		UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
		repo.printUsers();
		repo.salvar(new Usuario(null, "Usuário qualquer", "a@a.com", "aaa"));
		repo.printUsers();
		repo.salvar(new Usuario(null, "User #3", "a@a.com", "aaa"));
		repo.printUsers();
	}
}
