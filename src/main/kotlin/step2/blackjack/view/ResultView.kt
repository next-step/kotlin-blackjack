package step2.blackjack.view

import step2.blackjack.domain.model.Cards
import step2.blackjack.domain.model.Gambler
import step2.blackjack.domain.model.Gamblers
import step2.blackjack.domain.model.Pattern

object ResultView {

    private const val GAMBLER_STATUS_FORMAT = "%s카드: %s"
    private const val GAMBLER_STATUS_RESULT_FORMAT = "%s카드: %s - 결과: %d"
    private const val DEAL_CARD_DESCRIPTION = "%s에게 %d장의 나누었습니다."
    private const val DEAL_CARD_COUNT = 1

    fun drawDealCardDescription(gamblers: Gamblers, dealCardCount: Int = DEAL_CARD_COUNT) {
        val userNameListText = gamblers.joinToString { it.name.name }
        println(DEAL_CARD_DESCRIPTION.format(userNameListText, dealCardCount))
    }

    fun drawGamblerStatus(gamblers: Gamblers) {
        gamblers.forEach { user ->
            this.drawGamblerStatus(user)
        }
        println()
    }

    fun drawGamblerStatus(gambler: Gambler) {
        println(GAMBLER_STATUS_FORMAT.format(gambler.name, drawCardsStatus(gambler.cards)))
    }

    private fun drawCardsStatus(cards: Cards): String {
        return cards.cards.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" }
    }

    private fun drawPattern(pattern: Pattern): String = when (pattern) {
        Pattern.SPACE -> "스페이스"
        Pattern.CLOVER -> "클로버"
        Pattern.HEART -> "하트"
        Pattern.DIAMOND -> "다이아"
    }

    fun drawGamblersStatusResult(gamblers: Gamblers, target: Int) {
        println()
        gamblers.forEach { gambler ->
            drawUserStatusResult(gambler, target)
        }
    }

    /**
     * 유저 결과 상태 출력
     * */
    private fun drawUserStatusResult(gambler: Gambler, target: Int) {
        println(
            GAMBLER_STATUS_RESULT_FORMAT.format(
                gambler.name,
                gambler.cards.cards.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" },
                gambler.cards.maxTarget(target).toInt()
            )
        )
    }
}
