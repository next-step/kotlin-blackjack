package blackjack.view

import blackjack.domain.Player

object Screen {
    fun displayPlayerCards(players: List<Player>) {
        val names = players.map { player -> player.name }.joinToString(", ")
        println("${names} 에게 2장의 카드를 나누었습니다.")

        for (player in players) {
            displayPlayerCard(player)
        }
    }

    private fun displayPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.map { card -> "${card.point.cardName}${card.shape.shapeName}" }.joinToString(",")}")
    }
}
