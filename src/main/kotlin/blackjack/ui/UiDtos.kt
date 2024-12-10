package blackjack.ui

import blackjack.domain.CardRank

const val DEALER_NAME = "딜러"
typealias UserName = String
typealias UserNames = List<UserName>
typealias UserMore = Boolean
typealias Score = Int
typealias DisplayCardRank = String
typealias UserCards = List<DisplayCard>
typealias CardScore = Int

fun UserCards.displayCards(): String {
    return joinToString { it.toString() }
}

data class DisplayCard(
    val rank: DisplayCardRank,
    val suit: DisplaySuit,
) {
    companion object {
        fun from(
            rankName: String,
            suitName: String,
        ): DisplayCard {
            return DisplayCard(rankName, DisplaySuit.valueOf(suitName))
        }
    }

    override fun toString(): String {
        return "${DisplayCardType.toDisplayNameOf(rank)}$suit"
    }
}

data class FinalWinnerResults(
    val dealerResult: DealerResult,
    val playerResults: Map<UserName, UIMatchType>,
)

data class DealerResult(
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0,
)

data class RoundResult(val userName: UserName, val cards: List<DisplayCard>, val score: CardScore)

enum class DisplaySuit(val displayName: String) {
    HEART(displayName = "하트"),
    DIAMOND(displayName = "다이아몬드"),
    CLUB(displayName = "클로버"),
    SPADE(displayName = "스페이드"), ;

    override fun toString(): String {
        return displayName
    }
}

enum class DisplayCardType(
    val cardType: CardRank,
    val displayName: String,
) {
    ACE(cardType = CardRank.ACE, displayName = "A"),
    JACK(cardType = CardRank.JACK, displayName = "J"),
    QUEEN(cardType = CardRank.QUEEN, displayName = "Q"),
    KING(cardType = CardRank.KING, displayName = "K"),
    TWO(cardType = CardRank.TWO, displayName = "2"),
    THREE(cardType = CardRank.THREE, displayName = "3"),
    FOUR(cardType = CardRank.FOUR, displayName = "4"),
    FIVE(cardType = CardRank.FIVE, displayName = "5"),
    SIX(cardType = CardRank.SIX, displayName = "6"),
    SEVEN(cardType = CardRank.SEVEN, displayName = "7"),
    EIGHT(cardType = CardRank.EIGHT, displayName = "8"),
    NINE(cardType = CardRank.NINE, displayName = "9"),
    ;

    companion object {
        fun toDisplayNameOf(rank: DisplayCardRank): String {
            return entries.find { it.cardType.name == rank }?.displayName ?: throw IllegalArgumentException("잘못된 카드 숫자입니다")
        }
    }

    override fun toString(): String {
        return displayName
    }
}

enum class UIMatchType(val displayName: String) {
    WIN(displayName = "승"),
    LOSS(displayName = "패"),
    DRAW(displayName = "무"),
}
