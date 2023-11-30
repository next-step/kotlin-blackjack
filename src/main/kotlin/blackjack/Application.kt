package blackjack

import blackjack.domain.game.BlackJackGame
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerGroup
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val blackJackGame = BlackJackGame()
    var players = createPlayers()

    players = blackJackGame.dealCardToEveryOne(players)
    ResultView.printStartMessage(players)

    for (player in players) {
        players = askNeedMoreCard(player, players, blackJackGame)
    }

    val blackJackGameResult = blackJackGame.end(players)
    ResultView.printGameResult(blackJackGameResult)
}

private fun askNeedMoreCard(
    player: Player,
    players: PlayerGroup,
    blackJackGame: BlackJackGame
): PlayerGroup {
    var playerGroupWithNewCard = players
    var playerWithNewCard = player
    while (InputView.askNeedCard(playerWithNewCard.name)) {
        playerGroupWithNewCard = blackJackGame.dealCardTo(playerGroupWithNewCard, playerWithNewCard)
        playerWithNewCard = playerGroupWithNewCard.firstOrNull { it.name == playerWithNewCard.name } ?: throw IllegalArgumentException()
        ResultView.printPlayerState(playerWithNewCard)
    }
    return playerGroupWithNewCard
}

private fun createPlayers(): PlayerGroup {
    return PlayerGroup(InputView.readPlayerNames().map {
        Player(it)
    })
}
