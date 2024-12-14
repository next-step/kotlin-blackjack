package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.participant.Players
import blackjack.policy.MoreCardPolicy
import blackjack.view.OutputView

data class BlackJackGame(
    val dealer: Dealer,
    val gamePlayers: Players,
    val deck: Deck,
) {
    fun play(inputMoreCardPolicy: MoreCardPolicy): List<ParticipantResult> {
        return gamePlayers.players.map { player ->
            while (player.isNotBust()) {
                val isMoreCard = inputMoreCardPolicy.isMoreCard(player.getName())
                if(!isMoreCard) {
                    break
                }
                player.take(listOf(deck.pick()))
                OutputView.printPlayerCard(player)
            }
            ParticipantResult.of(dealer, player)
        }.flatten()
    }

    companion object {
        fun start(
            dealer: Dealer,
            players: List<Player>,
            deck: Deck,
        ): BlackJackGame {
            dealer.take(listOf(deck.pick(), deck.pick()))
            players.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }

            return BlackJackGame(dealer, Players(players), deck)
        }
    }
}
