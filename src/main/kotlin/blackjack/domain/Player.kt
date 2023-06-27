package blackjack.domain

import blackjack.domain.Cards.Companion.TARGET_SUM

open class Player(
    val name: String,
    private val gameCardsSet: GameCardsSet
) {
    private var myCards: Cards = Cards.empty()

    fun numberOfMyCards(): Int = myCards.cards.size

    fun sumOfMyCards(): Int = myCards.calculateOptimalSum()

    fun showMyCards(): String = myCards.toString()

    fun canDraw(): Boolean = myCards.calculateOptimalSum() <= TARGET_SUM

    fun drawCard() {
        if (!canDraw()) {
            throw IllegalStateException("카드 숫자의 합이 21을 초과합니다.")
        }

        val drawnCard = gameCardsSet.drawRandomCard()
        myCards = myCards.add(drawnCard)
    }
}
