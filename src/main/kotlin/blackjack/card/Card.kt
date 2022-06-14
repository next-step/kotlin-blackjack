package blackjack.card

class Card(private val number: CardNumber, private val shape: CardShape) {
    val primaryPoint = this.number.primaryPoint

    val secondaryPoint = this.number.secondaryPoint

    fun fullName(): String {
        return "${this.number.noName}${this.shape.shapeName}"
    }
}
