import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant:restaurants) {
            if(restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
        throw new restaurantNotFoundException(restaurantName);
    }
    public int getTotalCostForSelectedItems(List<String> selectedItems,String restaurantName) throws restaurantNotFoundException {
        Restaurant selectedRestaurant = findRestaurantByName(restaurantName);
        List<Item> completeMenu = selectedRestaurant.getMenu();
        int totalCost = 0;
        for(String selectedItem : selectedItems){
            Item item = findItemByName(selectedItem,completeMenu);
            if(item !=null){
                totalCost+=item.getPrice();
            }
        }
        return totalCost;
    }
    private Item findItemByName(String itemName, List<Item> menu) {
        for (Item item : menu) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
