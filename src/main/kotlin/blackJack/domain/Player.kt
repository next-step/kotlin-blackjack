package blackJack.domain

class Player(val name: String) {
    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()

    fun giveCard(card: Card) {
        _hands.add(card)
    }
}
