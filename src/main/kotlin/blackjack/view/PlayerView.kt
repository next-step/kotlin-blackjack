package blackjack.view

import blackjack.domain.player.Player

class PlayerView {
    fun printlnPlayer(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printlnHitYn(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printlnPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cards} - 결과: ${player.score}")
    }
}
