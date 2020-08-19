package blackjack.domain.deck

data class Card(val suits: Suits, val denomination: Denomination) {
    fun getScore(totalScore: Int = 0) = Denomination.scoreOf(denomination, totalScore)
    override fun toString(): String {
        return "${denomination.value}${suits.displayName}"
    }
}
