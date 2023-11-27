package blackjack.domain

import blackjack.domain.BlackjackUtil.computeScore
import blackjack.domain.BlackjackUtil.isBust
import blackjack.domain.BlackjackUtil.winAgainstDealer
import blackjack.domain.Dealer.Companion.INITIAL_CARD_NUM

open class Player(val name: String) {
    val hand = Hand()
    private var finalScore: Int = -1
    lateinit var result: Result
        private set

    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
    }

    open fun initialCards(): List<Card> {
        return hand.toList().take(INITIAL_CARD_NUM)
    }

    open fun canDrawMore(): Boolean {
        return !isBust(hand.sumOf())
    }

    fun setFinalScore() {
        finalScore = computeScore(hand).second
    }

    fun getFinalScore(): Int {
        check(finalScore > 0) { "최종 점수가 계산되지 않았습니다." }
        return finalScore
    }

    fun setResult(dealerScore: Int) {
        check(finalScore > 0) { "최종 점수가 계산되지 않았습니다." }
        require(dealerScore > 0) { "딜러의 최종 점수가 계산되지 않았습니다." }

        result = Result.of(winAgainstDealer(finalScore, dealerScore))
    }
}
