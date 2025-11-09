# 🗂️ File Sorter (Java Edition)

A stateful, command-line utility written in Java that automatically organizes files in a directory based on their extensions.

This tool is built to be a robust, real-world application. It reads internal sorting rules (e.g., `.pdf=Documents`), handles file name conflicts, and most importantly, **tracks every file it moves** in a persistent "manifest" file (`manifest.json`).

This stateful design (using Gson for JSON persistence) lays the groundwork for advanced features like sorting by different methods (date, size) and future "Undo" functionality.

## 🚀 Features

  * **Rule-Based Sorting:** Sorts files using an internal, customizable `rules.properties` file.
  * **Safe Conflict Handling:** Automatically renames files if a file with the same name already exists in the target directory (e.g., `file (1).txt`, `file (2).txt`).
  * **State Management:** Creates a `manifest.json` file in the user's home directory (`.filesorter/manifest.json`). This log tracks the "before" and "after" path of every file moved.
  * **Clean Architecture (OOP):** The source code is professionally refactored into distinct packages: `core`, `config`, and `util`.
  * **Safe Execution:** The program intelligently skips itself (e.g., `FileSorter.jar`) when run in "Sort In-Place" mode.

## 🖥️ Requirements

  * **Download:** You can download the latest `FileSorter.jar` file from the [**Releases Page**](https://github.com/cigamaLTT/FileSorting/releases/tag/v2.0).
  * **Java:** You **must** have **Java Runtime Environment (JRE) 17 (LTS)** or newer to run this program.
  * **Library:** The `Gson` (JSON) library is already **embedded** inside the `.jar` file (built as a Fat Jar).

-----

## 📖 How to Use

This tool can be run in two modes.

### Option 1: Sort In-Place (0 Arguments)

This is the most convenient mode. It sorts all files in the same directory where the `.jar` file is located, using the default `BY_EXTENSION` method.

1.  Copy `FileSorter.jar` directly into the folder you want to clean up (e.g., your `Downloads` folder).
2.  Open your Terminal (CMD, PowerShell) in that same folder.
3.  Run the command:
    ```bash
    java -jar FileSorter.jar
    ```
    *(The program will find all files (except itself) and move them into sub-folders like `Images`, `Videos`, etc., right inside the `Downloads` folder.)*

### Option 2: Advanced Mode (3 Arguments)

This gives you full control over the source, destination, and sorting method.

  * **Syntax:**

    ```bash
    java -jar "path/to/FileSorter.jar" "SOURCE_DIRECTORY" "TARGET_DIRECTORY" "SORT_METHOD"
    ```

  * **Arguments:**

      * `SOURCE_DIRECTORY`: The folder you want to clean up (e.g., `C:\Users\YourName\Downloads`).
      * `TARGET_DIRECTORY`: The root folder where sorted subfolders will be created (e.g., `D:\SortedFiles`).
      * `SORT_METHOD`: The sorting method. (Currently, only `"BY_EXTENSION"` is supported).

  * **Example:**

    ```bash
    java -jar "C:\Tools\FileSorter.jar" "C:\Users\YourName\Downloads" "D:\Files\Sorted" "BY_EXTENSION"
    ```

-----

## 🛠️ Technology Stack

  * **Language:** Java 17 (LTS)
  * **File Operations:** Java NIO.2 (`Path`, `Files`, `DirectoryStream`)
  * **Configuration:** `java.util.Properties` (to read `rules.properties` from *inside* the `.jar`)
  * **Data Persistence:** **Gson** (for reading/writing the `manifest.json`)
  * **Design:** OOP (Refactored into `core`, `config`, and `util` packages)

## 📜 Default Rules

The following sorting rules are **bundled (internal)** *inside* the `FileSorter.jar` (from the `rules.properties` file in `src/com/filesorter/config/`).

*(Currently, changing these rules requires modifying the `rules.properties` file in the source code and building a new `.jar` file).*

```properties
# === BUILT-IN RULES ===

# --- Documents ---
.pdf=Documents
.docx=Documents
.doc=Documents
.txt=TextFiles
# ... etc

# --- Images ---
.jpg=Images
.jpeg=Images
.png=Images
# ... etc
```