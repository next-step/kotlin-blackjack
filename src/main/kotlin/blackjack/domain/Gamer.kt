package blackjack.domain

data class Gamer(val name: String, val cards: Cards = Cards()) {

    override fun toString(): String {
        return "${name}카드: $cards"
    }

    fun getTotalScore(isAceEleven: Boolean = false): Int {
        return cards.getTotalScore(isAceEleven)
    }

    fun addCard(card: Card) = cards.addCard(card)

    fun hasCard(card: Card): Boolean = cards.hasCard(card)
}
