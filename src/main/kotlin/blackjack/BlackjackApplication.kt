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
    val dealer = BlackjackGame.start(
        listOf(Dealer()),
        cardsDeck
    )

    OutputView.printStartResult(players)

    divideCards(players.dealer, players.players, cardsDeck)

    OutputView.printResult(players)
}

private fun initPlayers(): List<Player> {
    val inputPlayersName = InputView.inputPlayersName()
    val playersName = PlayersParserUtil.parse(inputPlayersName)

    return playersName.map { playerName -> Player(name = playerName) }
}

private fun divideCards(
    dealer: Dealer,
    players: List<Participant>,
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

    dealer
        .addCardWhenLessThanStandard(cardsDeck)
        ?.let {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }
}

private fun addCardWhenWantMoreCard(
    wantMoreCard: Boolean,
    player: Participant,
    cardsDeck: CardsDeck,
): Participant {
    if (wantMoreCard) {
        return BlackjackGame.addCard(
            player,
            cardsDeck
        )
    }

    return player
}
