# 🗂️ File Sorter

A simple, fast, command-line utility written in Java that automatically organizes files in a directory based on their extensions.

This tool reads a set of internal rules (like `.pdf=Documents`) and moves files into their corresponding subfolders.

## 🚀 Download & Requirements

  * **Download:** You can download the latest `FileSorter.jar` file from the [**Releases Page**](https://github.com/cigamaLTT/FileSorting/releases/tag/v1.0).
  * **Requirements:** You only need the **Java Runtime Environment (JRE) 11** or newer to run this program. You can download it [here](https://www.java.com/en/download/).

-----

## How to Use

After downloading `FileSorter.jar`, open your Terminal (PowerShell, CMD, or bash).

You can run the program in two ways:

### Option 1: Sort In-Place (Recommended)

This is the most convenient way. The program will sort the files in the same directory where you run the command.

1.  **Pro-tip:** Copy `FileSorter.jar` directly into the folder you want to clean up (e.g., your `Downloads` folder).
2.  Open your Terminal in that folder.
3.  Run the command:
    ```bash
    java -jar FileSorter.jar
    ```
    *(The program will find all files in that folder and move them into sub-folders like `Images`, `Videos`, etc.)*

### Option 2: Specify Source and Target Directories

You can explicitly tell the program which folder to sort and where to put the sorted files.

  * **Syntax:**

    ```bash
    java -jar "path\to\FileSorter.jar" "SOURCE_DIRECTORY" "TARGET_DIRECTORY"
    ```

  * **Example:**
    Let's say you downloaded the file to `C:\Tools\FileSorter.jar` and you want to sort your `Desktop`.

    ```bash
    java -jar "C:\Tools\FileSorter.jar" "C:\Users\YourName\Desktop\Unsorted" "C:\Users\YourName\Desktop\SortedFiles"
    ```

-----

## Features

  * **Automated Sorting:** Moves files into designated folders based on built-in rules.
  * **In-Place Sorting:** Run without arguments to sort the current directory.
  * **Conflict Handling:** Automatically renames files if a file with the same name already exists in the target directory (e.g., `file (1).txt`, `file (2).txt`).

## Default Rules

The following sorting rules are bundled *inside* the `FileSorter.jar` file.

*(Currently, changing these rules requires modifying the source code and building a new `.jar` file. The `rules.properties` file is not read from the outside).*

```properties
# === BUILT-IN RULES ===

# --- Documents ---
.pdf=Documents
.docx=Documents
.doc=Documents
.txt=TextFiles
.odt=Documents
.rtf=Documents
.md=Documents
.epub=Ebooks
.mobi=Ebooks

# --- Images ---
.jpg=Images
.jpeg=Images
.png=Images
.gif=Images
.bmp=Images
.svg=Images
.webp=Images
.heic=Images
.psd=Photoshop

# --- Audio ---
.mp3=Audio
.m4a=Audio
.wav=Audio
.flac=Audio
.aac=Audio
.ogg=Audio

# --- Video ---
.mp4=Videos
.mov=Videos
.avi=Videos
.mkv=Videos
.wmv=Videos
.webm=Videos

# --- Archives ---
.zip=Archives
.rar=Archives
.7z=Archives
.tar=Archives
.gz=Archives
.iso=Archives

# ... and more
```
