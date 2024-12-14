package blackjack.flow

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Participants
import blackjack.participant.Player
import blackjack.participant.Players
import blackjack.view.OutputView
import blackjack.view.policy.InputMoreCardPolicy

data class BlackJackGame(
    val dealer: Dealer,
    val gamePlayers: Players,
    val turnMachine: TurnMachine,
) {
    fun play(): Participants {
        gamePlayers.players.forEach { player -> turnMachine.playerTurn(player) }
        turnMachine.dealerTurn(dealer)

        return Participants(dealer, gamePlayers)
    }

    companion object {
        fun start(
            dealer: Dealer,
            players: List<Player>,
            deck: Deck,
        ): BlackJackGame {
            dealer.take(listOf(deck.pick(), deck.pick()))
            players.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }

            return BlackJackGame(
                dealer,
                Players(players),
                TurnMachine(deck, InputMoreCardPolicy, OutputView),
            )
        }
    }
}
