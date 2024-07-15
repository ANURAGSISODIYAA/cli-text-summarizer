# CLI Tool for Text Summarization

A command-line tool that summarizes text or files using the Ollama API with the Qwen2 0.5B model.

## Features

- Summarize plain text input or text from a specified file.
- Uses the Qwen2 0.5B model for efficient summarization.

## Prerequisites

- Java JDK 21 
- Maven installed
- Ollama CLI installed

## Installation

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd <repository-directory>
2. **Build the project:**
   ```bash
   mvn clean package
3. **Ensure Ollama is set up and the Qwen2 model is available:**
   ```bash
   ollama pull qwen2:0.5
   
## To summarize text from a specified file, use:

```bash
java -jar target/cli-text-summarizer.jar --file <path-to-file>
```
**Example**

Summarizing Text
```bash
java -jar target/cli-text-summarizer.jar "Artificial intelligence is transforming industries and enhancing our daily lives."
```
Summarizing a File
```bash
java -jar target/cli-text-summarizer.jar --file example.txt

