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
    @ValueSource(strings = ["a,b,c", "참가자1, 참가자2, 참가자3", "!!,$$, ]]"])
    fun `,로 구분된 참가자 이름을 세 개 입력하면 세 개의 Player를 가진 Players 객체가 생성된다`(input: String) {
        val players = Players.from(input)

        assertThat(players).isNotNull
        assertThat(players.players.size).isEqualTo(3)
    }
}
