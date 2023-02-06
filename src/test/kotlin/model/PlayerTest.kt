package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerTest {
    @Test
    fun `플레이어 카드를 추가한다`() {
        val player = Player("Jake")
        assertThat(player.receiveCard(Card(CardNumber.THREE, CardShape.SPADES))).isTrue
    }

    @Test
    fun `플레이어 카드를 가져온다`() {
        val player = Player("Jake")
        player.receiveCard(Card(CardNumber.THREE, CardShape.SPADES))
        assertThat(player.cards[0]).isEqualTo(Card(CardNumber.THREE, CardShape.SPADES))
    }

    @Test
    fun `플레이어 이름을 가져온다`() {
        val player = Player("Jake")
        assertThat(player.name).isEqualTo("Jake")
    }

    @Test
    fun `플레이어 카드 숫자 합이 21미만일 경우 추가 카드 받기가 가능하다`() {
        assertAll(
            {
                val player = Player("Jake")
                player.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))
                player.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
                assertThat(
                    player.isExtraCard()
                ).isTrue
            },
            {
                val player = Player("Jake")
                player.receiveCard(Card(CardNumber.JACK, CardShape.HEARTS))
                player.receiveCard(Card(CardNumber.QUEEN, CardShape.HEARTS))
                assertThat(
                    player.isExtraCard()
                ).isTrue
            }
        )
    }

    @Test
    fun `플레이어 카드 숫자 합이 21이상일 경우 추가 카드 받기가 불가능하다`() {
        assertAll(
            {
                val player = Player("Jake")
                player.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))
                player.receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
                player.receiveCard(Card(CardNumber.ACE, CardShape.CLUBS))
                assertThat(
                    player.isExtraCard()
                ).isFalse
            },
            {
                val player = Player("Jake")
                player.receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
                player.receiveCard(Card(CardNumber.ACE, CardShape.CLUBS))
                assertThat(
                    player.isExtraCard()
                ).isFalse
            }
        )
    }
}
