package blackjack.view

import blackjack.domain.state.Hands
import blackjack.domain.state.State

object OutputView {
    fun printInitialCards(players: Map<String, State>) {
        val names = players.keys.joinToString(", ")
        println("\n${names}에게 2장의 카드를 나누었습니다.")
        players.forEach { (name, state) ->
            printPlayerCards(name, state.hands)
        }
        println()
    }

    fun printPlayerCards(
        name: String,
        hands: Hands,
    ) {
        println("${name}카드: ${hands.joinToString(", ")}")
    }

    fun printResults(players: Map<String, State>) {
        println()
        players.forEach { (name, state) ->
            println("${name}카드: ${state.hands.joinToString(", ")} - 결과: ${state.hands.score()}")
        }
    }
}
