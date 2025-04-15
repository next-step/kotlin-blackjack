# kotlin-blackjack
## Step 2
### Functional Requirements
- Card values follow standard Blackjack rules:
  - Number cards are counted by their face value.
  - Face cards (King, Queen, Jack) are each worth 10.
  - Aces can count as either 1 or 11.
- At the start of the game, each player receives two cards.
- Players can choose to draw additional cards as long as their total does not exceed 21.

### Player
- [x] Have hand
- [x] Have name
- [x] Add card to Hand

### Hand
- [x] Have cards as a list
- [x] Return cards total score
  - [x] Aces can count as either 1 or 11
- [x] Add card
- [x] Return size
- [x] Have to have at least two cards

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