package blackjack.domain

open class BlackJackPlayer(
    val name: String,
    val blackJackPlayerCards: BlackJackPlayerCards,
) {
    init {
        require(blackJackPlayerCards.cards.size == DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    fun drawCard(blackJackCard: BlackJackCard) {
        blackJackPlayerCards.addCard(blackJackCard)
    }

    open fun isDrawPossible(): Boolean {
        return blackJackPlayerCards.isCardNumberSumUnderBlackJackWinCardSum()
    }

    fun getBestSum(): Int {
        return blackJackPlayerCards.getCardsBestSum()
    }

    companion object {
        private const val DEFAULT_CARD_NUMBER = 2
    }
}
