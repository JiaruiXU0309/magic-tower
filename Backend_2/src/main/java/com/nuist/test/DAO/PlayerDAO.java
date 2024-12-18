package com.nuist.test.DAO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nuist.test.Entity.PlayerTable;
import com.nuist.test.Entity.WorldTable;
public interface PlayerDAO extends JpaRepository<PlayerTable, Integer> {
	PlayerTable findByPid(Integer pid);
	PlayerTable findByUsernameAndPassword(String username,String password);
}

