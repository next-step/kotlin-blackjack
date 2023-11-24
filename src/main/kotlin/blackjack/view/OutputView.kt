package blackjack.view

import blackjack.CardHolder
import blackjack.model.CardHand
import blackjack.model.GameResult
import blackjack.model.Player

object OutputView {
    fun renderInitMessage(playerNames: List<String>) {
        println("딜러와 ${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
    }
    fun renderPlayers(holders: List<CardHolder>) {
        holders.forEach {
            renderPlayer(it, ::println)
        }
    }

    fun renderPlayer(holder: CardHolder, printer: (message: Any) -> Unit) {
        printer(
            "${holder.name}카드: ${
                getCardString(holder.cardHand)
            }"
        )
    }

    fun renderResult(results: List<GameResult>) {
        results.forEach {
            renderPlayer(it.cardHolder, ::print)
            println("- 결과: ${it.resultCount}")
        }
    }

    private fun getCardString(cardHand: CardHand): String = cardHand.cards.joinToString(", ") {
        it.getName()
    }
}
