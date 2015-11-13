

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import br.com.pizzaservice.dao.entity.Pizza;
import br.com.pizzaservice.dao.repository.PizzaRepository;
import br.com.pizzaservice.dao.repository.SaborPizzaRepository;
//import da biblioteca jersey
//import da biblioteca jersey 
//import da biblioteca jersey

public class PizzaDeliveryServiceTest {
	@Mock
	private SaborPizzaRepository saborPizzaRepository;
	@Mock
	private PizzaRepository pizzaRepository;	
	

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void testFindSabores(){		
		Assert.assertNotNull(saborPizzaRepository.findAll());
	}
	
	@Test
	public void testFindPizzas(){		
		Assert.assertNotNull(pizzaRepository.findAll());
	}
	
	

}