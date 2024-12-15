package blackjack.flow

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Participants
import blackjack.participant.Player
import blackjack.participant.Players
import blackjack.view.OutputView
import blackjack.view.policy.InputMoreCardPolicy

data class BlackJackGame(
    val participants: Participants,
    val turnMachine: TurnMachine,
) {
    fun play(): Participants {
        val gamePlayers = participants.gamePlayers
        val players = gamePlayers.players
        players.forEach { player -> turnMachine.playerTurn(player) }

        val dealer = participants.dealer
        turnMachine.dealerTurn(dealer)

        return participants
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
                Participants(dealer, Players(players)),
                TurnMachine(deck, InputMoreCardPolicy, OutputView),
            )
        }
    }
}
