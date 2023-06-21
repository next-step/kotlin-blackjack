package blackjack

@JvmInline
value class Cards(
    private val values: MutableList<Card> = mutableListOf(),
) {
    init {
        require(values.size == values.toSet().size) { "중복된 카드가 저장될 수 없다." }
    }

    fun addCard(card: Card) {
        if (values.contains(card)) {
            throw IllegalArgumentException("이미 존재하는 카드를 추가할 수 없다.")
        }
        values.add(card)
    }

    fun deal(card: Card) {
        check(values.size < CAN_DEAL_CARDS_SIZE) { "이미 2장이상 가지고 있어 deal할 수 없다." }
        addCard(card)
    }

    fun hit(card: Card) {
        check(values.size >= CAN_DEAL_CARDS_SIZE) { "카드가 deal이 완료되지 않아 hit할 수 없다." }
        check(calculateScore() <= BUST_SCORE) { "카드가 bust되어 더이상 hit할 수 없다." }
    }

    private fun calculateScore() = values.sumOf { it.denomination.score.min() }

    fun values() = values.toList()

    companion object {
        private const val CAN_DEAL_CARDS_SIZE = 2
        private const val BUST_SCORE = 21
    }
}
