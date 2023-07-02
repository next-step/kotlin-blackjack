package blackjack

import blackjack.card.Card
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSignaturePack
import blackjack.card.signature.CardSymbolSignature

class TestUtils {
    companion object {
        fun card(ordinal: CardOrdinalSignature, symbolSignature: CardSymbolSignature): Card {
            return Card(CardSignaturePack(ordinal, symbolSignature))
        }
    }
}
