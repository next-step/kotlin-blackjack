# kotlin-blackjack

## Step 3
### Functional Requirements
Implement a program based on a simplified version of Blackjack. In this game, the player or dealer with a total closest to 21—without going over—wins.

- Card values follow standard Blackjack rules:
  - Number cards are counted by their face value.
  - Face cards (King, Queen, Jack) are each worth 10.
  - Aces can be worth either 1 or 11.
- Each player starts with two cards.
- Players may draw additional cards as long as their total remains 21 or less.
- The dealer must draw a card if their total is 16 or less, and must stand on 17 or more.
- If the dealer busts (goes over 21), all remaining players automatically win.
- After the game ends, display the result (win/loss) for each player.

## Step 2
### Functional Requirements
- Card values follow standard Blackjack rules:
  - Number cards are counted by their face value.
  - Face cards (King, Queen, Jack) are each worth 10.
  - Aces can count as either 1 or 11.
- At the start of the game, each player receives two cards.
- Players can choose to draw additional cards as long as their total does not exceed 21.

### Participant
- [x] Have hand
- [x] Add card to Hand
- [x] Open Card for first

### Dealer
- [x] Implement Participant
- [x] Add card to Hand

### Player
- [x] Implement Participant
- [x] Have name

### FirstTurn
- [x] Draw cards
- [x] When the sum is 21 return Blackjack
- [x] WHen the sum is less than 21 return Hit

### Hit
- [x] Draw Card
- [x] When the sum is over than 21 return Bust
- [x] When the sum is less than 21 return Hit
- [x] return stay

### Blackjack

### Bust

### Stay

### Hand
- [x] Have cards as a list
- [x] Return cards total score
  - [x] Aces can count as either 1 or 11
- [x] Add card
- [x] Return size
- [x] Have to have at least two cards
- [x] Return is bust
- [x] Return is blackjack

### PlayingCard
- [x] Has suit and denomination
- [x] Cache

### Deck
- [x] Have Cards
- [x] Draw card

### Suit
- enum class
- [x] Have Heart, Club, Diamond, Spade

### Denomination
- enum class
- [x] Have the number from Ace to King with score

### InputView
- [x] Get players name
- [x] Get player's response to get another card

### OutputView
- [x] Display player's cards
- [x] Display player's cards and total score
- [x] Display first turn
- [ ] Display final results