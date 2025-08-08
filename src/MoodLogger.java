import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MoodLogger {

    private static final String FILE_PATH = "data/logs.csv";

    // Save one MoodEntry to CSV
    public static void saveEntry(MoodEntry entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(entry.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving mood entry: " + e.getMessage());
        }
    }

    // Load all entries from CSV
    public static List<MoodEntry> loadEntries() {
        List<MoodEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    String date = parts[0];
                    int moodLevel = Integer.parseInt(parts[1]);
                    String note = parts[2];
                    entries.add(new MoodEntry(date, moodLevel, note));
                }
            }
        } catch (FileNotFoundException e) {
            // It's okay if file doesn't exist yet
        } catch (IOException e) {
            System.out.println("Error reading mood entries: " + e.getMessage());
        }

        return entries;
    }
}
