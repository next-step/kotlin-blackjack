package blackJack.domain

class Player(val name: String?) {
    init {
        checkName()
    }

    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()
    val totalScore: Int
        get() = TotalScore.getScore(_hands.map { it.number })

    private fun checkName() {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("이름은 공백값과 null값을 받을수 없습니다.")
        }
    }

    fun giveCard(card: Card) {
        _hands.add(card)
    }

    fun isBust(): Boolean = totalScore > 21
}
