package blackjack.domain

abstract class BlackJackPlayer(
    val name: String,
    val blackJackPlayerCards: BlackJackPlayerCards,
) {
    abstract fun drawCard(blackJackDeck: BlackJackDeck)

    abstract fun getBestSum(): Int

    abstract fun drawPossible(): Boolean

    companion object {
        const val DEFAULT_CARD_NUMBER = 2
    }
}
