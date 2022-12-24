package model

class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
    fun hit(): Card {
        return Card(selectCardNumber(), selectCardShape())
    }

    constructor() : this(selectCardNumber(), selectCardShape())

    companion object {
        fun selectCardNumber(): CardNumber {
            return CardNumber.values().toList().shuffled()[0]
        }

        fun selectCardShape(): CardShape {
            return CardShape.values().toList().shuffled()[0]
        }
    }
}
