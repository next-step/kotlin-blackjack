package blackjack.view

import blackjack.domain.player.Player

class PlayerView {
    fun printlnPlayer(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printlnPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cards} - 결과: ${player.score()}")
    }
}
