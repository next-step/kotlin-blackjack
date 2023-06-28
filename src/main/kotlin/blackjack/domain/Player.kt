package blackjack.domain

import blackjack.domain.Cards.Companion.BLACK_JACK_SCORE

open class Player(
    val name: String,
    private val gameCardsSet: GameCardsSet,
) {
    private var myCards: Cards = Cards.empty()
    private var state: PlayerState = PlayerState.DEFAULT

    fun getMyCards(): Cards = myCards.copy()

    fun numberOfMyCards(): Int = myCards.cards.size

    fun sumOfMyCards(): Int = myCards.calculateOptimalSum()

    fun canDraw(): Boolean {
        val optimalSum = myCards.calculateOptimalSum()

        if (optimalSum == BLACK_JACK_SCORE) {
            state = PlayerState.BLACK_JACK
            return false
        }

        if (optimalSum > BLACK_JACK_SCORE) {
            state = PlayerState.BUST
            return false
        }
        return true
    }

    fun wantStand() {
        state = PlayerState.STAND
    }

    fun findStateBySum(): PlayerState {
        if (sumOfMyCards() == BLACK_JACK_SCORE) {
            state = PlayerState.BLACK_JACK
        }

        if (sumOfMyCards() < BLACK_JACK_SCORE) {
            state = PlayerState.STAND
        }

        if (sumOfMyCards() > BLACK_JACK_SCORE) {
            state = PlayerState.BUST
        }

        return state
    }

    fun drawCard() {
        if (!canDraw()) {
            throw IllegalStateException("카드 숫자의 합이 21을 초과합니다.")
        }

        val drawnCard = gameCardsSet.drawRandomCard()
        myCards = myCards.add(drawnCard)
        state = PlayerState.HIT
    }
}
