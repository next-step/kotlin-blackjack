package blackjack

class Card(
    val suit: Suit,
    val number: CardNumber,
) {
    val scores: List<Int> = number.scores
}
