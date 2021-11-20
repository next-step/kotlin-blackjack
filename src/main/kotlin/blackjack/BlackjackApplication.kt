package blackjack

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.extensions.fromYNToBoolean
import blackjack.presentation.BlackjackGame
import blackjack.service.DetermineMatch
import blackjack.util.PlayersParserUtil
import blackjack.view.InputView
import blackjack.view.OutputView

private val blackjackGame = BlackjackGame(DetermineMatch())

fun main() {
    val cardsDeck = CardsDeck()

    val participants = blackjackGame.start(
        initPlayers(),
        cardsDeck
    )

    OutputView.printStartResult(participants)

    val existsBlackjack = blackjackGame.existsBlackjack(participants)

    if (existsBlackjack) {
        blackjackGame.matchWhenFirstCardBlackjack(participants)
    } else {
        divideCards(participants.dealer, participants.players, cardsDeck)
        OutputView.printDivideResult(participants)

        blackjackGame.match(participants)
    }

    OutputView.printMatchResult(participants)
}

private fun initPlayers(): List<Player> {
    val inputPlayersName = InputView.inputPlayersName()
    val playersName = PlayersParserUtil.parse(inputPlayersName)

    return playersName.map { playerName ->
        val betAmount = InputView.inputBetAmount(playerName)

        Player(
            player = Participant(playerName),
            betAmount = betAmount,
        )
    }
}

private fun divideCards(
    dealer: Dealer,
    players: List<Player>,
    cardsDeck: CardsDeck,
) {
    players.forEach { player ->
        var wantMoreCard = true

        while (player.isCardReceiveAble() && wantMoreCard) {
            wantMoreCard = InputView.inputWantMoreCard(player.player.name).fromYNToBoolean()

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
        return blackjackGame.addCard(
            player.player,
            cardsDeck
        )
    }

    return player.player
}
