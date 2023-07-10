package blackjack.view

import blackjack.domain.player.Player

object PlayerView {

    fun printPlayerCardsView(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }

    fun printPlayerMoreCardView(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }
}
