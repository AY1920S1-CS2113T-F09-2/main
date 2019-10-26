/**
 * Handling of JSON File Read/Write operations in Cube
 *
 * @author kuromono
 */
package cube.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cube.model.food.FoodList;
import cube.model.sale.SalesHistory;

public class StorageManager {
	@JsonProperty
	private FoodStorage foodStorage;
	@JsonProperty
	private RevenueStorage revenueStorage;
	@JsonProperty
	private ConfigStorage configStorage;
	@JsonProperty
	private SaleStorage saleStorage;

	/**
	 * Default constructor.
	 * Creates a new instance of Food & Revenue Storage Classes.
	 */
	public StorageManager() {
		this.foodStorage = new FoodStorage();
		this.revenueStorage = new RevenueStorage();
		this.configStorage = new ConfigStorage();
		this.saleStorage = new SaleStorage();
	}

	/**
	 * Constructor with 2 arguments.
	 * Creates a new instance of Food & Revenue Storage Classes.
	 */
	public StorageManager(FoodStorage foodStorage, RevenueStorage revenueStorage, ConfigStorage configStorage, SaleStorage saleStorage) {
		this.foodStorage = foodStorage;
		this.revenueStorage = revenueStorage;
		this.configStorage = configStorage;
		this.saleStorage = saleStorage;
	}

	/**
	 * Retrieves the FoodList stored in the FoodStorage.
	 * @return FoodList object stored in the FoodStorage.
	 */
	@JsonIgnore
	public FoodList getFoodList() {
		return foodStorage.getFoodList();
	}

	/**
	 * Stores the specified FoodList object into the FoodStorage.
	 * @param foodlist FoodList object to be stored into the FoodStorage.
	 */
	public void storeFoodList(FoodList foodlist) {
		foodStorage.storeFoodList(foodlist);
	}

	/**
	 * Retrieves the revenue stored in the RevenueStorage.
	 * @return Revenue stored in the RevenueStorage.
	 */
	@JsonIgnore
	public double getRevenue() {
		return revenueStorage.getRevenue();
	}

	/**
	 * Stores the specified revenue into the RevenueStorage.
	 * @param revenue Revenue amount to be stored in the RevenueStorage.
	 */
	public void storeRevenue(double revenue) {
		revenueStorage.storeRevenue(revenue);
	}

	/**
	 * Retrieves the configuration storage containing user-defined configurations.
	 * @return ConfigStorage object containing the user-defined configurations.
	 */
	@JsonIgnore
	public ConfigStorage getConfig() {
		return configStorage;
	}


	@JsonIgnore
	public SalesHistory getSalesHistory() {
		return saleStorage.getSalesHistory();
	}

	public void storeSalesHistory(SalesHistory salesHistory) {
		saleStorage.storeSalesHistory(salesHistory);
	}

}