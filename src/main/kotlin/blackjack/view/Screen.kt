package blackjack.view

import blackjack.domain.Player
import blackjack.domain.enums.CardPoint

object Screen {
    fun displayPlayerCards(players: List<Player>) {
        val names = players.map { player -> player.name }.joinToString(", ")
        println("$names 에게 2장의 카드를 나누었습니다.")

        for (player in players) {
            displayPlayerCard(player)
        }
    }

    fun displayPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.map { card -> "${card.point.cardName}${card.shape.shapeName}" }.joinToString(",")}")
    }

    fun displayResults(players: List<Player>) {
        players.map { player -> displayResult(player) }
    }

    private fun displayResult(player: Player) {
        val score = player.cards.map { card -> CardPoint.point(card) }.sum()
        println("${player.name}카드: ${player.cards.map { card -> "${card.point.cardName}${card.shape.shapeName}" }.joinToString(",")} - 결과: $score")
    }
}
