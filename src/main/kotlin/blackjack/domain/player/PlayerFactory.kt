package blackjack.domain.player

import blackjack.application.Deck
import blackjack.domain.player.state.Name

object PlayerFactory {
    private const val NUMBER_OF_INIT_CARDS = 2

    fun create(names: Array<Name>, deck: Deck): Players {
        val players = names.map { Player(it, deck.getCardsByNumberOfCards(NUMBER_OF_INIT_CARDS)) }
        return Players(players)
    }
}
