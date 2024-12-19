package blackjack.machine

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
            players =
                players.play(
                    isHitCard = { player -> InputView.isHitCard(player) },
                    deck = deck,
                    afterPlay = { player -> ResultView.printPlayerCard(player) },
                )

            players
                .getLosers()
                .takeIf { it.isNotEmpty() }
                ?.sumOf { it.betAmount }
                ?.run { dealer = dealer.win(betAmount = this) }

            dealer =
                dealer.drawIfBelowDealerStandingRule(
                    deck = deck,
                    afterDraw = { ResultView.printDealerDrawCard() },
                )
            dealer = dealer.handleBust(players.getRemainedPlayers().sum())
            players = players.applyWinToRemainPlayer(dealer = dealer)

            ResultView.printPlayersCardStatusAndSum(
                participant =
                    createParticipants(
                        dealer = dealer,
                        players = players,
                    ),
            )
        }

        ResultView.printBetResult(participantBets = (players.players + dealer).associateWith { it.betResult })
    }

    private fun handleBlackJack(
        players: Players,
        dealer: Dealer,
    ): Pair<Players, Dealer> =
        Players(
            players.players
                .map { it.updateBetResult(InputView.inputBettingAmount(it)) }
                .map { it.handleBlackJack(dealer = dealer) },
        ) to dealer.handleBlackJack(blackJackPlayers = players.players.filter { it.isBlackjack() })

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
        const val BONUS_RATIO = 1.5
    }
}
