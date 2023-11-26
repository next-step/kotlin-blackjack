package blackjack.view

import blackjack.model.CardHand
import blackjack.model.CardHolder
import blackjack.model.Role

object OutputView {
    fun renderInitMessage(playerNames: List<String>) {
        println("딜러와 ${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
    }

    fun renderPlayers(holders: List<CardHolder>) {
        holders.forEach {
            renderPlayer(it, ::println)
        }
    }

    fun renderFirstDealerHand(holder: CardHolder, printer: (message: Any) -> Unit) {
        printer(
            "딜러의 카드: ${holder.cardHand.cards[0].getName()}, 블라인드"
        )
    }

    fun renderPlayer(holder: CardHolder, printer: (message: Any) -> Unit) {
        printer(
            "${holder.name}카드: ${getCardString(holder.cardHand)}"
        )
    }

    fun renderResult(holders: List<CardHolder>) {
        holders.forEach {
            renderPlayer(it, ::print)
            println("- 결과: ${it.cardHand.totalScore} ${if (it.isBust) "(버스트)" else ""}")
        }
        println("## 최종 승패")
        holders.forEach {
            renderResolved(it)
        }
    }

    private fun renderResolved(it: CardHolder) {
        if (it.role == Role.DEALER) {
            println("딜러: ${it.winLoseDraw.win}승 ${it.winLoseDraw.lose}패 ${it.winLoseDraw.draw}무")
        } else {
            val resultString = when {
                it.winLoseDraw.win > 0 -> "승"
                it.winLoseDraw.lose > 0 -> "패"
                it.winLoseDraw.draw > 0 -> "무"
                else -> "판정 불가"
            }
            println("${it.name}: $resultString")
        }
    }

    private fun getCardString(cardHand: CardHand): String = cardHand.cards.joinToString(", ") {
        it.getName()
    }
}
