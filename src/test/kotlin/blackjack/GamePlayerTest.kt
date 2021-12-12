package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.GamePlayer
import blackjack.domain.Name
import blackjack.domain.state.Hit
import blackjack.domain.strategy.draw.HitDrawStrategy
import blackjack.state.CARD_HEART_ACE
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamePlayerTest {

    @Test
    fun `GamePlayer는 21점을 이상이면 카드를 받을 수 없다`() {
        val cards1 = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit1 = Hit(cards1)
        val gamePlayer1 = GamePlayer(name = Name.from("sh1"), state = hit1)
        assertThat(gamePlayer1.canHit()).isTrue

        val cards2 = Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE))
        val hit2 = Hit(cards2)
        val gamePlayer2 = GamePlayer(name = Name.from("sh2"), state = hit2)
        assertThat(gamePlayer2.canHit()).isFalse
    }

    @Test
    fun `GamePlayer가 처음에 뽑은 두장의 카드가 21이하이면 카드를 받을 수 있다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit = Hit(cards)

        val dealer = GamePlayer(name = Name.from("sh"), state = hit)
            .draw(CardDeck(), HitDrawStrategy)

        assertThat(dealer.cards.cards.size).isEqualTo(3)
    }
}
