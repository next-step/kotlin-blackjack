package blackjack

class Card(val cardShape: CARD_SHAPE = CARD_SHAPE.of(), val number: CardNumber = CardNumber.create()) {
    fun getName() = number.name + cardShape.printName
}
