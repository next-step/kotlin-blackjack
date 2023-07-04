package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

abstract class Player(
    open val name: String,
    val deck: Deck = Deck()
) {
    fun addCardToDeck(card: Card) {
        require(!deck.contains(card)) {
            "중복된 카드가 존재합니다."
        }
        deck.add(card)
    }
}
