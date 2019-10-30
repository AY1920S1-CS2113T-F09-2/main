/**
 * BatchCommand.java
 * Support commands related to batch file imports & exports.
 *
 * @author kuromono
 */
package cube.logic.command;

import cube.exception.CubeException;
import cube.logic.command.util.CommandResult;
import cube.model.ModelManager;
import cube.model.food.Food;
import cube.model.food.FoodList;
import cube.storage.StorageManager;
import cube.util.FileUtilCSV;

import java.util.ArrayList;

public class BatchCommand extends Command {
    /**
     * Use enums to specify the export or import operations to be performed by FileUtilCSV.
     */
    public enum OperationType {
        EXPORT, IMPORT
    }

    private String fileName;
    private BatchCommand.OperationType operationType;
    private FileUtilCSV<Food> batchUtil;

    private final String MESSAGE_SUCCESS = "The product list has been successfully %1$s as file:\n"
        + "%2$s\n";
    private final String MESSAGE_EXPORT = "exported";
    private final String MESSAGE_IMPORT = "imported";

    /**
     * Default constructor.
     * @param fileName Sets the filename of the CSV file to be loaded/saved from.
     * @param operationType Specifies to either IMPORT or EXPORT operation.
     */
    public BatchCommand(String fileName, String operationType) {
        this.fileName = fileName;
        this.operationType = OperationType.valueOf(operationType);
        this.batchUtil = new FileUtilCSV<>("data", fileName, new Food());
    }

    /**
     * Calls & updates the required functions for CSV batch import operations.
     * @param model ModelManager object to update the FoodList object from the import.
     * @param storage StorageManager object to update the FoodList object from the import.
     * @throws CubeException Throws an exception if error occured during file handling.
     */
    private void batchImport(ModelManager model, StorageManager storage) throws CubeException {
        ArrayList<Food> foodList = batchUtil.load();
        FoodList importedFoodList = new FoodList(foodList);
        model.setFoodList(importedFoodList);
        storage.storeFoodList(importedFoodList);
    }

    /**
     * Calls & updates the required functions for CSV batch export operations.
     * @param storage StorageManager object that contains the FoodList object to be saved.
     * @throws CubeException Throws an exception if error occured during file handling.
     */
    private void batchExport(StorageManager storage) throws CubeException {
        batchUtil.save(storage.getFoodList().getFoodList());
    }

    /**
     * Constructs the command result output to be shown to the user.
     */
    @Override
    public CommandResult execute(ModelManager model, StorageManager storage) {
        try {
            switch (operationType) {
            case IMPORT:
                batchImport(model, storage);
                return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_IMPORT, fileName));
            case EXPORT:
                batchExport(storage);
                return new CommandResult(String.format(MESSAGE_SUCCESS, MESSAGE_EXPORT, fileName));
            }
        } catch (CubeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
