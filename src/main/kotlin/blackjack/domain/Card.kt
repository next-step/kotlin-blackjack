package blackjack.domain

data class Card(private val card: Pair<CardScore, Suit>) {

    constructor(cardScore: CardScore, suit: Suit) : this(Pair(cardScore, suit))

    fun score(): Int = CardScore.scoreOfCard(card.first)

    fun isAce(): Boolean = this.card.first == CardScore.ACE

    override fun toString(): String {
        val initialOfCard = CardScore.initialOfCard(card.first)
        return initialOfCard + card.second.shapeName
    }
}
