package step2.blackjack.view

import step2.blackjack.model.Gambler
import step2.blackjack.model.Gamblers


/**
 * 출력 뷰
 * */
object ResultView {

    private const val USER_STATUS_FORMAT = "%s카드: %s"
    private const val USER_STATUS_RESULT_FORMAT = "%s카드: %s - 결과: %d"
    private const val DEAL_CARD_DESCRIPTION = "%s에게 %d장의 나누었습니다."
    private const val DEAL_CARD_COUNT = 1

    fun drawDealCardDescription(gamblers: Gamblers, dealCardCount: Int = DEAL_CARD_COUNT) {
        val userNameListText = gamblers.joinToString { it.name.name }
        println(DEAL_CARD_DESCRIPTION.format(userNameListText, dealCardCount))
    }

    /**
     * 유저 리스트 상태 출력
     * */
    fun drawGamblerStatus(gamblers: Gamblers) {
        gamblers.forEach { user ->
            this.drawGamblerStatus(user)
        }
        println()
    }

    /**
     * 유저 상태 출력
     * */
    private fun drawGamblerStatus(gambler: Gambler) {
        println(USER_STATUS_FORMAT.format(gambler.name, gambler.cards))
    }

    private fun drawCardStatus() {
        TODO("카드 상태 그리기")
    }

    /**
     * 유저 리스트 결과 상태 출력
     * */
    fun drawGamblersStatusResult(gamblers: Gamblers) {
        println()
        gamblers.forEach { gambler ->
            drawUserStatusResult(gambler)
        }
    }

    /**
     * 유저 결과 상태 출력
     * */
    private fun drawUserStatusResult(gambler: Gambler) {
//        println(USER_STATUS_RESULT_FORMAT.format(gambler.name, gambler.cards, gambler.cards.getTargetTotalSum(DEFAULT_AROUND_COUNT)))
    }
}
