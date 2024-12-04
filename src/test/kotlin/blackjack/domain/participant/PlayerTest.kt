package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
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

    @Test
    fun `플레이어의 카드 합이 버스트 되면 카드를 더이상 받을 수 없습니다`() {
        // given
        val cards = mutableListOf(
            Card.of(CardSuit.Heart, CardRank.Ten),
            Card.of(CardSuit.Heart, CardRank.Nine),
            Card.of(CardSuit.Heart, CardRank.Three),
        )
        val player = Player("jay", Cards(cards))

        // when
        val result = player.canReceiveCard()

        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `플레이어가 블랙잭이 되면 카드를 더이상 받을 수 없습니다`() {
        // given
        val cards = mutableListOf(
            Card.of(CardSuit.Heart, CardRank.Ten),
            Card.of(CardSuit.Heart, CardRank.Nine),
            Card.of(CardSuit.Heart, CardRank.Two),
        )
        val player = Dealer(cards = Cards(cards))

        // when
        val result = player.canReceiveCard()

        // then
        assertThat(result).isFalse()
    }

}
