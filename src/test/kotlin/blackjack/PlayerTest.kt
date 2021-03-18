package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlayerTest {
    private val player = Player("kasha")

    @Test
    fun `card의 상태 text를 검증한다`() {
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.ACE))
        player.cardDeck.add(Card(CardType.HEART, CardNumber.SEVEN))

        assertThat(player.getCardText()).isEqualTo("kasha카드: A스페이드, 7하트")
    }

    @Test
    fun `점수가 21점을 넘으면 죽는다`() {
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.JACK))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.QUEEN))

        assertTrue(player.isDead())
    }
}
