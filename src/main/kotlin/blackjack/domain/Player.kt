package blackjack.domain

class Player(private val name: String) {
    var deck = Cards()

    fun start(): Player {
        deck = CardDeck.start()
        return this
    }

    fun hit(card: Card) {
        deck.add(card)
    }

    fun takeOrBust(card: Card): PlayerState {
        return if (deck.underTheBlackJack(card))
            PlayerState.HIT
        else
            PlayerState.BUST
    }

    fun getScore(): Int {
        val hasAce = deck.cards.find { it.value.hasMultiNumber != null }
        val sum = deck.cards.sumOf { it.value.number }
        return if (hasAce != null) {
            val aceSum = deck.cards.sumOf { it.value.hasMultiNumber ?: it.value.number }
            if (aceSum < sum) {
                if (sum > BLACKJACK_NUMBER) aceSum
                else sum
            } else {
                if (aceSum > BLACKJACK_NUMBER) sum
                else aceSum
            }
        } else {
            sum
        }
    }

    override fun toString(): String {
        return name
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
