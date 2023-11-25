package blackjack.view

import blackjack.domain.model.*

object ResultView {

    private const val DEALER_STATUS_FORMAT = "%s: %s"
    private const val DEALER_STATUS_RESULT_FORMAT = "%s 카드: %s - 결과: %d"
    private const val GAMBLER_STATUS_FORMAT = "%s카드: %s"
    private const val GAMBLER_STATUS_RESULT_FORMAT = "%s카드: %s - 결과: %d"
    private const val DEAL_CARD_DESCRIPTION = "딜러와 %s에게 %d장의 나누었습니다."
    private const val WIN_DESCRIPTION = "승"
    private const val DRAW_DESCRIPTION = "무"
    private const val LOSE_DESCRIPTION = "패"
    private const val WIN_LOSE_DRAW_DESCRIPTION = "## 최종 $WIN_DESCRIPTION$DRAW_DESCRIPTION$LOSE_DESCRIPTION"
    private const val DEALER_WIN_LOSE_DRAW_DESCRIPTION = "딜러: %d${WIN_DESCRIPTION} %d${DRAW_DESCRIPTION} %d${LOSE_DESCRIPTION}"
    private const val GAMBLER_WIN_LOSE_DRAW_DESCRIPTION = "%s: %s"
    private const val DEAL_CARD_COUNT = 1

    fun drawDealCardDescription(gamblers: Gamblers, dealCardCount: Int = DEAL_CARD_COUNT) {
        val userNameListText = gamblers.joinToString { it.name.name }
        println(DEAL_CARD_DESCRIPTION.format(userNameListText, dealCardCount))
    }

    fun drawGamblersStatus(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            drawGamblerStatus(gambler)
        }
        println()
    }

    fun drawGamblerStatus(gambler: Gambler) {
        println(GAMBLER_STATUS_FORMAT.format(gambler.name, drawCardsStatus(gambler.cards)))
    }

    fun drawDealerStatus(dealer: Dealer) {
        val firstCard = dealer.cards.cards.first()
        println(DEALER_STATUS_FORMAT.format(dealer.name, "${firstCard.sign.sign}${drawPattern(firstCard.pattern)}"))
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
        gamblers.forEach { gambler ->
            drawGamblerStatusResult(gambler, target)
        }
    }

    private fun drawGamblerStatusResult(gambler: Gambler, target: Int) {
        println(
            GAMBLER_STATUS_RESULT_FORMAT.format(
                gambler.name,
                gambler.cards.cards.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" },
                gambler.cards.sum(target).value
            )
        )
    }
    fun drawDealerStatusResult(dealer: Dealer, target: Int) {
        println()
        println(
            DEALER_STATUS_RESULT_FORMAT.format(
                dealer.name,
                dealer.cards.cards.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" },
                dealer.cards.sum(target).value
            )
        )
    }

    fun drawVictoryResult(dealer: Dealer, gamblers: Gamblers) {
        println()
        println(WIN_LOSE_DRAW_DESCRIPTION)

        val dealerWinCount = gamblers.count { gambler -> gambler.winLoseDraw(dealer) == WinLoseDraw.LOSE }
        val dealerDrawCount = gamblers.count { gambler -> gambler.winLoseDraw(dealer) == WinLoseDraw.DRAW }
        val dealerLoseCount = gamblers.count { gambler -> gambler.winLoseDraw(dealer) == WinLoseDraw.WIN }

        println(DEALER_WIN_LOSE_DRAW_DESCRIPTION.format(dealerWinCount, dealerDrawCount, dealerLoseCount))

        gamblers.forEach { gambler ->
            println(GAMBLER_WIN_LOSE_DRAW_DESCRIPTION.format(gambler.name, drawGamblerWinLoseDraw(gambler.winLoseDraw(dealer))))
        }
    }

    private fun drawGamblerWinLoseDraw(winLoseDraw: WinLoseDraw): String {
        return when(winLoseDraw) {
            WinLoseDraw.WIN -> WIN_DESCRIPTION
            WinLoseDraw.DRAW -> DRAW_DESCRIPTION
            WinLoseDraw.LOSE -> LOSE_DESCRIPTION
        }
    }
}
