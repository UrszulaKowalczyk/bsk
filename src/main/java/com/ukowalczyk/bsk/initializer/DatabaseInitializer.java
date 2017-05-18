package com.ukowalczyk.bsk.initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ukowalczyk.bsk.model.Ingredient;
import com.ukowalczyk.bsk.model.Recipie;
import com.ukowalczyk.bsk.model.TableLabel;
import com.ukowalczyk.bsk.model.User;
import com.ukowalczyk.bsk.service.IngredientService;
import com.ukowalczyk.bsk.service.RecipieService;
import com.ukowalczyk.bsk.service.TableService;
import com.ukowalczyk.bsk.service.UserService;

@Component
public class DatabaseInitializer {
	@Autowired
	private UserService userService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private RecipieService recipieService;
	@Autowired
	private TableService tableService;

	public static final String TABLE_TABLELABEL = "tablelabel";
	public static final String TABLE_USER = "user";
	public static final String TABLE_RECIPIE = "recipie";
	public static final String TABLE_INGREDIENT = "ingredient";
	public static final String TABLE_RECIPIE_INGREDIENT = "recipie_ingredient";

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostConstruct
	public void initializeDatabase() {
		initializeUsers();
		initializeTables();
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
		Ingredient butter = ingredientService.findByName("masło");
		Ingredient chocolate = ingredientService.findByName("gorzka czekolada");
		Ingredient egg = ingredientService.findByName("jajko");
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient salt = ingredientService.findByName("sól");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(butter, chocolate, egg, flour, sugar, salt));
		String description = "Masło włożyć do rondelka i roztopić. Dodać 2 gorzkie czekolady (200 g) połamane na kosteczki i roztopić, odstawić z ognia. W  misce zmiksować jajka z cukrem. Dodać czekoladę z masłem i zmiksować. Dodać mąkę, sól zmiksować. Wyłożyć do blaszki. Resztę czekolady posiekać i posypać po wierzchu ciasta. Wstawić do piekarnika i piec przez ok. 35 minut";
		Recipie recipie = new Recipie("Brownie", description, allIngredients);
		recipieService.save(recipie);
	}

	private void addArbuzada() {
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient water = ingredientService.findByName("woda");
		Ingredient watermelon = ingredientService.findByName("arbuz");
		Ingredient lime = ingredientService.findByName("limonka");
		Ingredient ginger = ingredientService.findByName("imbir");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(sugar, water, watermelon, lime, ginger));
		String description = "Rozpuścić cukier w wodzie, zagotować, ostudzić. Arbuza obrać ze skóry i oczyścić z pestek. Miąższ pokroić w kostkę i włożyć do miski, zmiksować. Imbir zetrzeć na drobnej tarce. Imbir dodać do arbuza i wlać syrop. Wszystko wymieszać, przelać przez sitko do dzbanka. Dodać sok z limonki, wymieszać i wstawić do lodówki do schłodzenia na minimum godzinę.";
		Recipie recipie = new Recipie("Arbuzada", description, allIngredients);
		recipieService.save(recipie);
	}

	private void addRacuchy() {
		Ingredient yeast = ingredientService.findByName("drożdże");
		Ingredient milk = ingredientService.findByName("mleko");
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient egg = ingredientService.findByName("jajko");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient butter = ingredientService.findByName("masło");
		List<Ingredient> allIngredients = new ArrayList<>(Arrays.asList(yeast, milk, flour, egg, sugar, butter));
		String description = "Drożdże rozetrzeć z 1 łyżeczką cukru, następnie rozmieszać z ciepłym mlekiem oraz z kilkoma łyżkami mąki. Zostawić do wyrośnięcia w ciepłem miejscu na około 15 minut. Masło roztopić i ostudzić. Z białek ubić sztywną pianę, dodać resztę cukru i dalej ubijając dodawać kolejno żółtka. Do wyrośniętego rozczynu z drożdży dodać resztę mąki, stopione masło, ubite jaja, jednocześnie wyrabiając ciasto przez około 15 minut. Odstawić do wyrośnięcia na około 20 minut w ciepłe miejsce. Gdy ciasto wyrośnie, uformować małe kulki, w środek włożyć pokrojone jabłka lub 3 wiśnie, zlepić jak pierogi i uformować placuszki. Smażyć na głębokim oleju, na średnim ogniu, po kilka minut z każdej strony, aż nabiorą złotego koloru. Posypać cukrem pudrem.";
		Recipie recipie = new Recipie("Racuchy z jabłkami", description, allIngredients);
		recipieService.save(recipie);
	}

	private void addGofry() {
		Ingredient flour = ingredientService.findByName("mąka");
		Ingredient yeast = ingredientService.findByName("drożdże");
		Ingredient sugar = ingredientService.findByName("cukier");
		Ingredient soda = ingredientService.findByName("soda oczyszczona");
		Ingredient salt = ingredientService.findByName("sól");
		Ingredient milk = ingredientService.findByName("mleko");
		Ingredient butter = ingredientService.findByName("masło");
		Ingredient egg = ingredientService.findByName("jajko");
		List<Ingredient> allIngredients = new ArrayList<>(
				Arrays.asList(flour, yeast, sugar, soda, salt, milk, butter, egg));
		String description = "Mąkę przesiać do miski. Dodać pozostałe suche składniki (suszone drożdże, cukier, sodę oczyszczoną, sól) i wymieszać. W garnku na małym ogniu lekko (do maks. 40 st. C) podgrzać mleko i mieszając rózgą rozpuścić w nim masło. Dodać jajka i dokładnie wymieszać. Zawartość garnka pomału wlać do miski z suchymi składnikami, jednocześnie miksując do uzyskania jednorodnej masy. Kontynuować miksowanie przez kolejne ok. 2 minuty. Przygotowane ciasto odstawić pod przykryciem na minimum 1 godzinę (maks. 2 godziny) - po tym czasie powinno zwiększyć objętość i mieć charakterystyczne pęcherze na powierzchni. Odstane ciasto wymieszać i wykładać na dobrze rozgrzaną gofrownicę.";
		Recipie recipie = new Recipie("Gofry", description, allIngredients);
		recipieService.save(recipie);
	}

	private void initializeIngredients() {
		ingredientService.save(new Ingredient("cukier"));
		ingredientService.save(new Ingredient("gorzka czekolada"));
		ingredientService.save(new Ingredient("jajko"));
		ingredientService.save(new Ingredient("masło"));
		ingredientService.save(new Ingredient("mąka"));
		ingredientService.save(new Ingredient("sól"));
		ingredientService.save(new Ingredient("woda"));
		ingredientService.save(new Ingredient("arbuz"));
		ingredientService.save(new Ingredient("limonka"));
		ingredientService.save(new Ingredient("imbir"));
		ingredientService.save(new Ingredient("drożdże"));
		ingredientService.save(new Ingredient("mleko"));
		ingredientService.save(new Ingredient("soda oczyszczona"));
	}

	private void initializeUsers() {
		String password1 = passwordEncoder.encode("password1");
		User user1 = new User("username1", password1, 1);
		userService.save(user1);
		User user2 = new User("username2", password1, 2);
		userService.save(user2);
		User user3 = new User("username3", password1, 3);
		userService.save(user3);
		User user4 = new User("username4", password1, 4);
		userService.save(user4);
	}

	private void initializeTables() {
		tableService.save(new TableLabel(TABLE_USER, 1));
		tableService.save(new TableLabel(TABLE_TABLELABEL, 1));
		tableService.save(new TableLabel(TABLE_INGREDIENT, 2));
		tableService.save(new TableLabel(TABLE_RECIPIE, 3));
		tableService.save(new TableLabel(TABLE_RECIPIE_INGREDIENT, 4));
	}

}
