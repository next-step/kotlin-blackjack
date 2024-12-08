package blackjack.presentation

import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Participant

class OutputView {
    fun printInitialCards(players: List<Participant>) {
        println("${players.joinToString(transform = Participant::name)}에게 2장의 카드를 나누었습니다.")

        players.find { it is Dealer }?.let { dealer ->
            println("${dealer.name}카드: ${dealer.cards}")
        }
        players.filterNot { it is Dealer }.forEach { player ->
            println("${player.name}카드: ${player.cards}")
        }
    }

    fun printPlayerCards(player: Participant) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printResult(result: GameResult) {
        val (players, dealer) = result

        players.forEach { player ->
            println("${player.name}카드: ${player.cards} - 결과: ${player.score}")
        }

        println("## 최종 승패")
        println("${dealer.name}: ${result.dealerWins}승 ${result.dealerLosses}패")
        players.forEach { player ->
            println("${player.name}: ${result.getResult(player).message}")
        }
    }
}
