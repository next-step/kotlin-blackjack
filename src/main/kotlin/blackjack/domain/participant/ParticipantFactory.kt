package blackjack.domain.participant

import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import blackjack.domain.participant.state.Name

object ParticipantFactory {
    private const val NUMBER_OF_INIT_CARDS = 2

    fun create(names: Array<Name>, deck: Deck): Participants {
        val dealer = createDealer(deck)
        val players = names.map { Player(it, initState(deck.getCards(NUMBER_OF_INIT_CARDS))) }
        return Participants(dealer, *players.toTypedArray())
    }

    fun initState(cards: PlayingCards): State {
        if (cards.isBlackjack()) {
            return Blackjack(cards)
        }
        return Hit(cards)
    }

    private fun createDealer(deck: Deck): Dealer {
        return Dealer(initState(deck.getCards(NUMBER_OF_INIT_CARDS)))
    }
}
