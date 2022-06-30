package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    internal fun `카드를 받고 확인한다`() {
        val player = Player("name")
        val card = SPADE_ACE
        player.receiveCard(card)

        val result = player.hands.cards

        assertThat(result).hasSize(1)
        assertThat(result.first()).isEqualTo(card)
    }

    @Test
    internal fun `카드 숫자의 총합이 21이 넘으면 canNotHit=true이다`() {
        val player = Player("name")
        listOf(
            SPADE_JACK,
            HEART_JACK,
            DIAMOND_JACK
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(30)
        assertThat(player.canNotHit).isTrue
    }

    @Test
    internal fun `카드 숫자의 총합이 21이 안넘으면 canNotHit=false이다`() {
        val player = Player("name")
        listOf(
            SPADE_TWO,
            HEART_TWO,
            DIAMOND_TWO
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(6)
        assertThat(player.canNotHit).isFalse
    }

    @Test
    internal fun `다른 숫자들의 합이 10을 넘지 않으면 ACE를 11로 계산한다`() {
        val player = Player("name")
        listOf(
            SPADE_ACE,
            HEART_TWO,
            DIAMOND_TWO
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(15)
    }

    @Test
    internal fun `다른 숫자들의 합이 10을 넘으면 ACE를 1로 계산한다`() {
        val player = Player("name")
        listOf(
            SPADE_ACE,
            HEART_JACK,
            DIAMOND_JACK
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(21)
    }

    @Test
    internal fun `다른 숫자들의 합에 따라 ACE를 1또는 11로 계산한다`() {
        val player = Player("name")
        listOf(
            SPADE_ACE,
            HEART_ACE,
            DIAMOND_ACE
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(13)
    }
}
