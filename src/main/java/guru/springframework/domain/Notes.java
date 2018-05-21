package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by piyush.b.kumar on May 18, 2018.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private String recipeNotes;	
	@OneToOne
	private Recipe recipe;

}
