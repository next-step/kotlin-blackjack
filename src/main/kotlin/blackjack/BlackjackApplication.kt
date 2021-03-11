package blackjack

import blackjack.domain.deck.Deck
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerResponseDto

fun main() {
    val deck = Deck.createDeck()
    val playersNames = InputView.inputPlayerNames()
    val players = playersNames.map { Player(it, deck) }
    OutputView.showStartStatus(players.map { PlayerResponseDto(it) })
    for (player in players) {
        doGame(player, deck)
    }
    OutputView.showResult(players.map { PlayerResponseDto(it) })
}

fun doGame(player: Player, deck: Deck) {
    while (!player.isFinished()) {
        val isDraw = InputView.additionalDraw(player.name)
        player.action(isDraw, deck)
        showStatusIfIsDraw(player, isDraw)
    }
}

fun showStatusIfIsDraw(player: Player, isDraw: Boolean) {
    if (isDraw) {
        OutputView.showPlayerStatus(PlayerResponseDto(player))
    }
}
