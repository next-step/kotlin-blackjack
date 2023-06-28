package blackjack.domain.shuffle

import blackjack.domain.card.Card

class ForceMoveForwardCardShuffler(
    private val forwardTargets: List<Card>,
) : Shuffler<Card> {

    constructor(vararg forwardTargets: Card) : this(forwardTargets.toList())

    init {
        require(forwardTargets.distinct().size == forwardTargets.size) {
            "has duplication in forward targets"
        }
    }

    override fun shuffled(list: List<Card>): List<Card> {
        val forwardSet = forwardTargets.toSet()
        return buildList {
            addAll(forwardTargets)
            addAll(list.filter { forwardSet.contains(it).not() })
        }
    }
}
