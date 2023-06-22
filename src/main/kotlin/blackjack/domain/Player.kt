package blackjack.domain

import blackjack.domain.Cards.Companion.TARGET_SUM

class Player(val name: String) {
    private var myCards: Cards = Cards.empty()

    fun sumMyCards(): Int = myCards.sum()

    fun showMyCards(): String = myCards.toString()

    fun canDraw(): Boolean = myCards.sum() <= TARGET_SUM

    fun drawCard() {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val drawnCard = GameCardsSet.drawRandomCard()
        if (drawnCard != null) {
            myCards = myCards.add(drawnCard)
        }
    }

    fun countCards(): Int = myCards.cards.size
}
