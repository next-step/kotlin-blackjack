package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

object Screen {
    fun displayPlayerCards(players: List<Player>, dealer: Dealer) {
        val names = players.joinToString(", ") { player -> player.name }
        println("${dealer.name}와 $names 에게 2장의 카드를 나누었습니다.")

        for (player in listOf(dealer) + players) {
            displayPlayerCard(player)
        }
    }

    fun displayPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.map { card -> card.getName() }.joinToString(",")}")
    }

    fun displayResults(players: List<Player>) {
        players.map { player -> displayResult(player) }
    }

    private fun displayResult(player: Player) {
        val score = player.score()
        println("${player.name}카드: ${player.cards.map { card -> card.getName() }.joinToString(",")} - 결과: $score")
    }
}
