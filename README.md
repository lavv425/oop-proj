# DevTrack

**DevTrack** is a modular CLI application built with **Java 21+** and **Spring Boot**, designed to help developers manage projects, milestones, and tasks directly from the terminal. The app is backed by **MongoDB** and structured with clean separation of concerns, making it easy to extend and test.

---

## 📂 Project Structure

```
src/main/java/com/devtrack
├── factory/             # Tool factory and creation logic
├── interfaces/          # Service interfaces
├── iterator/            # Task iteration abstraction (iterator pattern)
├── menu/                # All CLI commands and menu logic
├── model/               # Domain entities and abstractions
├── repository/          # MongoDB repository layer
├── runner/              # CLI entrypoint (DevTrackCLI)
├── security/            # Input validation & exception shielding
├── service/             # Business logic (ProjectServiceImpl)
├── utils/               # CLI formatting & logger utilities
└── resources/
    └── application.properties
```

---

## ✅ Features

- Create and delete projects
- Add milestones to specific projects
- Add tasks under a given milestone
- View projects and milestone task details
- Structured menu and submenu navigation
- Input sanitization and error shielding
- Fully logged using SLF4J
- Built-in testability with mocked services

---

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher (tested with JDK 21)
- Maven 3.9+
- MongoDB (running locally on `localhost:27017`)

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

### Example Output

```
=== Welcome to DevTrack CLI ===

Main Menu:
1. Create a Project
2. Manage Projects
3. EXIT
```

---

## 🔐 Security & Utilities

| Module               | Purpose                                  |
|----------------------|------------------------------------------|
| `InputSanitizer`     | Strips unsafe characters from user input |
| `ExceptionShield`    | Centralized exception handling           |
| `LoggerUtil`         | SLF4J abstraction for consistent logging |
| `ConsoleStyle`       | ANSI color styling for CLI               |

---

## 🧪 Testing

Tests are written using **JUnit 5** and **Mockito**.

To enable mocking of interfaces and final classes:

```
# src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker
mock-maker-inline
```

Run tests with:

```bash
mvn test
```

---

## 📌 Roadmap (Possible)

- [ ] REST API interface
- [ ] Task completion toggle
- [ ] Report export (CSV/JSON)
- [ ] CLI visual themes
- [ ] Toolchain tracking module

---

## 👤 Author

**Michael Lavigna**  
*Computer Engineering & AI student • Fullstack Developer*

---

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 🙋 Contributing

Pull requests are welcome. For major changes, open an issue first to discuss what you would like to change or add.

---