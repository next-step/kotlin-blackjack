package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.domain.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PlayersTest {

    @ParameterizedTest(name = "이름 List를 통해 Players 객체를 만들 수 있다")
    @MethodSource("makePlayerListByStringListTest")
    fun `이름 List를 통해 Players 객체를 만들 수 있다`(nameList: List<String>, expected: List<Player>) {
        val playerList = Players.getPlayerListByNames(nameList)
        assertThat(playerList).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Player 가변인자를 통해 Players 객체를 만들 수 있다")
    @MethodSource("makePlayersByVararg")
    fun `Player 가변인자를 통해 Players 객체를 만들 수 있다`(player1: Player, player2: Player, expected: Players) {
        assertThat(Players.of(player1, player2)).isEqualTo(expected)
    }

    @Test
    fun `CardDeck으로부터 Player들에게 카드를 각각 하나씩 나눠줄 수 있다`() {
        val players = Players.of(Player(Name.from("seunghwan")), Player(Name.from("Seo")))

        players.eachAcceptCards(CardDeck())

        players.forEach {
            assertThat(it.cards.count()).isEqualTo(1)
        }
    }

    companion object {
        @JvmStatic
        fun makePlayerListByStringListTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf("pobi", "jason"), listOf(Player(Name.from("pobi")), Player(Name.from("jason")))),
                Arguments.of(
                    listOf("seunghwan", "seo"),
                    listOf(Player(Name.from("seunghwan")), Player(Name.from("seo")))
                )
            )
        }

        @JvmStatic
        fun makePlayersByVararg(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Player(Name.from("pobi")),
                    Player(Name.from("jason")),
                    Players(listOf(Player(Name.from("pobi")), Player(Name.from("jason"))))
                ),
                Arguments.of(
                    Player(Name.from("seunghwan")),
                    Player(Name.from("seo")),
                    Players(listOf(Player(Name.from("seunghwan")), Player(Name.from("seo"))))
                ),
            )
        }
    }
}
