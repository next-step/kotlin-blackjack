package blackjack

import blackjack.domain.Cards
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
    var answer = ""
    var playerGotWinningCards = false

    do {
        answer = InputView.selectCardDraw(player.name)
        checkAnswerIsYes(answer, player)
        playerGotWinningCards = checkMyCardsIsWinning(player)
    } while (answer != NO && !playerGotWinningCards)
}

private fun doDealerTurn(dealer: Dealer) {
    dealer.drawCard()
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
