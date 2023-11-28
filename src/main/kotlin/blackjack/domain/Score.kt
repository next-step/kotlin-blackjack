package blackjack.domain

class Score(cards: Cards) {
    val value: Int = cards.sumOf { card -> card.character.score }
}
