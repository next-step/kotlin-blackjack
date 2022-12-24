package model

class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
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
