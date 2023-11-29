package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {
    @Test
    fun `카드 점수 계산 - 에이스를 제외하고 카드 점수의 총합이 11점 이상이면 에이스는 1점이 된다`() {
        // given
        val cardList = CardList()
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.NINE),
            Card(Suit.HEART, Rank.TWO)
        )
        cardList.addCard(cards)

        // when
        val score = BlackJackGame.score(cardList)

        // then
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun `카드 점수 계산 - 에이스를 제외하고 카드 점수의 총합이 10점 이하면 에이스는 11점이 된다`() {
        // given
        val cardList = CardList()
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.SIX),
            Card(Suit.HEART, Rank.FOUR)
        )
        cardList.addCard(cards)

        // when
        val score = BlackJackGame.score(cardList)

        // then
        assertThat(score).isEqualTo(21)
    }
}
