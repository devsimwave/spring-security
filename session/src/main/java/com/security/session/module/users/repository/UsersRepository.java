package com.security.session.module.users.repository;

import com.security.session.module.users.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //TODO: @EntityGraph : Data JPA에서 fect 조인을 어노테이션으로 사용할 수 있도록 만들어 준 기능이다. 이거 사용 안하면 LazyInitializationException 뜸 이건 나중에 봐야함
    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findByEmail(String username);
}
