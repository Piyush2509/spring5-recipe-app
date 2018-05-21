package guru.springframework.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by piyush.b.kumar on May 18, 2018.
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

}
