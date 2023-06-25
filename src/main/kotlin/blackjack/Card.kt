package blackjack

/**
 * ### 트럼프 카드를 표현하는 클래스 입니다
 *
 * 13개로 구성된 랭크와 4개로 구성된 무늬를 가지며 각 카드에 따른 점수를 가집니다
 */
data class Card(
    val rank: CardRank,
    val suit: CardSuit,
) {
    val points = rank.points

    override fun toString(): String {
        return "${rank.displayName}${suit.displayName}"
    }

    companion object {
        val CARD_SET = CardRank.values().flatMap { cardRank ->
            CardSuit.values().map { cardSuit ->
                Card(cardRank, cardSuit)
            }
        }.toSet()
    }
}

enum class CardRank(
    val displayName: String,
    val points: List<Int>,
) {
    ACE("A", listOf(1, 11)),
    TWO("2", listOf(2)),
    THREE("3", listOf(3)),
    FOUR("4", listOf(4)),
    FIVE("5", listOf(5)),
    SIX("6", listOf(6)),
    SEVEN("7", listOf(7)),
    EIGHT("8", listOf(8)),
    NINE("9", listOf(9)),
    TEN("10", listOf(10)),
    JACK("J", listOf(10)),
    QUEEN("Q", listOf(10)),
    KING("K", listOf(10))
}

enum class CardSuit(
    val displayName: String,
) {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLUB("클로버")
}
