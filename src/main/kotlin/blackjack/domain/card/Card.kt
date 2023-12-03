package blackjack.domain.card

// TODO 생성자가 현재 테스트에서만 쓰이는데 해결할 수 없나?
class Card(
    val number: CardNumber,
    val pattern: CardPattern
) {

    companion object {
        val CARDS = CardPattern.values().associateWith { pattern ->
            CardNumber.values().map { number -> Card(number, pattern) }
        }.values.flatten()
    }
}
