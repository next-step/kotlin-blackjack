package blackjack.presentation

import blackjack.domain.Player

class OutputView {
    fun printInitialCards(players: List<Player>) {
        println("${players.joinToString(transform = Player::name)}에게 2장의 카드를 나누었습니다.")
        players.forEach { player ->
            println("${player.name}카드: ${player.hands}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.hands}")
    }

    fun printResult(players: List<Player>) {
        players.forEach { player ->
            println("${player.name}카드: ${player.hands} - 결과: ${player.score}")
        }
    }
}
