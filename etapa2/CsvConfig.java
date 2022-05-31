package etapa2;

// general file to set and manage base configurations for the code to properly read the table
public class CsvConfig {
    // determines the location of the file inside project folder
    public static final String FILE_PATH = "etapa2\\TA_PRECO_MEDICAMENTO.csv";
    // determines how the csv file is separated
    // ideally, it would change according to user's computer language
    // but we'll assume that it's in portuguese and therefore utilises semi-column
    // (;)
    public static final String COLUMN_SEPARATOR = ";";
}
