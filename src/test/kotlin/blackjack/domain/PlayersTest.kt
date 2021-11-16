package blackjack.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        assertThat(players.items.size).isEqualTo(2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a ", "참    가   자1"])
    fun `참가자 이름 중에 공백이나 탭 문자가 들어있는 경우 trim 처리되어 참가자 이름이 입력된다`(input: String) {
        val players = Players.from(input)

        assertThat(players).isNotNull
        assertThat(players.items.size).isEqualTo(1)
        assertThat(players.items[0].name).isEqualTo(input.trim())
    }

    @ParameterizedTest
    @MethodSource("getBlankedNames")
    fun `names 의 항목 중에 빈문자열이나 공백 이름이 존재하는 경우 buildPlayer()를 호출하면 IllegalArgumentException이 발생한다`(names: List<String>) {
        Assertions.assertThatThrownBy {
            Players.buildPlayer(names)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun getBlankedNames(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(listOf("", "a")),
            Arguments.of(listOf("a", " ")),
        )
    }

    @ParameterizedTest
    @MethodSource("getOrdinaryPlayerNames")
    fun `공백이나 빈문자열을 제외한 length 1이상의 name들로 생성한 names buildPlayer()를 호출하면 name이 trim된 player이름을 가진 Players가 생성된다`(names: List<String>) {
        val players = Players.buildPlayer(names)

        assertThat(players).isNotNull
        assertThat(players.size).isEqualTo(2)
        assertThat(players[0].name).isEqualTo(names[0].trim())
        assertThat(players[1].name).isEqualTo(names[1].trim())
    }

    private fun getOrdinaryPlayerNames(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(listOf("a", "b")),
            Arguments.of(listOf("poby ", "j a s o n")),
        )
    }
}
