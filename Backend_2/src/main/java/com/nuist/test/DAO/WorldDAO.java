package com.nuist.test.DAO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nuist.test.Entity.PlayerTable;
import com.nuist.test.Entity.WorldTable;
public interface WorldDAO extends JpaRepository<WorldTable, Integer> {
	WorldTable findByWid(Integer wid);
}
