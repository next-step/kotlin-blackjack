package blackjack.domain.participantion

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class Players(val players: List<Player>) {

    constructor(names: List<String>, cardDeck: CardDeck) : this(
        names.map { name -> Player(name, Cards(cardDeck)) }
    )
}
