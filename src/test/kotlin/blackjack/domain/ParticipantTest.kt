package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ParticipantTest {
    @Test
    fun `플레이어는 베팅금액 할당 가능하다`() {
        val participant = Player.from("playerA")

        participant.betting(Money(1_000))

        assertThat(participant.bettingMoney).isEqualTo(Money(1_000))
    }

    @Test
    fun `블랙잭을 판정한다`() {
        val player = Player.from("playerA")
        player.receive(Deck(listOf(Card(CardRank.ACE, Suit.DIAMOND), Card(CardRank.KING, Suit.CLUB))))

        val actual = player.isBlackJack()

        assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["1_000, 2_000, 1_000", "1_000, 0, -1_000"])
    fun `수익금을 계산한다`(bettingMoney: Int, target: Int, expected: Int) {
        val player = Player.from("playerA")
        player.betting(Money(bettingMoney))

        val actual = player.profit(Money(target))

        assertThat(actual).isEqualTo(Money(expected))
    }

    @Test
    fun `evenMoney 계산`() {
        val player = Player.from("playerA")
        player.betting(Money(1_000))

        val actual = player.evenMoney()

        assertThat(actual).isEqualTo(Money(1_500))
    }

}