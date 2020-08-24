package blackJack.domain

class Person(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 없을수 없습니다." }
    }

    private val _hands: MutableList<Card> = mutableListOf()
    val hands
        get() = _hands.toList()

    fun addCard(card: Card) {
        _hands.add(card)
    }

    fun getTotalScore(): Int = TotalScore.get(_hands)
}
