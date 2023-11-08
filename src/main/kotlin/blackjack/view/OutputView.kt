package blackjack.view

object OutputView {

    fun printInitCard(playerNames: List<String>) {
        println("${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
    }

    fun printPlayerCard(playerName: String, cards: List<String>) {
        println("${playerName}카드: ${cards.joinToString(", ")}")
    }

    fun printPlayerResult(playerName: String, cards: List<String>, score: Int) {
        println("${playerName}카드: ${cards.joinToString(", ")} - 결과: $score")
    }
}
