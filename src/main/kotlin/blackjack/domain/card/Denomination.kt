package blackjack.domain.card

enum class Denomination(val cardScores: CardScores, val symbol: String) {

    ACE(CardScores(listOf(CardScore(1), CardScore(11))), "A"),
    TWO(CardScores.from(2), "2"),
    THREE(CardScores.from(3), "3"),
    FOUR(CardScores.from(4), "4"),
    FIVE((CardScores.from(5)), "5"),
    SIX(CardScores.from(6), "6"),
    SEVEN(CardScores.from(7), "7"),
    EIGHT(CardScores.from(8), "8"),
    NINE(CardScores.from(9), "9"),
    TEN(CardScores.from(10), "10"),
    JACK(CardScores.from(10), "J"),
    QUEEN((CardScores.from(10)), "Q"),
    KING(CardScores.from(10), "K"),
    ;

    fun getCardScoresValue(): List<Int> {
        return cardScores.value.map { it.value }
    }
}
