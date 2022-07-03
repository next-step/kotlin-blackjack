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
        OVER_21_CARDS.map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(30)
        assertThat(player.canNotHit).isTrue
    }

    @Test
    internal fun `카드 숫자의 총합이 21이 안넘으면 canNotHit=false이다`() {
        val player = Player("name")
        UNDER_21_CARDS.map {
            player.receiveCard(it)
        }

        assertThat(player.sumOfPoints()).isEqualTo(14)
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

    @Test
    internal fun `카드가 2장이고, 합이 21이면 BLACKJACK이다`() {
        val player = Player("name")
        SUM21_CARD2.map {
            player.receiveCard(it)
        }

        assertThat(player.getStatus()).isEqualTo(Status.BLACKJACK)
    }

    @Test
    internal fun `카드가 3장 이상이고, 합이 21이면 HIT이다`() {
        val player = Player("name")
        SUM21_CARDS.map {
            player.receiveCard(it)
        }

        assertThat(player.getStatus()).isEqualTo(Status.HIT)
    }

    @Test
    internal fun `합이 21이하이면 HIT이다`() {
        val player = Player("name")
        UNDER_21_CARDS.map {
            player.receiveCard(it)
        }

        assertThat(player.getStatus()).isEqualTo(Status.HIT)
    }

    @Test
    internal fun `합이 21초과이면 BUST이다`() {
        val player = Player("name")
        OVER_21_CARDS.map {
            player.receiveCard(it)
        }

        assertThat(player.getStatus()).isEqualTo(Status.BUST)
    }

    @Test
    internal fun `두개의 점수를 비교하여 크면 양수를 반환한다`() {
        val player1 = Player("player1")
        OVER_21_CARDS.map { player1.receiveCard(it) }

        val player2 = Player("player2")
        UNDER_21_CARDS.map { player2.receiveCard(it) }

        assertThat(player1.compareScore(player2)).isGreaterThan(0)
    }

    @Test
    internal fun `두개의 점수를 비교하여 같으면 0을 반환한다`() {
        val player1 = Player("player1")
        SUM21_CARD2.map { player1.receiveCard(it) }

        val player2 = Player("player2")
        SUM21_CARDS.map { player2.receiveCard(it) }

        assertThat(player1.compareScore(player2)).isEqualTo(0)
    }

    @Test
    internal fun `두개의 점수를 비교하여 크면 음수를 반환한다`() {
        val player1 = Player("player1")
        UNDER_21_CARDS.map { player1.receiveCard(it) }

        val player2 = Player("player2")
        OVER_21_CARDS.map { player2.receiveCard(it) }

        assertThat(player1.compareScore(player2)).isLessThan(0)
    }
}
