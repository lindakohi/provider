package com.vermeg.amsBack.repositories;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vermeg.amsBack.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
}

