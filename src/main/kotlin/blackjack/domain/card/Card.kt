package blackjack.domain.card

sealed class Card(val suit: CardSuit, val value: Int, val bonusValue: Int = 0) {
    class Two(suit: CardSuit) : Card(suit, 2)
    class Three(suit: CardSuit) : Card(suit, 3)
    class Four(suit: CardSuit) : Card(suit, 4)
    class Five(suit: CardSuit) : Card(suit, 5)
    class Six(suit: CardSuit) : Card(suit, 6)
    class Seven(suit: CardSuit) : Card(suit, 7)
    class Eight(suit: CardSuit) : Card(suit, 8)
    class Nine(suit: CardSuit) : Card(suit, 9)
    class Ten(suit: CardSuit) : Card(suit, 10)
    class Jack(suit: CardSuit) : Card(suit, 10)
    class Queen(suit: CardSuit) : Card(suit, 10)
    class King(suit: CardSuit) : Card(suit, 10)
    class Ace(suit: CardSuit) : Card(suit, 1, 10)
}
