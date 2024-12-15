package blackjack.machine

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
    private var players: Players = Players(players = emptyList()),
    private var dealer: Dealer = Dealer.ready(initialCards = emptyList()),
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
        players = Players(players = playerList)
        dealer = Dealer.ready(initialCards = listOf(deck.draw(), deck.draw()))

        players =
            Players(
                players.players
                    .map { it.updateBetResult(InputView.inputBettingAmount(it)) }
                    .map { it.handleBlackJack(dealer = dealer) },
            )
        dealer = dealer.handleBlackJack(blackJackPlayers = players.players.filter { it.isBlackjack() })

        ResultView.printPlayerNamesAndDealer(players = players, dealer = dealer)
        ResultView.printPlayersCardStatus(participants = createParticipants(dealer = dealer, players = players))

        while (Rule.isGameActive(players = players, dealer = dealer)) {
            players = Players(players = players.players.map { playTurn(player = it) })
            dealer = dealer.drawIfBelowDealerStandingRule { deck.draw() }

            if(dealer.isBlackjack()) {
                dealer = dealer.lose(players = players.getRemainedPlayers())
                players = Players(players = players.players.map { if (it.isBust()) it else it.win() })
            }

            ResultView.printPlayersCardStatusAndSum(participant = createParticipants(dealer = dealer, players = players))
        }

        ResultView.printBetResult(participantBets = (players.players + dealer).associateWith { it.betResult })
    }

    private fun playTurn(
        player: Player,
    ): Player =
        when {
            player.isBust() -> player
            !InputView.isHitCard(player) -> player.also { ResultView.printPlayerCard(it) }
            else ->
                player
                    .hitCard(deck.draw())
                    .let { playerAfterDraw ->
                        ResultView.printPlayerCard(playerAfterDraw)
                        playerAfterDraw.lose()
                            .takeIf { it.isBust() }
                            ?.also { dealer = dealer.win(player = it) }
                            ?: playerAfterDraw
                    }
        }

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
        const val BONUS_RATIO = 1.5
    }
}
