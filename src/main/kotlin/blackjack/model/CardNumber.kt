package blackjack.model

enum class CardNumber(private val symbol: String, val scores: Scores) {
    ACE("A", ScoresFactory.create(listOf(1, 11))),
    TWO("2", ScoresFactory.create(listOf(2))),
    THREE("3", ScoresFactory.create(listOf(3))),
    FOUR("4", ScoresFactory.create(listOf(4))),
    FIVE("5", ScoresFactory.create(listOf(5))),
    SIX("6", ScoresFactory.create(listOf(6))),
    SEVEN("7", ScoresFactory.create(listOf(7))),
    EIGHT("8", ScoresFactory.create(listOf(8))),
    NINE("9", ScoresFactory.create(listOf(9))),
    TEN("10", ScoresFactory.create(listOf(10))),
    JACK("J", ScoresFactory.create(listOf(10))),
    QUEEN("Q", ScoresFactory.create(listOf(10))),
    KING("K", ScoresFactory.create(listOf(10)));

    override fun toString(): String {
        return symbol
    }
}
