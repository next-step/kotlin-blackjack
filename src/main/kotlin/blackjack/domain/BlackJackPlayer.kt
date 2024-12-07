package blackjack.domain

abstract class BlackJackPlayer(
    val name: String,
    val blackJackPlayerCards: BlackJackPlayerCards,
    protected var _profit: Int,
) {
    val profit: Int
        get() = _profit

    abstract fun drawCard(blackJackDeck: BlackJackDeck)

    abstract fun getBestSum(): Int

    abstract fun drawPossible(): Boolean

    companion object {
        const val DEFAULT_CARD_NUMBER = 2
        const val BLACKJACK_PROFIT_RATE = 1.5
    }
}
