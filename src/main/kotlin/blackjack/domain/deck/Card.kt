package blackjack.domain.deck

data class Card(val pip: Pip, val suit: Suit) {

    fun getScore(totalScore: Int = 0) = Pip.scoreOf(pip, totalScore)

    override fun toString(): String = "${pip.displayName} ${suit.displayName}"
}
