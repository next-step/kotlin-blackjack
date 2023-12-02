package blackjack

import blackjack.domain.dealer.Dealer
import blackjack.domain.Deck
import blackjack.domain.card.CardShuffleMachine
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.forEach
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication {

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2

        @JvmStatic
        fun main(args: Array<String>) {
            val players = InputView.readPlayers()
            val dealer = createDealer()
            OutputView.drawCardMessage(dealer, players, INIT_DRAW_CARD_COUNT)
            drawCardFirst(players, dealer)
            printFirstPlayerCardMessage(players)
            handCardToAllPlayers(players, dealer)
            OutputView.printResult(players)
        }

        private fun createDealer(): Dealer {
            val deck = Deck(CardShuffleMachine())
            return Dealer(deck)
        }

        private fun drawCardFirst(players: Players, dealer: Dealer) {
            repeat(INIT_DRAW_CARD_COUNT) {
                drawCardToAllPlayers(players, dealer)
            }
        }

        private fun drawCardToAllPlayers(players: Players, dealer: Dealer) {
            players.forEach { player ->
                dealer.handCard(player)
            }
        }

        private fun printFirstPlayerCardMessage(players: Players) {
            players.forEach { player ->
                OutputView.playerCardMessage(player)
            }
        }

        private fun handCardToAllPlayers(players: Players, dealer: Dealer) {
            players.forEach { player ->
                handCard(player, dealer)
            }
        }

        private fun handCard(player: Player, dealer: Dealer) {
            if (InputView.readDrawMoreCard(player).isNo()) {
                return
            }
            if (player.isBust()) {
                OutputView.bustMessage(player)
                return
            }
            dealer.handCard(player)
            OutputView.playerCardMessage(player)
            handCard(player, dealer)
        }
    }
}
