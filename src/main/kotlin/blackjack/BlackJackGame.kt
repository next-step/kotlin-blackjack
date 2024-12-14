package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Players
import blackjack.policy.InputMoreCardPolicy
import blackjack.policy.MoreCardPolicy
import blackjack.view.OutputView

data class BlackJackGame(
    val dealer: Dealer,
    val gamePlayers: Players,
    val deck: Deck,
) {
    fun start(): BlackJackGame {
        dealer.take(listOf(deck.pick(), deck.pick()))
        gamePlayers.player.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }
        return BlackJackGame(dealer, gamePlayers, deck)
    }

    fun play(inputMoreCardPolicy: MoreCardPolicy) {
        gamePlayers.player.map { player ->
            while (player.isNotBust()) {
                val isMoreCard = inputMoreCardPolicy.isMoreCard(player.name)
                if(!isMoreCard) {
                    break
                }
                player.take(deck.pick())
                OutputView.printPlayerCard(player)
            }
        }
    }
}
