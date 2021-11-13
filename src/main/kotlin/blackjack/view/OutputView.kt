package blackjack.view

import blackjack.domain.player.Player

class OutputView {
    fun printStartResult(players: List<Player>) {
        val names = players.joinToString { player -> player.name }
        println("$names 에게 2장의 카드를 나누어주었습니다.")

        players.forEach { player ->
            printPlayerCard(player)
        }
    }

    fun printPlayerCard(player: Player) {
        val name = player.name
        val cardNames = player.cards.map { card ->
            "${card.denomination.denomination} ${card.pattern}"
        }

        println("$name 카드: $cardNames")
    }

    fun printResult(players: List<Player>) {
        players.forEach { player ->
            val cardNames = player.cards.map { card ->
                "${card.denomination.denomination} ${card.pattern}"
            }

            println("${player.name}카드 $cardNames 합계: ${player.getCardSum()}")
        }
    }
}
