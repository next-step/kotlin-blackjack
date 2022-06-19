package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    internal fun `카드를 받고 확인한다`() {
        val player = Player("name")
        val card = Card(Symbol.SPADE, CardNumber.ACE)
        player.receiveCard(card)

        val result = player.cards

        assertThat(result).hasSize(1)
        assertThat(result.first()).isEqualTo(card)
    }

    @Test
    internal fun `카드 숫자의 총합이 21이 넘으면 isBust=true이다`() {
        val player = Player("name")
        listOf(
            Card(Symbol.SPADE, CardNumber.JACK),
            Card(Symbol.HEART, CardNumber.JACK),
            Card(Symbol.DIAMOND, CardNumber.JACK)
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(30)
        assertThat(player.isBust()).isTrue
    }

    @Test
    internal fun `카드 숫자의 총합이 21이 안넘으면 isBust=false이다`() {
        val player = Player("name")
        listOf(
            Card(Symbol.SPADE, CardNumber.TWO),
            Card(Symbol.HEART, CardNumber.TWO),
            Card(Symbol.DIAMOND, CardNumber.TWO)
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(6)
        assertThat(player.isBust()).isFalse
    }

    @Test
    internal fun `다른 숫자들의 합이 10을 넘지 않으면 ACE를 11로 계산한다`() {
        val player = Player("name")
        listOf(
            Card(Symbol.SPADE, CardNumber.ACE),
            Card(Symbol.HEART, CardNumber.TWO),
            Card(Symbol.DIAMOND, CardNumber.SEVEN)
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(20)
    }

    @Test
    internal fun `다른 숫자들의 합이 10을 넘으면 ACE를 1로 계산한다`() {
        val player = Player("name")
        listOf(
            Card(Symbol.SPADE, CardNumber.ACE),
            Card(Symbol.HEART, CardNumber.JACK),
            Card(Symbol.DIAMOND, CardNumber.NINE)
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(20)
    }

    @Test
    internal fun `다른 숫자들의 합에 따라 ACE를 1또는 11로 계산한다`() {
        val player = Player("name")
        listOf(
            Card(Symbol.SPADE, CardNumber.ACE),
            Card(Symbol.HEART, CardNumber.ACE),
            Card(Symbol.DIAMOND, CardNumber.ACE)
        ).map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(13)
    }
}
