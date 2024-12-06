package blackjack.domain

open class BlackJackNormalPlayer(
    val name: String,
    val blackJackPlayerCards: BlackJackPlayerCards,
) : BlackJackPlayer {
    init {
        require(blackJackPlayerCards.cards.size == BlackJackPlayer.DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    override fun drawCard(blackJackDeck: BlackJackDeck): Boolean {
        if (drawPossible()) {
            blackJackPlayerCards.addCard(blackJackDeck.draw())
            return true
        }
        return false
    }

    fun drawPossible(): Boolean {
        return blackJackPlayerCards.isCardNumberSumUnderBlackJackWinCardSum()
    }

    override fun getBestSum(): Int {
        return blackJackPlayerCards.getCardsBestSum()
    }
}
