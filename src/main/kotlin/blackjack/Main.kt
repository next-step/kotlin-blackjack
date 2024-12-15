package blackjack

import blackjack.infrastructure.RandomDeck
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerCommands

fun main() {
    // 1. 유저명 입력 및 유저 생성
    val deck = RandomDeck()
    val userNames = InputView.getUserNames()
    val game = BlackJackGame.createGame(userNames, deck)

    OutputView.printCurrentStatus(game)

    // 2-1. 블랙잭 여부 확인
    if (game.checkBlackJack()) {
        OutputView.printGameResult(game)
    } else {
        // 2-2. 유저별 카드 선택 및 출력
        playBlackJack(game)
    }

    // 3. 결과 출력
    OutputView.printGameResult(game)
}

private fun playBlackJack(game: BlackJackGame) {
    while (game.isPlaying) {
        game.checkCurrentPlayerStatusAndChange()
        handlePlayerCommand(game)
    }
    val dealerChance = game.handleDealerChance()
    OutputView.printDealerChance(dealerChance)
}

private fun handlePlayerCommand(game: BlackJackGame) {
    val player = game.currentPlayer
    val command = InputView.getUserCommand(player)
    when (command) {
        PlayerCommands.HIT -> game.hit(player)
        PlayerCommands.STAY -> game.stay(player)
    }
    OutputView.printPlayerCards(player)
}
