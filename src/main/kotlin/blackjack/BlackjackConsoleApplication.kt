package blackjack

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val playerNames = readln()
    val blackjackManager = BlackjackManager()
    blackjackManager.startGame(playerNames.split(",").map { it.trim() })
    println("${blackjackManager.players.joinToString { it.name }}에게 2장의 나누었습니다.")
    blackjackManager.players.forEach {
        println("${it.name}카드: ${it.handToList().joinToString { card -> card.number.symbol + card.suit.korName }}")
    }
    blackjackManager.playGame()
    println()
    blackjackManager.result()
}
