package blackjack.domain

data class Card(private val card: Pair<CardScore, Suit>) {

    fun score(): Int = CardScore.scoreOfCard(card.first)

    override fun toString(): String {
        val initialOfCard = CardScore.initialOfCard(card.first)
        return initialOfCard + card.second.shapeName
    }
}
