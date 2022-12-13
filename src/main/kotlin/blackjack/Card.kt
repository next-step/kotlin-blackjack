package blackjack

class Card(val cardShape: CardShape = CardShape.of(), val number: CardNumber = CardNumber.create()) {

    enum class CardShape(val printName: String) {
        Spade("스페이드"), Hart("하트"), Diamond("다이아"), Clover("클로버");

        companion object {
            fun of(): CardShape = values().random()
        }
    }

    fun getName() = number.name + cardShape.printName
}
