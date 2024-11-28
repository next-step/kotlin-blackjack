package blackjack.domain.card

sealed class CardSuit(val displayName: String) {
    data object Diamond : CardSuit("다이아몬드")
    data object Heart : CardSuit("하트")
    data object Spade : CardSuit("스페이드")
    data object Club : CardSuit("클로버")

    companion object {
        val all = listOf(Diamond, Heart, Spade, Club)
    }
}
