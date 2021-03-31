package blackjack.model.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PlayersTest {
    private val deck = MockDeck()

    @ParameterizedTest
    @MethodSource("playerNamesProvider")
    fun `플레이어 이름의 리스트로 Players를 만들 수 있다`(names: List<String>) {
        val result = Players(names, deck)

        assertThat(result.map { it.name }).containsAll(names)
    }

    @Test
    fun `Players 는 immutable 해야 한다`() {
        val playerList = mutableListOf(Player(deck, "sangw0804"))

        val players = Players(playerList)

        playerList.add(Player(deck, "newPlayer"))

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
