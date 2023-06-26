package blackjack.domain

import blackjack.domain.Cards.Companion.TARGET_SUM

class Player(
    val name: String,
) {
    private var myCards: Cards = Cards.empty()

    fun countMyCards(): Int = myCards.cards.size

    fun sumMyCards(): Int = myCards.calculateOptimalSum()

    fun showMyCards(): String = myCards.toString()

    fun canDraw(): Boolean = myCards.calculateOptimalSum() <= TARGET_SUM

    fun drawCard() {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val drawnCard = GameCardsSet.drawRandomCard()
        myCards = myCards.add(drawnCard)
    }
}
