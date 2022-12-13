package blackjack

class Card(val cardShape: CardShape, val number: CardNumber) {

    enum class CardShape(val printName: String) {
        Spade("스페이드"), Hart("하트"), Diamond("다이아"), Clover("클로버")
    }

    fun getName() = number.name + cardShape.printName
}
