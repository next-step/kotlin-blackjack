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
        suits.joinToString(separator = ", ") { "$rank${UiSuit.toDisplayNameOf(it)}" }
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