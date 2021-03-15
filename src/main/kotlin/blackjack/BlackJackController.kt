package blackjack

import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

private val YES = "Y"
private val NO = "N"
private val inputView = InputView()
private val outputView = OutputView()

fun main() {
    // 게임 인원을 입력받는다.
    val players = inputView.inputPlayer().map { name ->
        Player(name)
    }
    // 초기에 지급받은 카드를 표시해준다.
    for (player in players) {
        outputView.printPlayerCardList(player)
    }

    // 게임을 시작한다.
    for (player in players) {
        doPlayerTurn(player)
        outputView.printPlayerCardList(player)
    }

    // 게임의 결과 출력
    for (player in players) {
        outputView.printGameResult(player)
    }
}

private fun doPlayerTurn(player: Player) {
    var answer = ""
    while (answer != NO) {
        answer = inputView.selectCardDraw(player.name)
        checkAnswerIsYes(answer, player)
    }
}

private fun checkAnswerIsYes(answer: String, player: Player) {
    if (answer == YES) {
        player.drawCard()
        outputView.printPlayerCardList(player)
    }
}
