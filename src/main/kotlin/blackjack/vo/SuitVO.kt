package blackjack.vo

import blackjack.Suit

class SuitVO(
    val name: String,
) {
    companion object {
        operator fun invoke(suit: Suit): SuitVO {
            return when (suit) {
                Suit.CLUBS -> SuitVO("클로버")
                Suit.DIAMONDS -> SuitVO("다이아몬드")
                Suit.HEARTS -> SuitVO("하트")
                Suit.SPADES -> SuitVO("스페이드")
            }
        }
    }
}
