package blackjack

class Card(
    val number: Int
) {
    init {
        require(number in 1..10) { "숫자는 1에서 10 사이여야 합니다." }
    }
}
