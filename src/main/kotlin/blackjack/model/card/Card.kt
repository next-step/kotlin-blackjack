package blackjack.model.card

data class Card(private val cardScore: CardScore, private val suit: Suit) {

    val score: Int = cardScore.score

    fun isAce(): Boolean = CardScore.ACE == this.cardScore

    override fun toString(): String = cardScore.initial + suit.shapeName
}
