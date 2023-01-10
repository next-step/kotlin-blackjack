package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player

object OutputView {
    fun printInitialCards(players: List<Participant>) {
        println("${players.joinToString(", ") { it.name }}에게 2장을 나누어 주었습니다.")
        players.forEach { player ->
            println("${player.name}: ${cards(player)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${cards(player)}")
    }

    fun printDealerCardResult(dealer: Dealer) {
        println("${dealer.name} 는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(players: List<Participant>) {
        println(players.joinToString("\n") { "${it.name}카드: ${cards(it)} - 결과: ${it.getScore()}" })
    }

    private fun cards(player: Participant): String {
        return player.cards.joinToString(", ") { it.toString() }
    }
}
