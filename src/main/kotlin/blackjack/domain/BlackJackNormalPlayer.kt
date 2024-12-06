package blackjack.domain

open class BlackJackNormalPlayer(
    name: String,
    blackJackPlayerCards: BlackJackPlayerCards,
    profit: Int = 0,
    val bet: Int = 0,
) : BlackJackPlayer(name, blackJackPlayerCards, profit) {
    init {
        require(blackJackPlayerCards.cards.size == BlackJackPlayer.DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    override fun drawCard(blackJackDeck: BlackJackDeck) {
        if (drawPossible()) {
            blackJackPlayerCards.addCard(blackJackDeck.draw())
        }
    }

    override fun drawPossible(): Boolean {
        return blackJackPlayerCards.isCardNumberSumUnderBlackJackWinCardSum()
    }

    override fun getBestSum(): Int {
        return blackJackPlayerCards.getCardsBestSum()
    }

    fun win() {
        profit += bet
    }

    fun lose() {
        profit -= bet
    }
}
