package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PlayersTest {
    @ParameterizedTest
    @MethodSource("playerNamesProvider")
    fun `플레이어 이름의 리스트로 Players를 만들 수 있다`(names: List<String>) {
        val result = Players.Builder().playerNames(names).build()

        assertThat(result[0]).hasFieldOrPropertyWithValue("name", names[0])
        assertThat(result[1]).hasFieldOrPropertyWithValue("name", names[1])
    }

    @Test
    fun `Players 는 immutable 해야 한다`() {
        val playerList = mutableListOf(Player("sangw0804"))

        val players = Players.Builder().players(playerList).build()

        playerList.add(Player("newPlayer"))

        assertThat(players.size).isEqualTo(1)
    }

    companion object {
        @JvmStatic
        fun playerNamesProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf("sangw0804", "pobi")
                    )
                }
            )
        }
    }
}