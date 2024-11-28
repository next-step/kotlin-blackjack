package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

object BlackJackGame {
    fun start() {
        val dealer = Dealer()
        val names = InputView.getPlayerNames()
        val players = names.map { Player(it) }
        val participants = listOf(dealer) + players
        val deck = Deck()

        dealCards(participants, deck)
        ResultView.printFirstPhase(participants)

        drawPlayerCards(players, deck)
        drawDealerCards(dealer, deck)

        ResultView.printFinalResult(dealer, players)
    }

    private fun drawPlayerCards(players: List<Player>, deck: Deck) {
        players.forEach { player -> requestDrawCards(player, deck) }
    }

    private fun drawDealerCards(dealer: Dealer, deck: Deck) {
        while (dealer.canDrawCard()) {
            ResultView.printDealerDrawMessage(dealer)
            dealer.receiveCard(deck.drawCard())
        }
    }

    private fun dealCards(participants: List<Participant>, deck: Deck) {
        participants.forEach { participant ->
            participant.receiveCard(deck.drawCard())
            participant.receiveCard(deck.drawCard())
        }
    }

    private fun requestDrawCards(player: Player, deck: Deck) {
        while (player.canDrawCard()) {
            val request = InputView.requestCard(player.name)
            if (!request) {
                break
            }
            player.receiveCard(deck.drawCard())
            ResultView.printParticipantsCards(player)
        }
    }
}
