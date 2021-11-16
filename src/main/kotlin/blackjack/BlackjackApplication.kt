package blackjack

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
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

    divideCards(players.dealer, players.guest, cardsDeck)

    OutputView.printDivideResult(players)

    BlackjackGame.match(players)

    OutputView.printMatchResult(players)
}

private fun initPlayers(): List<Player> {
    val inputPlayersName = InputView.inputPlayersName()
    val playersName = PlayersParserUtil.parse(inputPlayersName)

    return playersName.map { playerName -> Player(name = playerName) }
}

private fun divideCards(
    dealer: Dealer,
    players: List<Player>,
    cardsDeck: CardsDeck,
) {
    players.forEach { player ->
        var wantMoreCard = true

        while (player.isCardReceiveAble() && wantMoreCard) {
            wantMoreCard = InputView.inputWantMoreCard(player.name).fromYNToBoolean()

            addCardWhenWantMoreCard(wantMoreCard, player, cardsDeck)

            OutputView.printPlayerCard(player.player)
        }
    }

    dealer
        .addCardWhenLessThanStandard(cardsDeck)
        ?.let {
            OutputView.printDealerReceivedCard()
        }
}

private fun addCardWhenWantMoreCard(
    wantMoreCard: Boolean,
    player: Player,
    cardsDeck: CardsDeck,
): Participant {
    if (wantMoreCard) {
        return BlackjackGame.addCard(
            player.player,
            cardsDeck
        )
    }

    return player.player
}
