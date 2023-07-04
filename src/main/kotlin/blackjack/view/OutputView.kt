package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

private fun CardNumber.displayName() = when (this) {
    CardNumber.ACE -> "A"
    CardNumber.JACK -> "J"
    CardNumber.QUEEN -> "Q"
    CardNumber.KING -> "K"
    else -> this.cardScore.primary.toString()
}

private fun CardShape.displayName() = when (this) {
    CardShape.CLUB -> "클로버"
    CardShape.DIAMOND -> "다이아"
    CardShape.HEART -> "하트"
    CardShape.SPADE -> "스페이드"
}

object OutputView {
    fun printStart(players: Players) {
        val playerNames = players.joinToString(", ") { it.name }
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        for (player in players) {
            printPlayerCard(player)
        }
    }

    fun printPlayerCard(player: Player) {
        println(resultMessage(player))
    }

    fun printPlayerResult(player: Player) {
        println("${resultMessage(player)}- 결과: ${player.score().value}")
    }

    private fun resultMessage(player: Player): String {
        val cardsMessage = player.cards().joinToString(", ") { it.number.displayName() + it.shape.displayName() }
        return "${player.name}의 카드: $cardsMessage"
    }
}
