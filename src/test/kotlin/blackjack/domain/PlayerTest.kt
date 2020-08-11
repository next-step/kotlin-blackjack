package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

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
        val player = Player("Malibin").deal(deck)

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
        val player = Player("Malibin", Cards.denominationsOf("8", "9", "10"))

        // then
        assertThatThrownBy { player.hit(deck) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("총점(27)이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : ${player.cards}")
    }

    @Test
    fun `플레이어의 현재 점수 계산`() {
        // given
        val player = Player("Malibin", Cards.denominationsOf("A", "J"))

        // then
        assertThat(player.getScore()).isEqualTo(21)
    }
}
