package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.JACK
import blackjack.domain.Number.QUEEN
import blackjack.domain.Number.THREE
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "\t", "\n"])
    internal fun `플레이어 이름이 공백문자면 예외가 발생한다`(input: String) {
        // given

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Player(input, Cards(Card(ACE, HEART), Card(TWO, CLOVER)))
        }
    }

    @Test
    internal fun `플레이어의 카드가 더해진다`() {
        // given
        val player = Player("user", Cards(Card(ACE, HEART), Card(TWO, CLOVER)))

        // when
        player.addCard(Card(THREE, DIAMOND))

        // then
        assertAll(
            { assertThat(player.cardElements).hasSize(3) },
            {
                assertThat(player.cardElements).containsExactly(
                    Card(ACE, HEART),
                    Card(TWO, CLOVER),
                    Card(THREE, DIAMOND)
                )
            }
        )
    }

    @Test
    internal fun `보유 카드 점수 합을 반환한다`() {
        // given
        val player = Player("user", Cards(Card(ACE, HEART), Card(TWO, CLOVER)))
        // when
        val resultScore = player.resultScore()
        // then
        assertThat(resultScore).isEqualTo(13)
    }

    @Test
    internal fun `블랙잭에 가까운 유저를 반환한다`() {
        // given
        val player1 = Player("user1", Cards(Card(QUEEN, HEART), Card(THREE, CLOVER)))
        val player2 = Player("user2", Cards(Card(QUEEN, HEART), Card(TWO, CLOVER)))

        // when, then
        assertThat(player1.isNearBlackJackThan(player2)).isTrue
    }

    @Test
    internal fun `21점 초과이면 false를 반환한다`() {
        // given
        val player = Player("user", Cards(Card(QUEEN, HEART), Card(JACK, CLOVER), Card(TWO, DIAMOND)))

        // when
        val ableMoreDrawCard = player.ableMoreDrawCard()

        // then
        assertThat(ableMoreDrawCard).isFalse
    }

    @Test
    internal fun `21점 미만이면 true를 반환한다`() {
        // given
        val player = Player("user", Cards(Card(QUEEN, HEART), Card(JACK, CLOVER)))

        // when
        val ableMoreDrawCard = player.ableMoreDrawCard()

        // then
        assertThat(ableMoreDrawCard).isTrue
    }
}
