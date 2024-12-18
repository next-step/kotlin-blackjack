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

    fun printDealerExtraDraw() {
        println("딜러는 16 이하라 한장의 카드를 더 받았습니다.")
    }

    fun printResult(result: GameResult) {
        val (players, dealer) = result

        println("${dealer.name}카드: ${dealer.cards} - 결과: ${dealer.score}")
        players.forEach { player ->
            println("${player.name}카드: ${player.cards} - 결과: ${player.score}")
        }

        println("## 최종 수익")
        println("${dealer.name}: ${dealer.profit}")
        players.forEach { player ->
            println("${player.name}: ${player.profit}")
        }
    }
}
