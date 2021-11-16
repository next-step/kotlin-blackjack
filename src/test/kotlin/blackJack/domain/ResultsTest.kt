package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ResultsTest {
    @Test
    fun `딜러와 플레이어가 합쳐진 GamePlayers 를 넣고, 결과가 맞는지 확인한다`() {
        // given
        val gamePlayers =
            GamePlayers(listOf(
                Player.of("flamme").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.THREE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                },
                Player.of("rain").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
                    this.receiveCard(Card(Suit.SPADES, Denomination.EIGHT))
                },
                Player.of("chacha").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                },
                Dealer().apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                }
            ))

        // when
        val results = Results.from(gamePlayers)
        val dealerResult = results.dealerResult

        // then
        assertAll({
            assertThat(dealerResult.win).isEqualTo(1)
            assertThat(dealerResult.lose).isEqualTo(1)
            assertThat(dealerResult.draw).isEqualTo(1)
        })
    }

    @Test
    fun `딜러가 없다면 에러`() {
        // given
        val gamePlayers =
            GamePlayers(listOf(
                Player.of("flamme").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.THREE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                },
                Player.of("rain").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
                    this.receiveCard(Card(Suit.SPADES, Denomination.EIGHT))
                },
                Player.of("chacha").apply {
                    this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                    this.receiveCard(Card(Suit.SPADES, Denomination.KING))
                }
            ))

        // when
        val actual = runCatching {
            Results.from(gamePlayers)
        }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("딜러가 없습니다.")
    }
}
