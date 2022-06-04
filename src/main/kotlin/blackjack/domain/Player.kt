package blackjack.domain

class Player(val name: String, cards: List<Card>) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun score(): Score {
        val originScore = cards.fold(0) { acc, card -> acc + card.value.origin }
        val alternativeScore = cards.fold(0) { acc, card ->
            if (card.value.alternative != 0) {
                acc + card.value.alternative
            } else {
                acc + card.value.origin
            }
        }

        return Score(originScore, alternativeScore)
    }

    infix fun take(newCard: List<Card>) {
        cards += newCard
    }

    fun numberOfCards(): Int {
        return cards.size
    }

    override fun toString(): String {
        return "$name cards : $cards"
    }
}
