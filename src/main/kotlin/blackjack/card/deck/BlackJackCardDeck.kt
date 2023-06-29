package blackjack.card.deck

import blackjack.card.Card
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSignaturePack
import blackjack.card.signature.CardSymbolSignature

class BlackJackCardDeck {
    private val cardSets = fillDeck()

    fun castCard(): Card {
        return cardSets.removeFirstOrNull() ?: throw CardDeckEmptyException()
    }

    private fun fillDeck(): ArrayDeque<Card> {
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

    class CardDeckEmptyException : RuntimeException("카드 덱이 비어있습니다")
}
