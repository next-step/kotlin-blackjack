package blackjack.domain

interface BlackJackPlayer {
    fun drawCard(blackJackDeck: BlackJackDeck): Boolean

    fun getBestSum(): Int

    companion object {
        const val DEFAULT_CARD_NUMBER = 2
    }
}
