package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {
    @ParameterizedTest
    @ValueSource(strings = ["test1", "test1,test2,test3,test4,test5,test6,test7,test8,test9"])
    fun `Players 인원은 최소 2명에서 최대 8명이어야 하고 그렇지않으면 예외가 발생한다`(playerNames: String) {
        assertThrows<IllegalArgumentException> {
            Players(playerNames)
        }
    }

    @Test
    fun `유효한 이름 검증`() {
        val players = Players("test1,test2,test3,test4")
        assertThat(players.players.size).isEqualTo(4)
        assertThat(players.players[0].name).isEqualTo(PlayerName("test1"))
        assertThat(players.players[1].name).isEqualTo(PlayerName("test2"))
        assertThat(players.players[2].name).isEqualTo(PlayerName("test3"))
        assertThat(players.players[3].name).isEqualTo(PlayerName("test4"))
    }

    @ParameterizedTest
    @ValueSource(strings = ["test1,,test2", "test1,  test2", "test1:test2"])
    fun `유효하지 않은 이름으로 Players를 생성하려고 하면 예외가 발생한다`(playerNames: String) {
        val exception = assertThrows<IllegalArgumentException> {
            Players(playerNames)
        }

        assertThat(exception.message).isEqualTo("유효하지 않은 이름 형식입니다")
    }
}