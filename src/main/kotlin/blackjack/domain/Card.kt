package blackjack.domain

data class Card(private val card: Pair<CardScore, Suit>) {

    fun score(): Int = card.first.score

    override fun toString(): String {
        val initialOfCard = CardScore.initialOfCard(card.first)
        return initialOfCard + card.second.shapeName
    }
}
