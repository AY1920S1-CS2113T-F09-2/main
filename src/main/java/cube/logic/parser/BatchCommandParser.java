package cube.logic.parser;

import cube.logic.command.BatchCommand;
import cube.logic.parser.exception.ParserErrorMessage;
import cube.logic.parser.exception.ParserException;

/**
 * Parse batch command.
 */
public class BatchCommandParser implements ParserPrototype<BatchCommand> {

    /**
     * Parse user batch command.
     * @param args user inputs.
     * @return batch command with the corresponding operation type.
     * @throws ParserException when user input is illegal.
     */
    public BatchCommand parse(String[] args) throws ParserException {
        String[] params = new String[] {"-o", "-i", "-e"};

        if (ParserUtil.hasInvalidParameters(args, params)) {
            throw new ParserException(ParserErrorMessage.INVALID_PARAMETER);
        }
        if (ParserUtil.hasRepetitiveParameters(args)) {
            throw new ParserException(ParserErrorMessage.REPETITIVE_PARAMETER);
        }
        if (args.length < 3) {
            throw new ParserException(ParserErrorMessage.NOT_ENOUGH_PARAMETER);
        }

        switch (args[1]) {
        case "-o":
            return new BatchCommand(args[2], BatchCommand.OperationType.EXPORT);
        case "-i":
            return new BatchCommand(args[2], BatchCommand.OperationType.IMPORT);
        case "-e":
            return new BatchCommand(args[2], BatchCommand.OperationType.EMPTY);
        default:
            throw new ParserException(ParserErrorMessage.INVALID_COMMAND_FORMAT);
        }
    }
}
