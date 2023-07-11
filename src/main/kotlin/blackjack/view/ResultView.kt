package blackjack.view

import blackjack.domain.CardType
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {
    fun printPlayers(players: Players) {
        val playersName = players.list.joinToString(", ") { it.name }

        println("${playersName}에게 2장의 카드를 나누어주었습니다.")

        players.list.forEach { player ->
            printPlayer(player)
        }
    }

    fun printPlayer(player: Player) {
        println("${player.name}카드: ${playerCards(player)}")
    }

    fun printResult(players: Players) {
        println()
        players.forEach { player ->
            println("${player.name}카드: ${playerCards(player)} - 결과: ${player.score()}")
        }
    }

    private fun playerCards(player: Player) =
        player.cards.cards.joinToString(", ") { it -> "${it.cardType.display()}${it.denomination.display()}" }
}

private fun CardType.display() = when (this) {
    CardType.CLOVER -> "클로버"
    CardType.DIAMOND -> "다이아"
    CardType.HEART -> "하트"
    CardType.SPADE -> "스페이드"
}

private fun Denomination.display() = when (this) {
    Denomination.ACE -> "A"
    Denomination.KING -> "K"
    Denomination.QUEEN -> "Q"
    Denomination.JACK -> "J"
    else -> this.score.toString()
}
