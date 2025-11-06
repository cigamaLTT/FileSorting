# 🗂️ Java File Sorter

A simple, fast, command-line utility written in Java that automatically organizes files in a directory based on their extensions. It reads sorting rules from a `rules.properties` file and moves files into their corresponding subfolders.

## Features

  * **Automated Sorting:** Moves files into designated folders (e.g., `Videos`, `Images`, `Documents`) based on customizable rules.
  * **Rule-Based:** Easily add or change sorting rules by editing the `rules.properties` file without needing to recompile the code.
  * **Conflict Handling:** Automatically renames files if a file with the same name already exists in the target directory (e.g., `file (1).txt`, `file (2).txt`).

## Requirements

  * [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or newer (to compile and run).

## Installation & Compilation

1.  Clone this repository to your local machine:

    ```bash
    git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
    ```

    *(Replace `YOUR_USERNAME/YOUR_REPOSITORY` with your project's URL)*

2.  Navigate into the project directory:

    ```bash
    cd YOUR_REPOSITORY
    ```

3.  Compile the Java code. Open your Terminal (PowerShell, CMD, or bash) and run:

    ```bash
    # This command works in most modern terminals
    javac Main.java RuleLoader.java

    # Or you could also use:
    javac *.java
    ```

    This will create `Main.class` and `RuleLoader.class` files in the same directory.

## How to Use

Once compiled, you can run the program using the `java` command.

### Option 1: Sort In-Place (Current Directory)

This is the most convenient way to use the sorter. It uses the current directory as both the **source** (where to find files) and the **destination** (where to create subfolders).

1.  Open your Terminal directly in the folder you want to clean up (e.g., your `Downloads` folder).

2.  Run the `java` command, using the `-cp` (classpath) flag to tell Java where to find your compiled `.class` files.

    ```bash
    # Replace "E:\github\FileSorting\FileSorting" with the FULL path to YOUR project folder
    java -cp "E:\github\FileSorting\FileSorting" Main
    ```

    The program will run and organize all files within that directory.

### Option 2: Specify Source and Target Directories

You can explicitly tell the program which folder to sort and where to put the sorted files.

  * **Syntax:**

    ```bash
    java -cp "PATH_TO_YOUR_CODE" Main "SOURCE_DIRECTORY" "TARGET_DIRECTORY"
    ```

  * **Example:**

    ```bash
    java -cp "E:\github\FileSorting\FileSorting" Main "C:\Users\YourName\Downloads" "C:\Users\YourName\Desktop\SortedFiles"
    ```

    This will take files from `Downloads` and move them into subfolders inside `SortedFiles`.

## Configuration (rules.properties)

The entire sorting logic is controlled by the `rules.properties` file. You can customize this file to fit your needs.

The syntax is simple: `.extension=TargetFolderName`

```properties
# === FILE CLEANER RULES ===
# Each line is a rule: .extension=FolderName
# Lines starting with # are comments.

# --- Documents ---
.pdf=Documents
.docx=Documents
.txt=TextFiles
.epub=Ebooks

# --- Images ---
.jpg=Images
.png=Images

# --- Video ---
.mp4=Videos
.mkv=Videos

# --- Archives ---
.zip=Archives
.rar=Archives

# --- Code ---
.py=Code
.java=Code
```

Feel free to add, remove, or modify any rules. The program will pick up the changes on its next run without needing to be recompiled.
