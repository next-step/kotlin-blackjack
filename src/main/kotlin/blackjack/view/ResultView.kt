package blackjack.view

import blackjack.domain.model.Cards
import blackjack.domain.model.player.Dealer
import blackjack.domain.model.player.Gambler
import blackjack.domain.model.Gamblers
import blackjack.domain.model.Pattern
import blackjack.domain.model.WinDrawLose
import blackjack.domain.model.player.AbstractPlayer

object ResultView {

    private const val DEALER_STATUS_FORMAT = "%s: %s"
    private const val DEALER_STATUS_RESULT_FORMAT = "%s 카드: %s - 결과: %d"
    private const val DEALER_DRAW_DESCRIPTION = "%s는 %d이하라 한장의 카드를 더 받았습니다."

    private const val GAMBLER_STATUS_FORMAT = "%s카드: %s"
    private const val GAMBLER_STATUS_RESULT_FORMAT = "%s카드: %s - 결과: %d"

    private const val DEAL_CARD_DESCRIPTION = "%s와 %s에게 %d장의 나누었습니다."

    private const val WIN_DESCRIPTION = "승"
    private const val DRAW_DESCRIPTION = "무"
    private const val LOSE_DESCRIPTION = "패"

    private const val WIN_LOSE_DRAW_DESCRIPTION = "## 최종 $WIN_DESCRIPTION$DRAW_DESCRIPTION$LOSE_DESCRIPTION"
    private const val DEALER_WIN_DRAW_LOSE_DESCRIPTION = "딜러: %d$WIN_DESCRIPTION %d$DRAW_DESCRIPTION %d$LOSE_DESCRIPTION"
    private const val GAMBLER_WIN_DRAW_LOSE_DESCRIPTION = "%s: %s"

    fun drawFirstDealCard(dealer: Dealer, gamblers: Gamblers, dealCardCount: Int) {
        val gamblerNames = gamblers.joinToString { it.name.value }
        println(DEAL_CARD_DESCRIPTION.format(dealer.name.value, gamblerNames, dealCardCount))

        drawPlayerFirstStatus(dealer)
        gamblers.forEach { gambler ->
            drawGamblerStatus(gambler)
        }
        println()
    }

    private fun drawPlayerFirstStatus(player: AbstractPlayer) {
        println(DEALER_STATUS_FORMAT.format(player.name, drawCardsStatus(player.getFirstOpenedCards())))
    }

    fun drawGamblerStatus(gambler: Gambler) {
        println(GAMBLER_STATUS_FORMAT.format(gambler.name, drawCardsStatus(gambler.cards)))
    }

    private fun drawCardsStatus(cards: Cards): String {
        return cards.values.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" }
    }

    private fun drawPattern(pattern: Pattern): String = when (pattern) {
        Pattern.SPACE -> "스페이스"
        Pattern.CLOVER -> "클로버"
        Pattern.HEART -> "하트"
        Pattern.DIAMOND -> "다이아"
    }

    fun drawHitDealer(dealer: Dealer) {
        println()
        println(DEALER_DRAW_DESCRIPTION.format(dealer.name.value, Dealer.MAX_HIT_NUMBER))
    }

    fun drawGameResult(dealer: Dealer, gamblers: Gamblers) {
        drawDealerStatusResult(dealer)
        drawGamblersStatusResult(gamblers)
        drawVictoryResult(dealer, gamblers)
    }

    private fun drawDealerStatusResult(dealer: Dealer) {
        println()
        println(
            DEALER_STATUS_RESULT_FORMAT.format(
                dealer.name,
                dealer.cards.values.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" },
                dealer.cards.score().value
            )
        )
    }

    private fun drawGamblersStatusResult(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            drawGamblerStatusResult(gambler)
        }
    }

    private fun drawGamblerStatusResult(gambler: Gambler) {
        println(
            GAMBLER_STATUS_RESULT_FORMAT.format(
                gambler.name,
                gambler.cards.values.joinToString { card -> "${card.sign.sign}${drawPattern(card.pattern)}" },
                gambler.cards.score().value
            )
        )
    }

    private fun drawVictoryResult(dealer: Dealer, gamblers: Gamblers) {
        println("\n$WIN_LOSE_DRAW_DESCRIPTION")

        val gamblersWinDrawLoseCount = gamblers.winDrawLoseCount(dealer)
        println(DEALER_WIN_DRAW_LOSE_DESCRIPTION.format(gamblersWinDrawLoseCount.loseCount, gamblersWinDrawLoseCount.drawCount, gamblersWinDrawLoseCount.winCount))

        gamblers.forEach { gambler ->
            println(
                GAMBLER_WIN_DRAW_LOSE_DESCRIPTION.format(
                    gambler.name, drawGamblerWinDrawLose(gambler.winDrawLose(dealer))
                )
            )
        }
    }

    private fun drawGamblerWinDrawLose(winDrawLose: WinDrawLose): String {
        return when (winDrawLose) {
            WinDrawLose.WIN -> WIN_DESCRIPTION
            WinDrawLose.DRAW -> DRAW_DESCRIPTION
            WinDrawLose.LOSE -> LOSE_DESCRIPTION
        }
    }
}
