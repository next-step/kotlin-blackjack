package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.card.DrawStrategy
import blackjack.domain.card.RandomDrawStrategy
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerTest {

    @Test
    fun `초기 카드 2장 받고 해당 카드가 있는지 확인`() {
        // given
        val expectedCard = Card.spadeOf("A")
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        })

        // when
        val player = Challenger("Malibin").deal(deck)

        // then
        assertThat(player.cards.values).isEqualTo(listOf(expectedCard, expectedCard))
    }

    @Test
    fun `초기 카드는 1번만 받을 수 있다`() {
        // given
        val deck = Deck(RandomDrawStrategy())
        val player: Player = Challenger("Malibin").deal(deck)

        // then
        assertThatThrownBy { player.deal(deck) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("deal은 처음 한 번만 할 수 있습니다.")
    }

    @Test
    fun `21점 초과후 hit시 exception`() {
        // given
        val deck = Deck(object : DrawStrategy {
            override fun fetchCard(): Card {
                return Card.spadeOf("K")
            }
        })
        val player: Player = Challenger(PlayerInfo("Malibin", 0), Cards("8", "9", "10"))

        // then
        assertThatThrownBy { player.hit(deck) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("총점(27)이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : ${player.cards}")
    }

    @Test
    fun `플레이어가 BlackJack이면 더이상 플레이 할 수 없다`() {
        // given
        val blackJackCards = Cards("A", "10")
        val dealer: Player = Dealer(cards = blackJackCards)
        val challenger: Player = Challenger(PlayerInfo("", 0), blackJackCards)

        // then
        assertAll(
            { assertThat(dealer.canPlay()).isFalse() },
            { assertThat(challenger.canPlay()).isFalse() }
        )
    }

    @Test
    fun `딜러는 17점 이상이면 플레이 할 수 없다`() {
        // given
        val cards = Cards("5", "5", "7")
        val dealer: Player = Dealer(cards = cards)

        // then
        assertThat(dealer.canPlay()).isFalse()
    }
}
