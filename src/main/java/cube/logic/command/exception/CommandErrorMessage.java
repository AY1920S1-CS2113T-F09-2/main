package cube.logic.command.exception;

public class CommandErrorMessage {
	public static final String FOOD_ALREADY_EXISTS
			= "OOPS!!! The food already exists";
	public static final String FOOD_NOT_EXISTS
			= "OOPS!!! The food does not exists";	
	public static final String INVALID_QUANTITY_SOLD
			= "OOPS!!! The quantity sold is negative or too large";	
	public static final String INVALID_INDEX
			= "OOPS!!! The index is out of the range of food list";
	public static final String INVALID_TYPE
			= "OOPS!!! The food type does not exist";
}