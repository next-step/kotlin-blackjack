package blackjack.card.deck

import blackjack.card.Card
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSignaturePack
import blackjack.card.signature.CardSymbolSignature

object DefaultBlackJackCardSetGenerator {
    fun execute(): ArrayDeque<Card> {
        val cardSymbolSignatures = CardSymbolSignature.values()
        val cardOrdinalSignatures = CardOrdinalSignature.values()
        val list = cardOrdinalSignatures.map { o ->
            cardSymbolSignatures.map { s ->
                Card(CardSignaturePack(o, s))
            }
        }.flatten()
            .shuffled()
        return ArrayDeque(list)
    }
}
