package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardListTest {
    @Test
    fun `단건 카드 추가`() {
        // given
        val cardList = CardList()
        val cards = listOf(Card(Suit.SPADE, Rank.ACE))

        // when
        cardList.addCard(cards)

        // then
        assertThat(cardList.cards).containsAll(cards)
    }

    @Test
    fun `여러 카드 추가`() {
        // given
        val cardList = CardList()
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.NINE),
            Card(Suit.HEART, Rank.TWO)
        )

        // when
        cardList.addCard(cards)

        // then
        assertThat(cardList.cards).containsAll(cards)
    }
}
