package integration;

import com.example.ClassLoadInherite;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClassLoadWatcher {

	private ClassLoadInherite load = new ClassLoadInherite();
	
	@PostConstruct
	public void construct() {
		ClassLoadInherite load = new ClassLoadInherite();
	}
}
