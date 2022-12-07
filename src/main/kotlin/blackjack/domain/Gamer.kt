package blackjack.domain

data class Gamer(val name: String, val cards: Cards = Cards()) {

    fun getTotalScore(isAceEleven: Boolean = false): Int {
        return cards.getTotalScore(isAceEleven)
    }

    fun addCard(card: Card) = cards.addCard(card)

    fun hasCard(card: Card): Boolean = cards.hasCard(card)
}
