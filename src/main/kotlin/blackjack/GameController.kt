package blackjack

import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

private const val YES = "Y"
private const val NO = "N"

fun main() {
    val players = InputView.inputPlayer().map { name -> Player(name) }
    OutputView.printPlayerInfo(players)

    for (player in players) {
        doPlayerTurn(player)
        OutputView.printPlayerCardList(player)
    }

    OutputView.printGameResult(players)
}

private fun doPlayerTurn(player: Player) {
    var answer = ""
    var playerGotWinningCards = false

    do {
        answer = InputView.selectCardDraw(player.name)
        checkAnswerIsYes(answer, player)
        playerGotWinningCards = checkMyCardsIsWinning(player)
    } while (answer != NO && !playerGotWinningCards)
}

private fun checkAnswerIsYes(answer: String, player: Player) {
    if (answer == YES) {
        player.drawCard()
        OutputView.printPlayerCardList(player)
    }
}

private fun checkMyCardsIsWinning(player: Player): Boolean {
    val totalScore = player.calculateMyCards()
    if (totalScore == Cards.WINNING_NUMBER) {
        println("이미 21에 해당하는 카드를 가지고 있기 때문에 턴을 스킵합니다.")
        return true
    }

    return false
}
