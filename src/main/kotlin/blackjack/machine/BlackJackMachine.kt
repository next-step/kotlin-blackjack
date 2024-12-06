package blackjack.machine

import blackjack.dealer.Dealer
import blackjack.deck.Deck
import blackjack.player.Player
import blackjack.player.Players
import blackjack.rule.Rule
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine(
    private val deck: Deck,
) {
    fun play() {
        val playerList =
            Players
                .generateFromNames(InputView.inputPlayerNames())
                .players
                .map { player ->
                    (1..DEFAULT_HAND_SIZE).fold(player) { updatedPlayer, _ ->
                        updatedPlayer.hitCard(deck.draw())
                    }
                }
        var players = Players(players = playerList)
        var dealer = Dealer.ready(initialCards = listOf(deck.draw(), deck.draw()))

        ResultView.printPlayerNamesAndDealer(players = players, dealer = dealer)
        ResultView.printPlayersCardStatus(players = players, dealer = dealer)

        while (isGameActive(players = players)) {
            players = Players(players = players.players.map { playTurn(it) })
            dealer = playDealerTurn(dealer)
            ResultView.printPlayersCardStatusAndSum(players = players, dealer = dealer)
        }
    }

    private fun playTurn(player: Player): Player =
        when {
            player.isBust() -> player
            !InputView.isHitCard(player) ->
                player
                    .also { ResultView.printPlayerCard(it) }
            else ->
                player
                    .hitCard(deck.draw())
                    .also { ResultView.printPlayerCard(it) }
        }

    private fun playDealerTurn(dealer: Dealer): Dealer =
        if (dealer.isDraw()) {
            dealer
                .hitCard(deck.draw())
                .also { ResultView.printDealerDrawCard() }
        } else {
            dealer
        }

    private fun isGameActive(players: Players) = players.players.any { !it.isBust() }

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
    }
}
