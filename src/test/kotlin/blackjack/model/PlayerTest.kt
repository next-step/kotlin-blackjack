package blackjack.model

import blackjack.model.Denomination.ACE
import blackjack.model.Denomination.JACK
import blackjack.model.Denomination.QUEEN
import blackjack.model.Denomination.TEN
import blackjack.model.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    internal fun `플레이어 이름을 입력받는다`() {
        val name = "jason"
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    internal fun `카드를 뽑으면 플레이어 카드목록에 추가된다`() {
        val player = Player("jason")
        val deck = CardDeck()
        player.addCards(deck.getCards(2))
        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    internal fun `플레이어가 가진 카드 숫자를 합친다`() {
        val player = Player("jason")
        player.addCard(Card.of(SPADE, TEN))
        player.addCard(Card.of(SPADE, QUEEN))
        assertThat(player.getScore()).isEqualTo(20)
    }

    @Test
    internal fun `Ace는 1 또는 11로 계산한다`() {
        TODO("결과점수 계산 알고리즘 구현")
    }

    @Test
    internal fun `카드 합이 21을 넘을 경우 카드를 받지 못한다`() {
        val player = Player("jason")
        player.addCard(Card.of(SPADE, ACE))
        player.addCard(Card.of(SPADE, JACK))
        player.addCard(Card.of(SPADE, QUEEN))
        assertThrows<IllegalStateException> { player.addCard(Card.of(SPADE, ACE)) }
    }
}
