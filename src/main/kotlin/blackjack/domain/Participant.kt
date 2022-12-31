package blackjack.domain

class Participant(
    val name: String,
    val cards: Cards = Cards(),
    var score: Int = 0,
) {
    fun addCard(card: Card) {
        cards.add(card)
        score += card.denomination.calc(score)
    }

    fun isBust(): Boolean {
        return score > 21
    }

    fun isBlackJack(): Boolean {
        return score == 21 && cards.cards.size == 2
    }

    fun isHit(): Boolean {
        return score < 16
    }
}
