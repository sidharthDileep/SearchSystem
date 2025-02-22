public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

            AutocompleteSystem autocomplete = new AutocompleteSystem(10); // DB Connection


            // Fetch suggestions
            System.out.println("Suggestions for 'go': " + autocomplete.getSuggestions("go"));
            System.out.println("Suggestions for 'goo': " + autocomplete.getSuggestions("goo"));
            System.out.println("Suggestions for 'gol': " + autocomplete.getSuggestions("gol"));
    }
}