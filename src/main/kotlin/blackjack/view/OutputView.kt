package blackjack.view

class OutputView {
    fun printInitialPlayersCards(playerCards: Map<String, String>) {
        println("\n${playerCards.keys.map { it }.joinToString(", ")}에게 2장씩 나누었습니다.")
        playerCards.forEach { (playerName, cards) ->
            printSinglePlayerCards(playerName, cards)
        }
        println()
    }

    fun printPlayerCannotDrawCard(
        playerName: String,
        cards: String,
    ) {
        println("${playerName}는 카드를 더 받을 수 없습니다.")
        println("${playerName}카드: $cards")
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
