package blackjack

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player
import blackjack.extensions.fromYNToBoolean
import blackjack.presentation.BlackjackGame
import blackjack.util.PlayersParserUtil
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val cardsDeck = CardsDeck()

    val players = BlackjackGame.start(
        initPlayers(),
        cardsDeck
    )

    OutputView.printStartResult(players)

    val cardReceivedPlayers = divideCards(players, cardsDeck)

    OutputView.printResult(cardReceivedPlayers)
}

private fun initPlayers(): List<Player> {
    val inputPlayersName = InputView.inputPlayersName()
    val playersName = PlayersParserUtil.parse(inputPlayersName)

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
            wantMoreCard = InputView.inputWantMoreCard(player.name).fromYNToBoolean()

            if (wantMoreCard) {
                cardReceivedPlayer = BlackjackGame.addCard(
                    cardReceivedPlayer,
                    cardsDeck
                )
            }

            OutputView.printPlayerCard(cardReceivedPlayer)
        }

        cardReceivedPlayer
    }
}
