# Chatbot Application

## Description
This is a Spring Boot-based chatbot application that integrates with the OpenAI API to provide AI-driven responses to user queries.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)


## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/chatbot.git
   ```
2. Navigate to the project directory:
   ```bash
   cd chatbot
   ```
3. Install the necessary dependencies:
   ```bash
   ./mvnw install
   ```
4. Update the `application.properties` file with your OpenAI API key.

## Usage
To start the application, run:
```bash
./mvnw spring-boot:run
```

You can then send a POST request to the endpoint:
```bash
curl -X POST http://localhost:8080/chat -H "Content-Type: application/json" -d '{"message": "Hello!"}'
```

## Features
- AI-driven responses using OpenAI API.
- Easy integration with existing applications.
- Supports multiple conversation contexts.

## Contributing
Contributions are welcome! Please read the [CONTRIBUTING.md](CONTRIBUTING.md) for details on how to contribute.
