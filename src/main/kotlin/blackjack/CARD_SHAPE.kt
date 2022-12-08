package blackjack

enum class CARD_SHAPE(val printName: String) {
    Spade("스페이드"), Hart("하트"), Diamond("다이아"), Clover("클로버");

    companion object {
        fun of(): CARD_SHAPE = values().random()
    }
}
