package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

private const val YES = "Y"
private const val NO = "N"

fun main() {
    val players = InputView.inputPlayer().map { name -> Player(name) }
    val dealer = Dealer()
    val allPaticipants = Participants(players + dealer)
    OutputView.printPlayerInfo(allPaticipants)

    for (player in players) {
        doPlayerTurn(player)
    }
    doDealerTurn(dealer)

    OutputView.printPlayersCardList(allPaticipants)
    OutputView.printGameResult(allPaticipants)

    OutputView.printGameWinner(allPaticipants)
}

private fun doPlayerTurn(player: Player) {
    var isKeepPlayerTurn = true
    do {
        isKeepPlayerTurn = selectDrawCard(player)
    } while (isKeepPlayerTurn)
}

private fun doDealerTurn(dealer: Dealer) {
    dealer.drawCard()
}

private fun selectDrawCard(player: Player): Boolean {
    if (!player.checkMyCardsIsOver21()) {
        val answer = InputView.selectCardDraw(player.name)
        return checkPlayerAnswerIsYes(answer, player)
    }
    OutputView.printWhenCardsOver21()
    return false
}

private fun checkPlayerAnswerIsYes(answer: String, player: Player): Boolean {
    if (answer == YES) {
        player.drawCard()
        OutputView.printPlayerCardList(player)
        return true
    }
    return false
}
