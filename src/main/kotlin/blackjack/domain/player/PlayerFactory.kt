package blackjack.domain.player

import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import blackjack.domain.player.state.Name

object PlayerFactory {
    private const val NUMBER_OF_INIT_CARDS = 2

    fun create(names: Array<Name>, deck: Deck): Players {
        val players = names.map { Player(it, initState(deck.getCardsByNumberOfCards(NUMBER_OF_INIT_CARDS))) }
        return Players(players)
    }

    fun create(name: Name, playingCards: PlayingCards): Player {
        return Player(name, initState(playingCards))
    }

    private fun initState(cards: PlayingCards): State {
        if (cards.isBlackjack()) {
            return Blackjack(cards)
        }
        return Hit(cards)
    }
}
