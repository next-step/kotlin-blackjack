package blackjack.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerResultTest {

    @Test
    @DisplayName("참여자 입력이 공백으로 들어왔을 경우 IllegalArgumentException 오류")
    fun test1() {
        val names = ""
        assertThrows<IllegalArgumentException> { PlayerResult(names) }
    }

    @Test
    @DisplayName("참여자 입력이 홍길동, 심봉사로 들어왔을 경우 참여자는 [홍길동, 심봉사]")
    fun test2() {
        val names = "홍길동, 심봉사"
        val players = PlayerResult(names).players

        assertThat(players.map { it.name }).isEqualTo(listOf("홍길동", "심봉사"))
    }
}
