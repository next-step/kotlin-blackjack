package blackjack

fun main() {
    val blackjackManager = BlackjackManager()
    blackjackManager.startGame(listOf("jason", "pobi"))
    println("${blackjackManager.players.joinToString { it.name }}에게 2장의 나누었습니다.")
    blackjackManager.players.forEach {
        println("${it.name}카드: ${it.handToList().joinToString { card -> card.number.symbol + card.suit.korName }}")
    }
    blackjackManager.playGame()
    println()
    blackjackManager.result()
}
