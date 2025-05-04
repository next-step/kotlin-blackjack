# kotlin-blackjack

## Blackjack 

### Key objects
Card

- [x] Number cards are counted by their face value.
- [x] Aces can count as either 1 or 11.

Card Number

- [x] Number or symbol (J, Q, K, A).
- [x] Face cards (King, Queen, Jack) are each worth 10.

Card Suit

- [x] Contains four card suits (Spades, Hearts, Diamonds, Clubs).

Deck

- [x] Deck must not contain duplicated cards.

Hands
- [x] Must have two cards to be initialized.
- [x] Should be able to add card to hands.

Player

- [x] Receives two cards at start of the game.
- [x] Players can choose to draw additional cards as long as their total does not exceed 21.

### States

State
- [x] Interface for all game states

InitialTurn
- [x] Should remain initial until card size is less than or equal to 2
- [x] If hands has more than 3 cards after draw, should move state to Hit.

Hit
- [x] Can add cards until bust
