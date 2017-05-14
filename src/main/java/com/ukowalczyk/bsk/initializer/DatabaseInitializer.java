package com.ukowalczyk.bsk.initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ukowalczyk.bsk.enums.LabelEnum;
import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Label;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.LabelService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.UserService;

@Component
public class DatabaseInitializer {

	@Autowired
	private LabelService labelService;
	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private RecipieService recipieService;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostConstruct
	public void initializeDatabase() {
		initializeLabels();
		initializeUsers();
		initializeIngredients();
		initializeRecipies();
	}

	private void initializeRecipies() {
		addBrownie();
		addArbuzada();
		addRacuchy();
		addGofry();
	}

	private void addBrownie() {
		Label label = labelService.findByValue(LabelEnum.UNCLASSIFIED);
		Ingredient butter = ingredientService.findByName("masło");
		Ingredient chocolate = ingredientService.findByName("gorzka czekolada");
		Ingredient egg = ingredientService.findByName("jajko");
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient salt = ingredientService.findByName("sól");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(butter, chocolate, egg, flour, sugar, salt));
		String description = "Masło włożyć do rondelka i roztopić. Dodać 2 gorzkie czekolady (200 g) połamane na kosteczki i roztopić, odstawić z ognia. W  misce zmiksować jajka z cukrem. Dodać czekoladę z masłem i zmiksować. Dodać mąkę, sól zmiksować. Wyłożyć do blaszki. Resztę czekolady posiekać i posypać po wierzchu ciasta. Wstawić do piekarnika i piec przez ok. 35 minut";
		Recipie recipie = new Recipie("Brownie", description, allIngredients, label);
		recipieService.save(recipie);
	}

	private void addArbuzada() {
		Label label = labelService.findByValue(LabelEnum.CONFIDENTIAL);
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient water = ingredientService.findByName("woda");
		Ingredient watermelon = ingredientService.findByName("arbuz");
		Ingredient lime = ingredientService.findByName("limonka");
		Ingredient ginger = ingredientService.findByName("imbir");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(sugar, water, watermelon, lime, ginger));
		String description = "Rozpuścić cukier w wodzie, zagotować, ostudzić. Arbuza obrać ze skóry i oczyścić z pestek. Miąższ pokroić w kostkę i włożyć do miski, zmiksować. Imbir zetrzeć na drobnej tarce. Imbir dodać do arbuza i wlać syrop. Wszystko wymieszać, przelać przez sitko do dzbanka. Dodać sok z limonki, wymieszać i wstawić do lodówki do schłodzenia na minimum godzinę.";
		Recipie recipie = new Recipie("Arbuzada", description, allIngredients, label);
		recipieService.save(recipie);
	}
	
	private void addRacuchy() {
		Label label = labelService.findByValue(LabelEnum.SECRET);
		Ingredient yeast = ingredientService.findByName("drożdże");
		Ingredient milk = ingredientService.findByName("mleko");
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient egg = ingredientService.findByName("jajko");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient butter = ingredientService.findByName("masło");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(yeast, milk, flour, egg, sugar, butter));
		String description = "Drożdże rozetrzeć z 1 łyżeczką cukru, następnie rozmieszać z ciepłym mlekiem oraz z kilkoma łyżkami mąki. Zostawić do wyrośnięcia w ciepłem miejscu na około 15 minut. Masło roztopić i ostudzić. Z białek ubić sztywną pianę, dodać resztę cukru i dalej ubijając dodawać kolejno żółtka. Do wyrośniętego rozczynu z drożdży dodać resztę mąki, stopione masło, ubite jaja, jednocześnie wyrabiając ciasto przez około 15 minut. Odstawić do wyrośnięcia na około 20 minut w ciepłe miejsce. Gdy ciasto wyrośnie, uformować małe kulki, w środek włożyć pokrojone jabłka lub 3 wiśnie, zlepić jak pierogi i uformować placuszki. Smażyć na głębokim oleju, na średnim ogniu, po kilka minut z każdej strony, aż nabiorą złotego koloru. Posypać cukrem pudrem.";
		Recipie recipie = new Recipie("Racuchy z jabłkami", description, allIngredients, label);
		recipieService.save(recipie);
	}
	
	private void addGofry() {
		Label label = labelService.findByValue(LabelEnum.TOP_SECRET);
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient yeast = ingredientService.findByName("drożdże");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient soda = ingredientService.findByName("soda oczyszczona");
		Ingredient salt = ingredientService.findByName("sól");
		Ingredient milk = ingredientService.findByName("mleko");
		Ingredient butter = ingredientService.findByName("masło");
		Ingredient egg = ingredientService.findByName("jajko");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(flour, yeast, sugar, soda, salt, milk, butter, egg));
		String description = "Mąkę przesiać do miski. Dodać pozostałe suche składniki (suszone drożdże, cukier, sodę oczyszczoną, sól) i wymieszać. W garnku na małym ogniu lekko (do maks. 40 st. C) podgrzać mleko i mieszając rózgą rozpuścić w nim masło. Dodać jajka i dokładnie wymieszać. Zawartość garnka pomału wlać do miski z suchymi składnikami, jednocześnie miksując do uzyskania jednorodnej masy. Kontynuować miksowanie przez kolejne ok. 2 minuty. Przygotowane ciasto odstawić pod przykryciem na minimum 1 godzinę (maks. 2 godziny) - po tym czasie powinno zwiększyć objętość i mieć charakterystyczne pęcherze na powierzchni. Odstane ciasto wymieszać i wykładać na dobrze rozgrzaną gofrownicę.";
		Recipie recipie = new Recipie("Racuchy z jabłkami", description, allIngredients, label);
		recipieService.save(recipie);
	}

	private void initializeIngredients() {
		Label label = labelService.findByValue(LabelEnum.UNCLASSIFIED);
		ingredientService.save(new Ingredient("cukier", label));
		ingredientService.save(new Ingredient("gorzka czekolada", label));
		ingredientService.save(new Ingredient("jajko", label));
		ingredientService.save(new Ingredient("masło", label));
		ingredientService.save(new Ingredient("mąka", label));
		ingredientService.save(new Ingredient("sól", label));
		ingredientService.save(new Ingredient("woda", label));
		ingredientService.save(new Ingredient("arbuz", label));
		ingredientService.save(new Ingredient("limonka", label));
		ingredientService.save(new Ingredient("imbir", label));
		ingredientService.save(new Ingredient("drożdże", label));
		ingredientService.save(new Ingredient("mleko", label));
		ingredientService.save(new Ingredient("soda oczyszczona", label));
	}

	private void initializeUsers() {
		Label label1 = labelService.findByValue(LabelEnum.UNCLASSIFIED);
		String password1 = passwordEncoder.encode("password1");
		User user1 = new User("username1", password1, label1);
		userService.save(user1);
		Label label2 = labelService.findByValue(LabelEnum.CONFIDENTIAL);
		User user2 = new User("username2", password1, label2);
		userService.save(user2);
		Label label3 = labelService.findByValue(LabelEnum.SECRET);
		User user3 = new User("username3", password1, label3);
		userService.save(user3);
		Label label4 = labelService.findByValue(LabelEnum.TOP_SECRET);
		User user4 = new User("username4", password1, label4);
		userService.save(user4);
	}

	private void initializeLabels() {
		for (LabelEnum label : LabelEnum.values()) {
			labelService.save(label.toString());
		}
	}

}
