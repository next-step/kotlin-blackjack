package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.participant.Players
import blackjack.policy.InputMoreCardPolicy
import blackjack.view.OutputView

data class BlackJackGame(
    val dealer: Dealer,
    val gamePlayers: Players,
    val deck: Deck,
    val moreCardPolicy: InputMoreCardPolicy,
) {
    fun play(): List<ParticipantResult> {
        gamePlayers.players.forEach { player -> playerTurn(player) }
        dealerTurn()

        return ParticipantResult.of(dealer, gamePlayers)
    }

    private fun playerTurn(player: Player) {
        while (!player.isBust()) {
            val isMoreCard = moreCardPolicy.isMoreCard(player.getName())
            if (!isMoreCard) {
                break
            }
            player.take(listOf(deck.pick()))
            OutputView.printPlayerCard(player)
        }
    }

    private fun dealerTurn() {
        if(dealer.isMoreCard()) {
            dealer.take(listOf(deck.pick()))
            OutputView.printDealerMoreCard()
        }
    }

    companion object {
        fun start(
            dealer: Dealer,
            players: List<Player>,
            deck: Deck,
            moreCardPolicy: InputMoreCardPolicy,
        ): BlackJackGame {
            dealer.take(listOf(deck.pick(), deck.pick()))
            players.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }

            return BlackJackGame(dealer, Players(players), deck, moreCardPolicy)
        }
    }
}
