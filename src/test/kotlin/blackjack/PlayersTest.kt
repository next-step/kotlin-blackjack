package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
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

    @ParameterizedTest(name = "이름 List를 통해 Player List를 만들 수 있다")
    @MethodSource("makePlayerListByStringListTest")
    fun `이름 List를 통해 Player List를 만들 수 있다`(nameList: List<String>, expected: List<Player>) {
        val playerList = Players.getPlayerListByNames(nameList)
        println(playerList == expected)
        assertThat(playerList).isEqualTo(expected)
    }

    @Test
    fun `Players는 + 연산자를 통해 플레이어를 추가할 수 있다`() {
        var players = Players.from(
            listOf(
                Player.of(Name.from("player1")),
                Player.of(Name.from("player2"))
            )
        )
        players += Player.of(name = Name.from("player3"))

        assertThat(players.players).isEqualTo(
            Players.from(
                listOf(
                    Player.of(Name.from("player1")),
                    Player.of(Name.from("player2")),
                    Player.of(Name.from("player3"))
                )
            ).players
        )
    }

    @Test
    fun `CardDeck으로부터 Player들에게 카드를 각각 하나씩 나눠줄 수 있다`() {
        val players = Players.from(listOf(Player(Name.from("seunghwan")), Player(Name.from("Seo"))))
        val cardDeck = CardDeck()
        players.eachAcceptCards(cardDeck)

        players.players.forEach {
            assertThat(it.cards.cards.count()).isEqualTo(1)
        }
    }

    @Test
    fun `Players는 Dealer를 포함하고 있는지 알 수 있다`() {
        var players = Players.from(listOf(Player(Name.from("a")), Player(Name.from("b"))))
        players += Dealer()

        assertThat(players.isContainDealer()).isTrue
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
    }
}
