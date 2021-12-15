package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.GamePlayer
import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.strategy.draw.HitDrawStrategy
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
    fun `CardDeck으로부터 Player들에게 카드를 각각 하나씩 나눠줄 수 있다`() {
        var players = Players.from(listOf(GamePlayer(Name.from("seunghwan")), GamePlayer(Name.from("Seo"))))
        val cardDeck = CardDeck()
        players = players.drawCardEachPlayer(cardDeck, HitDrawStrategy)

        players.players.forEach {
            assertThat(it.cards.cards.count()).isEqualTo(1)
        }
    }

    companion object {
        @JvmStatic
        fun makePlayerListByStringListTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf("pobi", "jason"), listOf(GamePlayer(Name.from("pobi")), GamePlayer(Name.from("jason")))),
                Arguments.of(
                    listOf("seunghwan", "seo"),
                    listOf(GamePlayer(Name.from("seunghwan")), GamePlayer(Name.from("seo")))
                )
            )
        }
    }
}
