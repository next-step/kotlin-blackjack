package blackjack.card

class Card(private val number: CardNumber, private val shape: CardShape) {
    var primaryPoint = this.number.primaryPoint
        private set

    var secondaryPoint = this.number.secondaryPoint
        private set

    fun fullName(): String {
        return "${this.number.noName}${this.shape.shapeName}"
    }
}
