package blackjack.domain.participantion

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class Players(val players: List<Player>) {

    constructor(names: List<String>, cardDeck: CardDeck, prices: List<Price>) : this(
        names.zip(prices).map { (name, price) ->
            Player(name, Cards(cardDeck), price)
        }
    )
}
