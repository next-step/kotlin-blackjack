package blackjack.model.gamer

import blackjack.model.BetMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GamersTest {
    private val deck = MockDeck()

    @ParameterizedTest
    @MethodSource("gamerNamesProvider")
    fun `플레이어 이름과 베팅 금액의 리스트로 Players를 만들 수 있다`(nameAndBets: List<Pair<String, Int>>) {
        val result = Gamers(nameAndBets, deck)

        assertThat(result.map { it.name }).containsAll(nameAndBets.map { it.first })
    }

    @Test
    fun `Players 는 immutable 해야 한다`() {
        val gamerList = mutableListOf(Player(deck, "sangw0804", BetMoney(1000)))

        val gamers = Gamers(gamerList)

        gamerList.add(Player(deck, "newPlayer", BetMoney(1000)))

        assertThat(gamers.size).isEqualTo(1)
    }

    companion object {
        @JvmStatic
        fun gamerNamesProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(
                            ("sangw0804" to 1000),
                            ("pobi" to 2000)
                        )
                    )
                }
            )
        }
    }
}
