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

    divideCards(players, cardsDeck)

    OutputView.printResult(players)
}

private fun initPlayers(): List<Player> {
    val inputPlayersName = InputView.inputPlayersName()
    val playersName = PlayersParserUtil.parse(inputPlayersName)

    return playersName.map { playerName -> Player(name = playerName) }
}

private fun divideCards(
    players: List<Player>,
    cardsDeck: CardsDeck,
) {
    players.forEach { player ->
        var wantMoreCard = true

        while (player.getCardSum() <= 21 && wantMoreCard) {
            wantMoreCard = InputView.inputWantMoreCard(player.name).fromYNToBoolean()

            addCardWhenWantMoreCard(wantMoreCard, player, cardsDeck)

            OutputView.printPlayerCard(player)
        }
    }
}

private fun addCardWhenWantMoreCard(
    wantMoreCard: Boolean,
    player: Player,
    cardsDeck: CardsDeck,
): Player {
    if (wantMoreCard) {
        return BlackjackGame.addCard(
            player,
            cardsDeck
        )
    }

    return player
}
