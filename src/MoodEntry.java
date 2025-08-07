public class MoodEntry {
    private String date;
    private int moodLevel;
    private String note;

    public MoodEntry(String date, int moodLevel, String note) {
        this.date = date;
        this.moodLevel = moodLevel;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public String getNote() {
        return note;
    }

    // Returns a string in CSV format to save to file
    public String toCSV() {
        return date + "," + moodLevel + "," + note;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\nMood Level: " + moodLevel + "\nNote: " + note;
    }
}
