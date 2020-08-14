package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerTest {

    @Test
    fun `초기 카드 2장 받고 해당 카드가 있는지 확인`() {
        // given
        val expectedCard = Card.denominationOf("A")
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }

            override fun getDealCards(): List<Card> {
                return listOf(expectedCard, expectedCard)
            }
        }

        // when
        val player = Challenger("Malibin").deal(deck)

        // then
        assertThat(player.cards.values).isEqualTo(listOf(expectedCard, expectedCard))
    }

    @Test
    fun `21점 초과후 hit시 exception`() {
        // given
        val expectedCard = Card.denominationOf("K")
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }

            override fun getDealCards(): List<Card> {
                return emptyList()
            }
        }
        val player = Challenger("Malibin", Cards.denominationsOf("8", "9", "10"))

        // then
        assertThatThrownBy { player.hit(deck) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("총점(27)이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : ${player.cards}")
    }

    @Test
    fun `(플레이어 + 기존 리스트) 하면 플레이어가 맨 앞인 합쳐진 리스트가 나온다`() {
        // given
        val player: Player = Challenger("player1")
        val players: List<Player> = listOf(Challenger("player2"), Challenger("player3"))

        // when
        val newPlayers = player + players

        // then
        assertAll(
            { assertThat(newPlayers).hasSize(3) },
            { assertThat(newPlayers[0].name).isEqualTo(player.name) }
        )
    }

    @Test
    fun `딜러는 17점 이상이면 반드시 Stand 상태이다`() {
        // given
        val cards = Cards(
            listOf(
                Card.denominationOf("5"),
                Card.denominationOf("5"),
                Card.denominationOf("7")
            )
        )
        val dealer: Player = Dealer(cards)

        // then
        assertThat(dealer.state).isEqualTo(State.Stand)
    }
}
