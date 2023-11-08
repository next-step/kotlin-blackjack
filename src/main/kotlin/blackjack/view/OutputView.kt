package blackjack.view

import blackjack.domain.Player

object OutputView {

    fun printInitCard(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
    }

    fun printPlayersCard(players: List<Player>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.playerCard.cards.joinToString(", ")}")
    }

    fun printPlayerResult(player: List<Player>) {
        player.forEach {
            println("${it.name}카드: ${it.playerCard.cards.joinToString(", ")} - 결과: ${it.score()}")
        }

    }
}
