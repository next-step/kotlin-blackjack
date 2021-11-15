package blackjack.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "  ", "   "])
    fun `빈문자열이나 공백으로 이루어진 문자열로 참가자 이름을 입력하면 IllegalArgumentException이 발생한다`(input: String) {
        Assertions.assertThatThrownBy {
            Player(input, PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator()))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "참가자", "Player"])
    fun `공백이 아닌 문자열로 참가자 이름을 입력하면 정상적으로 Player객체를 생성할 수 있다`(input: String) {
        val player = Player(input, PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator()))

        assertThat(player).isNotNull
        assertThat(player.name).isEqualTo(input)
    }
}
