package blackjack.domain

/**
 * 카드 번호 (A~K까지)
 */
enum class CardNumber(val rank: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    companion object {
        fun isAce(rank: String): Boolean {
            return (ACE.rank == rank)
        }

        fun isRoyalFamily(rank: String): Boolean {
            return (JACK.rank == rank || QUEEN.rank == rank || KING.rank == rank)
        }
    }
}
