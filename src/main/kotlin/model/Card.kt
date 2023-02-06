package model

data class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
    override fun toString(): String {
        return "$cardNumber$cardShape"
    }
}
