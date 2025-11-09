# 🗂️ File Sorter 

A stateful, command-line utility written in Java that automatically organizes files from a source directory into a new target directory based on their extensions.

This tool is built to be a robust, real-world application. It reads internal sorting rules (e.g., `.pdf=Documents`), handles file name conflicts, and most importantly, **tracks every file it moves** in a persistent "manifest" file (`manifest.json`).

This stateful design (using Gson for JSON persistence) lays the groundwork for advanced features like sorting by different methods (date, size) and future "Undo" functionality.

## 🚀 Features

  * **Rule-Based Sorting:** Sorts files using an internal, customizable `rules.properties` file.
  * **Safe Conflict Handling:** Automatically renames files if a file with the same name already exists in the target directory (e.g., `file (1).txt`, `file (2).txt`).
  * **State Management:** Creates a `manifest.json` file in the user's home directory (`.filesorter/manifest.json`). This log tracks the "before" and "after" path of every file moved, allowing for future "un-sorting" or "re-sorting" operations.
  * **Clean Architecture (OOP):** The source code is professionally refactored into distinct packages:
      * `com.filesorter.core` (The main `SortEngine`)
      * `com.filesorter.config` (Manages `RuleLoader` and `ManifestManager`)
      * `com.filesorter.util` (Contains `FileUtils` for helper methods)
  * **Safe Execution:** The program intelligently skips itself (e.g., `FileSorter.jar`) if it's run inside the source directory.

## 🖥️ Requirements

  * **Download:** You can download the latest `FileSorter.jar` file from the [**Releases Page**](https://www.google.com/search?q=https://github.com/cigamaLTT/FileSorting/releases).
  * **Java:** You **must** have **Java Runtime Environment (JRE) 17 (LTS)** or newer to run this program.
  * **Library:** The `Gson` (JSON) library is already **embedded** inside the `.jar` file (built as a Fat Jar).

-----

## 📖 How to Use

This is a command-line (CLI) tool that requires **two arguments** to function: a Source directory and a Target directory.

### Syntax

Open your Terminal (PowerShell, CMD, or bash) and use the following syntax:

```bash
java -jar "path/to/FileSorter.jar" "SOURCE_DIRECTORY" "TARGET_DIRECTORY"
```

  * **`SOURCE_DIRECTORY`**: The folder you want to clean up (e.g., `C:\Users\YourName\Downloads`).
  * **`TARGET_DIRECTORY`**: The folder where you want the sorted subfolders to be created (e.g., `D:\SortedFiles`).

### Example

Let's say you downloaded `FileSorter.jar` to `C:\Tools\` and you want to clean up your `Downloads` folder and put the results in `D:\Archive`.

```bash
java -jar "C:\Tools\FileSorter.jar" "C:\Users\YourName\Downloads" "D:\Archive"
```

The program will scan the `Downloads` folder and create subfolders (like `D:\Archive\Images`, `D:\Archive\Documents`, etc.) based on the rules, while logging every move to the manifest.

*(**Note:** Running the .jar without arguments will default to sorting the current directory into itself, which is also supported).*

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

# ... (and many more)
```