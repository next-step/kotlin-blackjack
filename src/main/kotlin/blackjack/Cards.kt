package blackjack

@JvmInline
value class Cards(
    private val values: MutableList<Card>,
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

    fun values() = values.toList()
}
