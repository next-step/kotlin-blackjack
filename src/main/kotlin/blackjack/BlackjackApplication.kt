package blackjack

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player
import blackjack.extensions.fromYNToBoolean
import blackjack.presentation.BlackjackGame
import blackjack.service.PlayersParser
import blackjack.view.InputView
import blackjack.view.OutputView

val inputView = InputView()
val outputView = OutputView()
val blackjackGame = BlackjackGame()

fun main() {
    val cardsDeck = CardsDeck()

    val players = blackjackGame.start(
        initPlayers(),
        cardsDeck
    )

    outputView.printStartResult(players)

    val cardReceivedPlayers = divideCards(players, cardsDeck)

    outputView.printResult(cardReceivedPlayers)
}

private fun initPlayers(): List<Player> {
    val parser = PlayersParser()

    val inputPlayersName = inputView.inputPlayersName()
    val playersName = parser.parse(inputPlayersName)
    return playersName.map { playerName -> Player(name = playerName) }
}

private fun divideCards(
    players: List<Player>,
    cardsDeck: CardsDeck,
): List<Player> {
    return players.map { player ->
        var cardReceivedPlayer = player
        var wantMoreCard = true

        while (cardReceivedPlayer.getCardSum() <= 21 && wantMoreCard) {
            wantMoreCard = inputView.inputWantMoreCard(player.name).fromYNToBoolean()

            if (wantMoreCard) {
                cardReceivedPlayer = blackjackGame.addCard(
                    cardReceivedPlayer,
                    cardsDeck
                )
            }

            outputView.printPlayerCard(cardReceivedPlayer)
        }

        cardReceivedPlayer
    }
}
