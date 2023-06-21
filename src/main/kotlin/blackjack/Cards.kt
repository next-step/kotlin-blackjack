package blackjack

@JvmInline
value class Cards(
    val values: List<Card>,
) {
    init {
        require(values.size == values.toSet().size) { "중복된 카드가 저장될 수 없다." }
    }

    fun addCard(card: Card) {
        if (values.contains(card)) {
            throw IllegalArgumentException("이미 존재하는 카드를 추가할 수 없다.")
        }
    }
}
