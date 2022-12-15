package blackjack.domain.card

import blackjack.domain.card.strategy.ShuffleStrategy

@JvmInline
value class PlayingCards private constructor(private val list: MutableList<PlayingCard>) {
    constructor() : this(mutableListOf())

    fun get(): PlayingCard {
        if (list.isEmpty()) {
            throw NoSuchElementException("카드가 없습니다.")
        }
        return list.removeAt(FIRST_INDEX)
    }

    fun add(card: PlayingCard) {
        if (list.contains(card)) {
            throw IllegalArgumentException("중복된 카드는 추가할 수 없습니다.")
        }
        list.add(card)
    }

    fun isBust(): Boolean {
        return list.sumOf { it.denomination.score(it) } > BLACKJACK_NUMBER
    }

    fun isBlackjack(): Boolean {
        val isSizeOfBlackjack = list.size == BLACKJACK_SIZE
        val isAceAndTen = list.any { it.denomination == Denomination.ACE } && list.any { it.denomination.score(it) == TEN }
        return isSizeOfBlackjack && isAceAndTen
    }

    fun isStay(): Boolean {
        val isBiggerThanBlackjackSize = list.size >= BLACKJACK_SIZE
        val isLessThanWinningNumber = list.sumOf { it.denomination.score(it) } <= BLACKJACK_NUMBER
        return isBiggerThanBlackjackSize && isLessThanWinningNumber
    }

    fun getScore(): Int {
        if (isBlackjack()) {
            return BLACKJACK_NUMBER
        }
        return list.sumOf { it.denomination.score(it) }
    }

    fun cards(): List<String> {
        return list.map { it.toString() }
    }

    fun size(): Int {
        return list.size
    }

    companion object {
        fun of(vararg cards: PlayingCard): PlayingCards {
            return PlayingCards(cards.toMutableList())
        }

        fun of(cards: List<PlayingCard>): PlayingCards {
            return PlayingCards(cards.toMutableList())
        }

        fun shuffle(shuffleStrategy: ShuffleStrategy): PlayingCards {
            return shuffleStrategy.shuffle()
        }

        private const val BLACKJACK_NUMBER = 21
        private const val BLACKJACK_SIZE = 2
        private const val TEN = 10
        private const val FIRST_INDEX = 0
    }
}
