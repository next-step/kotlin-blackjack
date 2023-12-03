package blackjack.domain

import blackjack.domain.card.CardShuffleMachine
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.participant.forEach
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {
    fun start() {
        val dealer = createDealer()
        val players = InputView.readPlayers(dealer)
        printFirstTurnCards(dealer, players)

        handCardToAllPlayers(dealer, players)
        handCardToDealer(dealer)

        OutputView.printResult(dealer, players)
        val gameResult = players.getGameResult(dealer)
        OutputView.printGameResult(gameResult)
    }

    private fun createDealer(): Dealer {
        val deck = Deck(CardShuffleMachine())
        return Dealer(deck = deck)
    }

    private fun printFirstTurnCards(dealer: Dealer, players: Players) {
        OutputView.drawCardMessage(dealer, players, INIT_DRAW_CARD_COUNT)
        OutputView.playerCardMessage(dealer)
        players.forEach { player ->
            OutputView.playerCardMessage(player)
        }
    }

    private fun handCardToAllPlayers(dealer: Dealer, players: Players) {
        players.forEach { player ->
            handCard(player, dealer)
        }
    }

    private fun handCard(player: Player, dealer: Dealer) {
        if (InputView.readHitOrStay(player).isStay()) {
            return
        }
        if (player.isBust()) {
            OutputView.bustMessage(player)
            return
        }
        val card = dealer.handCard()
        player.receiveCard(card)
        OutputView.playerCardMessage(player)
        handCard(player, dealer)
    }

    private fun handCardToDealer(dealer: Dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerReceiveCardMessage(dealer, Dealer.MIN_HIT_SCORE)
            val card = dealer.handCard()
            dealer.receiveCard(card)
        }
    }

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2
    }
}
