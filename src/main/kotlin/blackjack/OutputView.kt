package blackjack

class OutputView {
    fun printInitialPlayersCards(playerCards: Map<String, String>) {
        println("\n${playerCards.keys.map { it }.joinToString(", ")}에게 2장씩 나누었습니다.")
        playerCards.forEach { (playerName, cards) ->
            printSinglePlayerCards(playerName, cards)
        }
        println()
    }

    fun printSinglePlayerCards(
        playerName: String,
        cards: String,
    ) {
        println("${playerName}카드: $cards")
    }

    fun printPlayResult(results: Map<String, Pair<String, Int>>) {
        println()
        results.forEach { (playerName, playerData) ->
            val (cards, total) = playerData
            println("${playerName}카드: $cards - 결과: $total")
        }
    }
}
