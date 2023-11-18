package blackjack

data class PlayingCard(
    val suit: Suits,
    val number: CardNumber
)

enum class Suits(val korean: String) {
    CLOVER("클로버"), HEART("하트"), SPADE("스페이드"), DIAMOND("다이아몬드")
}

enum class CardNumber(val numberName: String) {
    ACE("A") {
        override fun getScore() = CardScore(1, 11)
    },
    TWO("2") {
        override fun getScore() = CardScore(2)
    },
    THREE("3") {
        override fun getScore() = CardScore(3)
    },
    FOUR("4") {
        override fun getScore() = CardScore(4)
    },
    FIVE("5") {
        override fun getScore() = CardScore(5)
    },
    SIX("6") {
        override fun getScore() = CardScore(6)
    },
    SEVEN("7") {
        override fun getScore() = CardScore(7)
    },
    EIGHT("8") {
        override fun getScore() = CardScore(8)
    },
    NINE("9") {
        override fun getScore() = CardScore(9)
    },
    TEN("10") {
        override fun getScore() = CardScore(10)
    },
    JACK("J") {
        override fun getScore() = CardScore(10)
    },
    QUEEN("Q") {
        override fun getScore() = CardScore(10)
    },
    KING("K") {
        override fun getScore() = CardScore(10)
    };

    abstract fun getScore(): CardScore
}
