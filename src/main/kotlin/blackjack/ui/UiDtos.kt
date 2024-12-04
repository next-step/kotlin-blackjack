package blackjack.ui

typealias UserName = String
typealias UserNames = List<UserName>
typealias UserMore = Boolean
typealias Score = Int
typealias CardRank = String
typealias CardSuit = String
typealias RoundResult = Map<UserName, UserCards>
typealias ViewResult = Map<UserName, Map<UserCards, Score>>

typealias UserCards = Map<CardRank, List<CardSuit>>
fun UserCards.toPrettyString(): String {
    return this.entries.joinToString(separator = ", ") { (rank, suits) ->
        suits.joinToString(separator = ", ") { "${CardType.toDisplayNameOf(rank)}${UiSuit.toDisplayNameOf(it)}" }
    }
}

enum class UiSuit(val displayName: String) {
    HEART(displayName = "하트"),
    DIAMOND(displayName = "다이아몬드"),
    CLUB(displayName = "클로버"),
    SPADE(displayName = "스페이드"), ;

    companion object {
        fun toDisplayNameOf(other: CardSuit): String {
            return entries.find { it.name == other }?.displayName ?: throw IllegalArgumentException("잘못된 카드 모양입니다")
        }
    }
}

enum class CardType(val displayName: String) {
    ACE(displayName = "A"),
    JACK(displayName = "J"),
    QUEEN(displayName = "Q"),
    KING(displayName = "K"),
    TWO(displayName = "2"),
    THREE(displayName = "3"),
    FOUR(displayName = "4"),
    FIVE(displayName = "5"),
    SIX(displayName = "6"),
    SEVEN(displayName = "7"),
    EIGHT(displayName = "8"),
    NINE(displayName = "9"),
    TEN(displayName = "10"), ;

    companion object {
        fun toDisplayNameOf(rank: CardRank): String {
            return entries.find { it.name == rank }?.displayName ?: throw IllegalArgumentException("잘못된 카드 숫자입니다")
        }
    }
}