# IPL Player Auction

![Alt text]([image-url](https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/d2004693a143f320680d7291ff371eab/large.png))
## Project Description

**IPL Player Auction** is a Java-based application that demonstrates the application of networking and multi-threading in real-life scenarios. The system simulates the process of an IPL (Indian Premier League) player auction, where teams can buy and sell players. It consists of two main sections: **Admin** and **Club Manager**.

- **Admin Section**: Manages the player database and provides various query options.
- **Club Manager Section**: Allows multiple teams to log in, view available players, and participate in buying/selling players.

## Features

### Admin Section
- **Player List**: Displays a list of players with their details and images.
- **Search Options**: Includes search filters by:
  - Player name
  - Club
  - Country
  - Position
  - Salary range
- **Statistics**: Provides statistics such as:
  - Country-wise player count
  - Maximum salary in a club
  - Maximum height in a club
  - Maximum age in a club
- **Player Management**: Allows the admin to add new players to the database.

### Club Manager Section
- **Team Login**: Each team must log in using their unique username and password.
- **Dressing Room**: Displays the list of players in the club's roster with their details and images.
- **Auction Mechanism**: 
  - Teams can see available players for purchase.
  - Once a team buys a player, that player is removed from the available list.
  - Players can also be sold, making them visible for other teams to buy.
  - Supports multiple teams logging in simultaneously, with each team having its own dedicated "dressing room."

## Technologies Used

- **Java**: Core programming language.
- **JavaFX**: For creating the graphical user interface (GUI).
- **Networking & Multi-threading**: To handle multiple clients (teams) interacting with the server simultaneously.

## Installation

### Prerequisites
Ensure that you have the following installed:

- **Java SDK** (Java 8 or higher recommended)
- **JavaFX** (compatible with your JDK version)

### Steps to Install:
1. Clone the repository or download the project files.
2. Ensure that you have a compatible version of **Java SDK** and **JavaFX** set up.
3. Compile and run the project using your preferred IDE (e.g., IntelliJ IDEA, Eclipse) or from the command line.

```bash
javac -cp "C:\javafx-sdk-23\lib\*" Main.java
java -cp ".;C:\javafx-sdk-23\lib\*" Main





