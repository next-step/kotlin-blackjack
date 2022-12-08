package blackjack

class Card(val cardShape: CARD_SHAPE = CARD_SHAPE.of(), val number: CardNumber = CardNumber.create()) {

    enum class CARD_SHAPE(val printName: String) {
        Spade("스페이드"), Hart("하트"), Diamond("다이아"), Clover("클로버");

        companion object {
            fun of(): CARD_SHAPE = values().random()
        }
    }

    fun getName() = number.name + cardShape.printName
}
