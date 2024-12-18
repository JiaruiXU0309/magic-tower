package com.nuist.test.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "player")
public class PlayerTable {
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	private Integer pid;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@ManyToMany
	@JoinTable(name = "player_world", 
			joinColumns = @JoinColumn(name = "pid"), 
			inverseJoinColumns = @JoinColumn(name = "wid")
	)
	@JsonBackReference
	private Set<WorldTable> worlds = new HashSet<>();


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Set<WorldTable> getWorlds() {
		return worlds;
	}

	public void setWorlds(Set<WorldTable> worlds) {
		this.worlds = worlds;
	}
}
