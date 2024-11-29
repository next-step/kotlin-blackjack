package blackjack

class Player(val name: String, initialCards: List<Card>) {
    private val _hand = initialCards.toMutableList()
    private val hand: List<Card>
        get() = _hand.toList()

    init {
        require(name.isNotBlank()) {
            "이름은 빈 문자열일 수 없습니다: name=$name"
        }
    }
}
