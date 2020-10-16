package blackjack


import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val sampleCard1 = Card(
    Pair(
        Suit.CLUB,
        Denomination.FIVE
    )
)
val sampleCard2 = Card(
    Pair(
        Suit.DIAMOND,
        Denomination.FOUR
    )
)
val sampleCards = Cards(mutableListOf(sampleCard1, sampleCard2))

class CardsTest {
    @Test
    fun `카드 뭉치들 점수 확인`() {

        assertThat(sampleCards.getTotalScore()).isEqualTo(9)
    }

    @Test
    fun `카드 뭉치에 카드 추가`() {
        val sampleAddCard = Card(
            Pair(
                Suit.HEART,
                Denomination.FIVE
            )
        )
        assertThat(sampleCards.addCard(sampleAddCard)).isEqualTo(mutableListOf(sampleCard1, sampleCard2, sampleAddCard))
    }

    @Test
    fun `카드 정보 스트링으로 출력`() {
        assertThat(sampleCards.toString()).isEqualTo("[5클로버, 4다이아몬드]")
    }
}
