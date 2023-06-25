package blackjack

class Round(private val trump: Trump = Trump(_cards = Trump.defaultTrump().shuffled().toMutableList())) {
    fun getCard(): Card = trump.getCard()

    companion object {
        const val DEALER_MINIMUM_SCORE = 17
        const val BLACK_JACK = 21
    }
}
