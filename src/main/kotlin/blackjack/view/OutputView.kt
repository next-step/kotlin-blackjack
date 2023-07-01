package blackjack.view

import blackjack.domain.Player

class OutputView {

    fun printDefaultReceivedCards(players: List<Player>) {
        println("\n${players.joinToString(", ") { it.name }} 에게 2장의 카드를 나누었습니다.")
        players.forEach { player -> printlnPlayerCards(player) }
        println()
    }

    fun printlnPlayerCards(player: Player) {
        printPlayerCards(player)
        println()
    }

    fun printGameResult(scoreMap: Map<Player, Int>) {
        println()
        scoreMap.forEach { (key, value) ->
            printPlayerCards(key)
            println("- 결과: $value")
        }
    }

    private fun printPlayerCards(player: Player) {
        print("${player.name}카드: ")
        print(player.cards.values.joinToString(", ") { card ->
            card.rank.symbol + card.suit.value
        })
    }
}