package blackjack.view

import blackjack.domain.HardAcePointStrategy
import blackjack.domain.Player

object OutputView {
    fun printCards(player: Player) {
        println("${player.name}카드: ${player.cards.value.joinToString { "${it.rank.value}${it.suit.value}" }}")
    }

    fun printResult(player: Player) {
        println(
            "${player.name}카드: ${player.cards.value.joinToString { "${it.rank.value}${it.suit.value}" }} - 결과: ${
                player.cards.getPoints()
            }"
        )
    }
}
