package blackjack.domain.card

import kotlin.math.abs

class CardSet(val cards: List<Card>) {
    val size = cards.size

    init {
        val distinct = cards.distinct()
        require(distinct.size == cards.size) { "카드뭉치에는 중복된 카드를 허용하지 않습니다." }

    }

    fun isFull(cardPolicyGroup: CardScorePolicyGroup): Boolean {
        return CARD_SUM_LIMIT <= sumOfMin(cardPolicyGroup).score
    }

    fun addCard(card: Card): CardSet {
        val newCards = cards.toMutableList()
        newCards.add(card)
        return CardSet(newCards)
    }

    fun addCard(cardSet: CardSet): CardSet {
        val newCards = cards.toMutableList()
        newCards.addAll(cardSet.cards)
        return CardSet(newCards)
    }

    fun sum(cardScorePolicy: CardScorePolicy): CardScore {
        return CardScore(cards.map { cardScorePolicy.getScore(it) }.sumOf { it.score })
    }

    fun sumOfBest(cardPolicyGroup: CardScorePolicyGroup, targetNumber: Int): CardScore {
        return cardPolicyGroup.policies.map { sum(it) }.minBy { abs(targetNumber - it.score) }
    }

    fun sumOfMin(cardPolicyGroup: CardScorePolicyGroup): CardScore {
        return cardPolicyGroup.policies.map { sum(it) }.minBy { it.score }
    }

    companion object {
        private const val CARD_SUM_LIMIT = 21
        private val EMPTY_CARD_SET = CardSet(emptyList())

        fun of(vararg card: Card): CardSet {
            return CardSet(card.toList())
        }

        fun empty(): CardSet {
            return EMPTY_CARD_SET
        }
    }
}
