package blackjack.domain

class BlackJackPlayer(
    val name: String,
    val blackJackCards: BlackJackCards,
) {
    init {
        require(blackJackCards.cards.size == DEFAULT_CARD_NUMBER) { "플레이어는 처음에 2장만 가지고 시작해야해요" }
    }

    fun drawCard(blackJackCard: BlackJackCard) {
        blackJackCards.addCard(blackJackCard)
    }

    fun isDrawPossible(): Boolean {
        return blackJackCards.isCardNumberSumUnder21()
    }

    fun getBestSum(): Int {
        return blackJackCards.getCardsBestSum()
    }

    companion object {
        private val DEFAULT_CARD_NUMBER = 2
    }
}
