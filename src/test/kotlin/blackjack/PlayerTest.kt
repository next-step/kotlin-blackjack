package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlayerTest {
    private val player = Player("kasha")
    private val player2 = Player("nextstep")
    private val players = Players(listOf(player, player2))

    @Test
    fun `점수가 21점을 넘으면 죽는다`() {
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.JACK))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.QUEEN))

        assertTrue(player.isDead())
    }

    @Test
    fun `힛을 이용하면 모든 플레이어에게 카드를 2장 부여한다`() {
        players.hit(CardExtractor())

        assertThat(player.cardDeck.cards.size).isEqualTo(2)
        assertThat(player2.cardDeck.cards.size).isEqualTo(2)
    }
}
