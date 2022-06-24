package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

private const val INIT_DRAW_CARD = 2

fun main() {
    val deck = Deck()

    val participants = Participants(
        dealer = Dealer(),
        player = InputView.getPlayerNames().map { Player(it) }
    )

    ResultView.printDistributeCard(INIT_DRAW_CARD, participants)

    participants.participants.forEach {
        InputView.distributeInit(it, INIT_DRAW_CARD) { deck.draw() }
        InputView.printCurrentCard(it)
    }

    participants.player.forEach {
        InputView.distributeToPlayer(it) { deck.draw() }
    }

    InputView.distributeToDealer(participants.dealer) { deck.draw() }

    ResultView.printResult(participants)
}
