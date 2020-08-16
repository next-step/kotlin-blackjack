package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.Cards
import blackjack.model.player.GamePlayer
import blackjack.model.status.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GamePlayerTest {

    @DisplayName(value = "게임 배팅 후,hit 진행 하면,전체 score가 계산되어야한다.")
    @Test
    fun checkCardPoolSize() {
        val cardList = listOf(Card(CardType.DIAMONDS, CardNumber.TWO), Card(CardType.HEARTS, CardNumber.TWO))
        val cardDeck = CardDeck(cardList.toMutableSet())

        val player = GamePlayer("조남재").apply {
            gameBatting(cardDeck.popTwoCard())
        }

        val expect = cardList.sumBy { it.getScore() }
        assertThat(player.getScore()).isEqualTo(Score(expect))
    }

    @DisplayName(value = "Player가 21이 넘지 않으경우,hit 을 할수 있다.")
    @Test
    fun checkPlayerCanHit() {
        val cardList = listOf(Card(CardType.DIAMONDS, CardNumber.ONE), Card(CardType.HEARTS, CardNumber.KING))
        val player = GamePlayer("조남재", Cards(cardList.toMutableSet()))

        assertThat(player.canMoreCard()).isTrue()
    }
}
