package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어가 카드를 받으면 카드 수는 1 증가해야 한다`() {
        // given
        val player = Player("jay")

        // when
        player.receivedCard(Card.of(CardSuit.Heart, CardRank.Ace))

        // then
        assertThat(player.cards.cards.size).isEqualTo(1)
    }

}
