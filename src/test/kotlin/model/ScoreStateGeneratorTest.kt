package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScoreStateGeneratorTest {
    @Test
    fun `카드 값 합이 21 초과 시 버스트로 판단한다`() {
        // given
        val players = Players()
        players.create(Names("Tester1"))
        players.values[0].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
        players.values[0].receiveCard(Card(CardNumber.TWO, CardShape.HEARTS))

        // when
        val scoreState = ScoreStateGenerator().generate(players.values[0].cards)

        // then
        assertThat(scoreState).isEqualTo(ScoreState.BUST)
    }

    @Test
    fun `카드 값 합이 21일 경우 블랙잭으로 판단한다`() {
        // given
        val players = Players()
        players.create(Names("Tester1"))
        players.values[0].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.ACE, CardShape.HEARTS))

        // when
        val scoreState = ScoreStateGenerator().generate(players.values[0].cards)

        // then
        assertThat(scoreState).isEqualTo(ScoreState.BLACKJACK)
    }

    @Test
    fun `카드 값 합이 21 미만 시 히트로 판단한다`() {
        // given
        val players = Players()
        players.create(Names("Tester1"))
        players.values[0].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))

        // when
        val scoreState = ScoreStateGenerator().generate(players.values[0].cards)

        // then
        assertThat(scoreState).isEqualTo(ScoreState.HIT)
    }
}
