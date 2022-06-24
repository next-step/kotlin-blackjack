package blackjack.dealer

import blackjack.card.Deck
import blackjack.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    fun `요청한 카드 갯수만큼 카드를 뽑아 줄 수 있다`(count: Int) {
        val deck = Deck.init()
        val dealer = Dealer(deck)
        val player = Player("pang")

        dealer.drawTo(count, player)

        assertThat(player.myCards().size).isEqualTo(count)
    }
}
