package blackjack.card

import blackjack.card.signature.CardSignaturePack

class Card(
    private val cardSignaturePack: CardSignaturePack,
) {
    override fun toString(): String {
        return cardSignaturePack.getName()
    }
}
