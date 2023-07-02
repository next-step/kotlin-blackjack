package blackjack.card

import blackjack.card.signature.CardSignaturePack

class Card(
    val cardSignaturePack: CardSignaturePack,
) {
    override fun toString(): String {
        return cardSignaturePack.getName()
    }
}
