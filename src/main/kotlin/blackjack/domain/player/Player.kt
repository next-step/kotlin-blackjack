package blackjack.domain

import blackjack.domain.game.EarningsRateCase
import blackjack.domain.game.GameResultType
import blackjack.domain.state.Begin
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Burst
import blackjack.domain.state.Hit
import blackjack.domain.state.State

/**
 * ### 블랙잭을 플레이하는 사람을 표현하는 객체 입니다.
 */
sealed class Player {
    abstract val name: String
    abstract val canHit: Boolean

    protected var state: State = Begin()

    fun receive(card: Card) {
        check(canHit) { "Can not hit anymore" }
        state = state.draw(card)
    }

    fun initializeHands(card1: Card, card2: Card) {
        val begin = state as? Begin ?: throw IllegalStateException("Player state is not Begin")
        state = begin.drawInitialHands(card1, card2)
    }

    fun currentDeck(): Deck {
        return state.currentDeck()
    }

    fun stay() {
        state = state.stay()
    }

    fun score(): Score {
        return state.score()
    }

    fun isBlackjack(): Boolean {
        return state is Blackjack
    }

    fun isBurst(): Boolean {
        return state is Burst
    }
}

/**
 * ### 블랙잭 플레이어 중 딜리를 표현하는 객체입니다
 */
data class Dealer(
    override val name: String = "Dealer",
) : Player() {
    override val canHit: Boolean
        get() = state is Hit && score().isLessThanEqualToDealerHitThreshold
}

/**
 * ### 블랙잭 플레이어 중 딜러가 아닌 도전자를 표현하는 객체입니다
 */
data class Challenger(
    override val name: String,
    val bettingAmount: Int = 0
) : Player() {

    override val canHit: Boolean
        get() = state is Hit

    fun isWin(dealer: Dealer): Boolean {
        return GameResultType.of(this.score(), dealer.score()) == GameResultType.CHALLENGER_WIN
    }

    fun getEarnings(dealer: Dealer): Int {
        val gameResultType = GameResultType.of(this.score(), dealer.score())
        val earningsRate = EarningsRateCase.of(gameResultType, this.isBlackjack(), dealer.isBlackjack()).earningsRate
        return (earningsRate * bettingAmount).toInt()
    }
}
