package blackjack.machine

import betting.board.BettingBoard
import blackjack.dealer.Dealer
import blackjack.deck.Deck
import blackjack.participant.createParticipants
import blackjack.player.Player
import blackjack.player.Players
import blackjack.rule.Rule
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine(
    private val deck: Deck,
    private val bettingBoard: BettingBoard = BettingBoard(),
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

        bettingBoard.setup(
            playerBets = players.players.associateWith { InputView.inputBettingAmount(it) },
            dealer = dealer,
        )

        ResultView.printPlayerNamesAndDealer(players = players, dealer = dealer)
        ResultView.printPlayersCardStatus(participants = createParticipants(dealer = dealer, players = players))

        while (Rule.isGameActive(players = players, dealer = dealer)) {
            players = Players(players = players.players.map { playTurn(it) })
            dealer = dealer.drawIfBelowDealerStandingRule { deck.draw() }
            ResultView.printPlayersCardStatusAndSum(participant = createParticipants(dealer = dealer, players = players))
        }

        ResultView.printWinner(players = players, dealer = dealer)
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
                    .also {
                        if(it.isBust()) bettingBoard.bust(it)
                        ResultView.printPlayerCard(it)
                    }
        }

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
    }
}
