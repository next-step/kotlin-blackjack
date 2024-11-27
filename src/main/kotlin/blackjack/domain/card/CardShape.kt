package blackjack.domain.card

sealed class CardShape(val symbol: String) {
    data object Spade : CardShape("스페이드")

    data object Diamond : CardShape("다이아")

    data object Heart : CardShape("하트")

    data object Clover : CardShape("클로버")
}
