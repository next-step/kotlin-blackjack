package blackjack.domain.player

import blackjack.domain.bet.Bet
import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가지고 있다`() {
        val name = "vivian"
        val player = Player(name, 1000.0, `starting cards`())

        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `플레이어는 베팅 금액을 지정해야 한다`() {
        val bet = 1000.0
        val player = Player("vivian", bet, `starting cards`())

        assertThat(player.bet).isEqualTo(Bet(bet))
    }

    @Test
    fun `플레이어는 2장의 카드로 시작한다`() {
        val player = Player()

        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어가 2장의 카드로 시작하지 않을 경우 IllegalArgumentException 이 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Player(`starting cards`() + listOf(Card.Two(CardSuit.CLOVER))) }
    }

    @Test
    fun `플레이어는 핸드에 카드를 추가할 수 있다`() {
        val player = Player()
        val additionalCard = Card.Two(CardSuit.CLOVER)

        player.addCardToHand(additionalCard)

        assertThat(player.cards.size).isEqualTo(3)
        assertThat(player.cards[2]).isSameAs(additionalCard)
    }
}

private fun `starting cards`() = listOf(Card.Two(CardSuit.DIAMOND), Card.Ace(CardSuit.SPADE))

private fun Player(cards: List<Card> = `starting cards`()) = Player("vivian", 1000.0, cards)
