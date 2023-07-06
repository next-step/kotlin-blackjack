package blackjack

import blackjack.controller.BlackjackController

fun main() {
    val blackjackController = BlackjackController()
    val players = blackjackController.inputPlayers()

    blackjackController.drawInitialCards(players)
    println()
    blackjackController.printInitialCards(players)
    println()

    var playerIterator = players.iterator()
    var currentPlayer = playerIterator.next()
    val isPlaying = true
    while (isPlaying) {
        if (blackjackController.drawMoreCard(currentPlayer)) {
            continue
        }
        // 다음 플레이어가 있으면 다음 플레이어로 턴을 넘긴다
        if (playerIterator.hasNext()) {
            currentPlayer = playerIterator.next()
            continue
        }
        // 다음 플레이어가 없으면 게임을 종료한다
        break
    }
    println()

    // 결과를 출력한다.
    blackjackController.printResult(players)
}
