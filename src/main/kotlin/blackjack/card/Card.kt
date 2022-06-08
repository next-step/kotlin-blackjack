package blackjack.card

class Card(private val number: CardNumber, private val shape: CardShape) {
    fun fullName(): String {
        return "${this.number.noName}${this.shape.shapeName}"
    }
}
