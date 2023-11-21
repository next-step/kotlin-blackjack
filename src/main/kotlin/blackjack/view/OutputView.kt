package blackjack.view

import blackjack.domain.Player

object OutputView {
    fun printCards(player: Player) {
        println("${player.name}카드: ${player.cards.value.joinToString { "${it.rank.nameValue}${it.suit.value}" }}")
    }

    fun printResult(player: Player) {
        println(
            "${player.name}카드: ${player.cards.value.joinToString { "${it.rank.nameValue}${it.suit.value}" }} - 결과: ${
                player.cards.getPoints()
            }"
        )
    }
}
