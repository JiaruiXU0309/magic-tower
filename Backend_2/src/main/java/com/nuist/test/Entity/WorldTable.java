package com.nuist.test.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "world")
public class WorldTable {
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	private Integer wid;
	@Column(name = "worldname")
	private String worldname;
	@ManyToMany
	@JoinTable(name = "player_world",
			joinColumns = @JoinColumn(name = "wid"), 
			inverseJoinColumns = @JoinColumn(name = "pid")
	)
	@JsonBackReference
	private Set<PlayerTable> players = new HashSet<>();

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWorldname() {
		return worldname;
	}

	public void setWorldname(String worldname) {
		this.worldname = worldname;
	}

	public Set<PlayerTable> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerTable> players) {
		this.players = players;
	}
}