package blackjack.domain.dsl

import blackjack.domain.FaceCard
import blackjack.domain.FaceType
import blackjack.domain.SymbolType

@BuilderMarker
class FaceCardsBuilder : Builder<Set<FaceCard>> {
    val values = mutableSetOf<FaceCard>()

    infix fun SymbolType.to(faceType: FaceType): FaceCard {
        val card = FaceCard(symbol = this, faceType = faceType)
        values.add(card)

        return card
    }

    infix fun FaceCard.and(type: FaceType): FaceCard {
        val card = FaceCard(symbol = symbol, faceType = type)
        values.add(card)

        return card
    }

    override fun build(): Set<FaceCard> = values.toSet()
}
