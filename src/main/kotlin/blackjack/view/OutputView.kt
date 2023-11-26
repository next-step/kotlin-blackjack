package blackjack.view

import blackjack.model.CardHand
import blackjack.model.CardHolder
import blackjack.model.ResultValue
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
            "딜러의 카드: ${
                holder.cardHand.cards[0].getName()
            }, 블라인드"
        )
    }

    fun renderPlayer(holder: CardHolder, printer: (message: Any) -> Unit) {
        printer(
            "${holder.name}카드: ${
                getCardString(holder.cardHand)
            }"
        )
    }

    fun renderResult(holders: List<CardHolder>) {
        holders.forEach {
            renderPlayer(it, ::print)
            println("- 결과: ${it.cardHand.totalScore}")
        }
    }

    fun renderResolved(resolvedResult: Map<Int, ResultValue>) {
        println("## 최종 승패")
        resolvedResult.entries.forEach { (_, value) ->
            if (value.holder.role == Role.DEALER) {
                println("${value.holder.name}: ${value.winLoseDraw.win}승 ${value.winLoseDraw.lose}패 ${value.winLoseDraw.draw}무")
            } else {
                val resultString = when {
                    value.winLoseDraw.win > 0 -> "승"
                    value.winLoseDraw.lose > 0 -> "패"
                    value.winLoseDraw.draw > 0 -> "무"
                    else -> "판정 불가"
                }
                println("${value.holder.name}: $resultString")
            }
        }
    }

    private fun getCardString(cardHand: CardHand): String = cardHand.cards.joinToString(", ") {
        it.getName()
    }
}
