package blackjack

enum class Rank(val nickname: String, val point: Int) {
    ACE(nickname = "A", point = 1),
    TWO(nickname = "2", point = 2),
    THREE(nickname = "3", point = 3),
    FOUR(nickname = "4", point = 4),
    FIVE(nickname = "5", point = 5),
    SIX(nickname = "6", point = 6),
    SEVEN(nickname = "7", point = 7),
    EIGHT(nickname = "8", point = 8),
    NINE(nickname = "9", point = 9),
    TEN(nickname = "10", point = 10),
    JACK(nickname = "J", point = 10),
    QUEEN(nickname = "Q", point = 10),
    KING(nickname = "K", point = 10);

    companion object {
        fun of(cardString: String): Rank {
            return values().find { it.nickname in cardString } ?: throw IllegalArgumentException("옳지 않는 카드 이름 입니다.")
        }
    }
}
