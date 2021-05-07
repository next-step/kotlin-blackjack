package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.MockDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GamersTest {
    private val deck = MockDeck()

    @ParameterizedTest
    @MethodSource("gamerNamesProvider")
    fun `플레이어 이름과 베팅 금액의 리스트로 Players를 만들 수 있다`(sources: List<PlayerBuildSource>) {
        val result = Gamers(*sources.toTypedArray())

        assertThat(result.map { it.name }).containsAll(sources.map { it.name })
    }

    @Test
    fun `Players 는 immutable 해야 한다`() {
        val gamerList = mutableListOf(Player("sangw0804", BetMoney(1000)))

        val gamers = Gamers(gamerList)

        gamerList.add(Player("newPlayer", BetMoney(1000)))

        assertThat(gamers.size).isEqualTo(1)
    }

    companion object {
        @JvmStatic
        fun gamerNamesProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(
                            PlayerBuildSource("sangw0804", 1000),
                            PlayerBuildSource("pobi", 2000)
                        )
                    )
                }
            )
        }
    }
}
