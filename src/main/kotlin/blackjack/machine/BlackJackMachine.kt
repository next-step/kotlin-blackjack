package blackjack.machine

import betting.TurnResult
import blackjack.dealer.Dealer
import blackjack.deck.Deck
import blackjack.participant.createParticipants
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
        val initialPlayers = Players(players = playerList)
        val initialDealer = Dealer.ready(initialCards = listOf(deck.draw(), deck.draw()))

        var (players, dealer) = handleBlackJack(players = initialPlayers, dealer = initialDealer)
        ResultView.printPlayerNamesAndDealer(players = players, dealer = dealer)
        ResultView.printPlayersCardStatus(participants = createParticipants(dealer = dealer, players = players))

        while (Rule.isGameActive(players = players, dealer = dealer)) {
            val (playersAfterTurn, dealerAfterPlayerTurn) = players.play(
                isHitCard = { player -> InputView.isHitCard(player) },
                draw = { deck.draw() },
                afterPlay = { player -> ResultView.printPlayerCard(player) },
                dealer = dealer,
            )

            val (playersAfterDealerDraw, dealerAfterDraw) = dealerAfterPlayerTurn.drawIfBelowDealerStandingRule(
                players = playersAfterTurn,
                draw = { deck.draw() },
                afterDraw = { ResultView.printDealerDrawCard() },
            )

            players = playersAfterDealerDraw
            dealer = dealerAfterDraw
            ResultView.printPlayersCardStatusAndSum(participant = createParticipants(dealer = dealer, players = players))
        }

        ResultView.printBetResult(participantBets = (players.players + dealer).associateWith { it.betResult })
    }

    private fun handleBlackJack(
        players: Players,
        dealer: Dealer,
    ): TurnResult =
        TurnResult.status(
            players =
                Players(
                    players.players
                        .map { it.updateBetResult(InputView.inputBettingAmount(it)) }
                        .map { it.handleBlackJack(dealer = dealer) },
                ),
            dealer = dealer.handleBlackJack(blackJackPlayers = players.players.filter { it.isBlackjack() })
        )

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
        const val BONUS_RATIO = 1.5
    }
}
