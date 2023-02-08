package com.springboot.crud_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud_springboot.model.Usuario;
import com.springboot.crud_springboot.repository.UserRepository;

@RestController
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
    
    @GetMapping(value = "/userlist")
    @ResponseBody // retorna os dados para o  corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuarios (){
    	
    	List<Usuario> users = userRepository.findAll(); // executa a consulta ao banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK); // retorna um json
    }
    
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario user){
    	
    	Usuario userTest = userRepository.save(user);
    	
    	return new ResponseEntity<Usuario>(userTest, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario user){
    	
    	if(user.getId() == null) {
    		return new ResponseEntity<String>("ID nao informado", HttpStatus.BAD_REQUEST);
    	}
    	Usuario userTest = userRepository.save(user);
    	
    	return new ResponseEntity<Usuario>(userTest, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long id){
    	
    	userRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("Usuario deletado", HttpStatus.OK);
    }
    
    @GetMapping(value ="/userlist/id")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorId(@RequestParam(name = "id") Long id){
    	
    	Usuario userTest = userRepository.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(userTest, HttpStatus.OK);
    }
    
    @GetMapping(value ="/userlist/name")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
    	
    	List<Usuario> userTest = userRepository.getByName(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(userTest, HttpStatus.OK);
    }
    
    
    
}
