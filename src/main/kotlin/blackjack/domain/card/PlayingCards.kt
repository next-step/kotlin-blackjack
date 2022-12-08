package blackjack.domain.card

class PlayingCards(list: List<PlayingCard>) {
    private val _list: MutableList<PlayingCard> = list.toMutableList()
    val list: List<PlayingCard>
        get() = _list.toList()

    fun get(): PlayingCard {
        if (_list.isEmpty()) {
            throw NoSuchElementException("카드가 없습니다.")
        }
        return _list.removeAt(0)
    }

    fun add(card: PlayingCard) {
        if (_list.contains(card)) {
            throw IllegalArgumentException("중복된 카드는 추가할 수 없습니다.")
        }
        _list.add(card)
    }

    fun isBust(): Boolean {
        return _list.sumOf { it.denomination.score(it) } > WINNING_NUMBER
    }

    fun isBlackjack(): Boolean {
        val isSizeOfBlackjack = _list.size == BLACKJACK_SIZE
        val isAceAndTen = _list.any { it.denomination == Denomination.ACE } && _list.any { it.denomination.score(it) == TEN }
        return isSizeOfBlackjack && isAceAndTen
    }

    fun isStay(): Boolean {
        val isBiggerThanBlackjackSize = _list.size >= BLACKJACK_SIZE
        val isLessThanWinningNumber = _list.sumOf { it.denomination.score(it) } <= WINNING_NUMBER
        return isBiggerThanBlackjackSize && isLessThanWinningNumber
    }

    companion object {
        private const val WINNING_NUMBER = 21
        private const val BLACKJACK_SIZE = 2
        private const val TEN = 10
    }
}
