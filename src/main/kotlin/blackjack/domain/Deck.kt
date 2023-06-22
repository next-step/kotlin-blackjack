package blackjack.domain

import blackjack.domain.dsl.buildDeck

class Deck(values: MutableSet<Card> = mutableSetOf()) {
    private val internalValues: MutableSet<Card> = values.toMutableSet()

    val size: Int
        get() = internalValues.size

    fun add(card: Card) = internalValues.add(card)

    fun remove(card: Card) = internalValues.remove(card)

    fun toList(): List<Card> = internalValues.toList()

    fun pick(count: Int): Set<Card> {
        require(internalValues.size > count) {
            "더 이상 뽑을 카드가 없습니다. 현재 카드는 ${internalValues.size}개 남아있습니다."
        }
        val result = internalValues.shuffled().take(count).toSet()
        internalValues.removeAll(result)

        return result
    }

    fun score(): Int {
        var result = 0
        for (card in internalValues.sorted()) {
            result += card.score(acc = result)
        }

        return result
    }

    fun copy(): Deck = Deck(internalValues.toMutableSet())

    companion object {
        fun create(): Deck = buildDeck {
            aceCards(*SymbolType.values())
            numberCards {
                SymbolType.values().forEach {
                    NumberCard.MIN_LIMIT..NumberCard.MAX_LIMIT from it
                }
            }
            faceCards {
                SymbolType.values().forEach {
                    it to FaceType.KING and FaceType.QUEEN and FaceType.JACK
                }
            }
        }
    }
}
