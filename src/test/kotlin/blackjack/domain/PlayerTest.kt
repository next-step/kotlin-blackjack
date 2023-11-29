package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `단건 카드 추가`() {
        // given
        val player = Player("test")
        val card = Card(Suit.SPADE, Rank.ACE)

        // when
        player.addCard(card)

        // then
        assertThat(player.cards).contains(card)
    }

    @Test
    fun `여러 카드 추가`() {
        // given
        val player = Player("test")
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.TWO),
            Card(Suit.HEART, Rank.THREE),
            Card(Suit.CLUB, Rank.FOUR)
        )

        // when
        player.addCard(cards)

        // then
        assertThat(player.cards).containsAll(cards)
    }

    @Test
    fun `카드 점수 계산 - 에이스를 제외하고 카드 점수의 총합이 11점 이상이면 에이스는 1점이 된다`() {
        // given
        val player = Player("test")
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.NINE),
            Card(Suit.HEART, Rank.TWO)
        )
        player.addCard(cards)

        // when
        val score = player.score()

        // then
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun `카드 점수 계산 - 에이스를 제외하고 카드 점수의 총합이 10점 이하면 에이스는 11점이 된다`() {
        // given
        val player = Player("test")
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.SIX),
            Card(Suit.HEART, Rank.FOUR)
        )
        player.addCard(cards)

        // when
        val score = player.score()

        // then
        assertThat(score).isEqualTo(21)
    }
}
