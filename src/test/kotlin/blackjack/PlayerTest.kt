package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.Deck
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {

    class FakeCardDeck : Deck {
        override fun popCard(): Card {
            return Card.newInstance(CardType.CLUBS, CardNumber.TWO)
        }
    }

    @DisplayName(value = "게임 배팅 후, hit 진행 하면, 전체 score가 계산되어야한다.  ")
    @Test
    fun checkCardPoolSize() {
        val player = Player.newInstance("조남재").apply {
            gameBatting(FakeCardDeck())
            hit(FakeCardDeck())
        }
        val expect = CardNumber.TWO.score + CardNumber.TWO.score + CardNumber.TWO.score
        assertThat(player.getScore()).isEqualTo(expect)
    }
}
