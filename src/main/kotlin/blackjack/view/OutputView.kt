package blackjack.view

import blackjack.domain.player.Player

class OutputView {
    fun printStartResult(players: List<Player>) {
        val names = players.joinToString { player -> player.name }
        println("$names 에게 2장의 카드를 나누어주었습니다.")

        players.forEach { player ->
            val name = player.name
            val cardNames = player.cards.joinToString { card ->
                "${card.denomination} ${card.pattern}"
            }

            println("$name 카드: $cardNames")
        }
    }
}
