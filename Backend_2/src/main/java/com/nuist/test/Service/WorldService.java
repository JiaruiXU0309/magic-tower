package com.nuist.test.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuist.test.DAO.WorldDAO;
import com.nuist.test.Entity.PlayerTable;
import com.nuist.test.Entity.WorldTable;
@Service
public class WorldService {
	@Autowired
	private WorldDAO worldDAO;
	public Set<PlayerTable> allPlayers(Integer wid) {
		WorldTable worldTable=worldDAO.findByWid(wid);
		return worldTable.getPlayers();
	}
}
