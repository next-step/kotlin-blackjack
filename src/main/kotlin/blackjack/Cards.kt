package blackjack

@JvmInline
value class Cards(
    val values: List<Card>,
) {
    init {
        require(values.size == values.toSet().size) { "중복된 카드가 저장될 수 없다." }
    }
}
