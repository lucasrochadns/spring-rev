package br.com.teste.capitulo.service;

import br.com.teste.capitulo.domain.User;
import br.com.teste.capitulo.repository.UserRepository;
import br.com.teste.capitulo.service.exceptions.DBException;
import br.com.teste.capitulo.service.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Page<User> findAll(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public User findById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User update(Long id, User user){
        try{
            User user01 = repository.getReferenceById(id);
            user01.setFirstName(user.getFirstName()); user01.setLastName(user.getLastName());
            user01.setEmail(user.getEmail());
            return repository.save(user01);
        }catch(Exception message){
            throw new DBException("Data base error");
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id){
        try{
            repository.deleteById(id);
        }catch(Exception message){
            throw new DBException("Data Error Delete By ID");
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if(user == null){
            logger.error("User not Found " + username);
            throw new UsernameNotFoundException("User not Found");
        }
        logger.info("User Found");
        return user;
    }
}
