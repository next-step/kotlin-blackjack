package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.GamePlayer
import blackjack.domain.Name
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.HitDrawStrategy
import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy
import blackjack.state.CARD_HEART_ACE
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamePlayerTest {

    @Test
    fun `GamePlayer는 21점 이상이면 카드를 받을 수 없다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_ACE))
        val hit = Hit(cards)
        val gamePlayer = GamePlayer(name = Name.from("sh2"), state = hit)
        assertThat(gamePlayer.state.canHit(GamePlayerHittableStrategy)).isFalse
    }

    @Test
    fun `GamePlayer가 처음에 뽑은 두장의 카드가 21 미만이면 카드를 받을 수 있다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit = Hit(cards)

        val gamePlayer = GamePlayer(name = Name.from("sh"), state = hit)
            .draw(CardDeck(), HitDrawStrategy)

        assertThat(gamePlayer.cards.cards.size).isEqualTo(3)
    }

    @Test
    fun `GamePlayer는 stay()할 수 있다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit = Hit(cards)

        val gamePlayer = GamePlayer(name = Name.from("sh"), state = hit)
            .draw(CardDeck(), HitDrawStrategy)
            .stay()

        assertThat(gamePlayer.state).isInstanceOf(Stay::class.java)
    }
}
