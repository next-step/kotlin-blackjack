package blackjack.domain.member

import blackjack.domain.Bet
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.GameState
import blackjack.domain.Number
import blackjack.domain.Sharp
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ResultPlayerTest {

    @ParameterizedTest
    @CsvSource(value = ["WIN:1000", "LOSE:-1000", "WIN_BLACKJACK:1500"], delimiter = ':')
    fun `상태에 맞는 상금을 반환한다`(gameState: GameState, expected: Int) {
        // given
        val resultPlayer = ResultPlayer(
            Player(
                "koi",
                Cards(Card(Number.ACE, Sharp.HEART), Card(Number.JACK, Sharp.CLOVER)),
                Bet.of(1000)
            ),
            gameState
        )

        // when
        val benefit = resultPlayer.benefit()

        // then
        assertThat(benefit).isEqualTo(expected.toDouble())
    }
}
