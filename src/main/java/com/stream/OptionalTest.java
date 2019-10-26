package com.stream;

import java.util.Optional;

import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class OptionalTest {

	/**
	 * User如果不为null ： orElseGet() 方法不创建 User 对象
	 */
	@Test
	public void givenPresentValue_whenCompare_thenOk() {
//	    User user = new User("john@gmail.com", "1234");
	    User user = null;
	    System.out.println("Using orElse");
	    User result = Optional.ofNullable(user).orElse(createNewUser());
	    System.out.println("Using orElseGet");
	    User result2 = Optional.ofNullable(user).orElseGet(this::createNewUser);
	    
	    System.out.println("result: " + result);
	    System.out.println("result2: " + result2);
	}
	
	private User createNewUser() {
	    System.out.println("Creating New User");
	    return new User("extra@gmail.com", "1234");
	}
	
	/**
	 * 可以决定抛出什么样的异常，而不总是抛出 NullPointerException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void whenThrowException_thenOk() {
		User user = null;
	    User result = Optional.ofNullable(user).orElseThrow(IllegalArgumentException::new);
	    System.out.println(result);
	}
	
	@Test
	public void whenMap_thenOk() {
//	    User user = new User("anna@gmail.com", "1234");
	    User user = null;
	    String email = Optional.ofNullable(user)
	      .map(User::getMail).orElse("default@gmail.com");
	    System.out.println(email);
//	    System.out.println(user.getMail() + "=" + email);
	}
}

class User {
	private String mail;
	private String id;
	public User(String mail, String id) {
		super();
		this.mail = mail;
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User {mail=" + mail + ", id=" + id + "}";
	}
	
}
