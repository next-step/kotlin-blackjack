package blackjack.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "a,", " , a"])
    fun `참가자 이름에 빈문자열이나 공백을 입력하면 IllegalArgumentException이 발생한다`(input: String) {
        Assertions.assertThatThrownBy {
            Players.from(input)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a,b", "참가자1, 참가자2", "!!,$$"])
    fun `,로 구분된 참가자 이름을 두 개 입력하면 두 개의 Player를 가진 Players 객체가 생성된다`(input: String) {
        val players = Players.from(input)

        assertThat(players).isNotNull
        assertThat(players.players.size).isEqualTo(2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a ", "참    가   자1"])
    fun `참가자 이름 중에 공백이나 탭 문자가 들어있는 경우 trim 처리되어 참가자 이름이 입력된다`(input: String) {
        val players = Players.from(input)

        assertThat(players).isNotNull
        assertThat(players.players.size).isEqualTo(1)
        assertThat(players.players[0].name).isEqualTo(input.trim())
    }
}
