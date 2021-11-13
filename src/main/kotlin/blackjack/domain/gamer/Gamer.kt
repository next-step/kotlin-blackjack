package blackjack.domain.gamer

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

interface Gamer {
    val name: String
    val cards: Cards

    fun receiveCard(card: Card)

    fun haveCards(): String
}
