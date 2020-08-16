package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.toCards
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
            gameBatting(cardDeck.popPlayerCardDummy())
        }

        val expect = cardList.sumBy { it.getScore() }
        assertThat(player.getScore()).isEqualTo(Score(expect))
    }

    @DisplayName(value = "Player가 21이상일 경우, hit 을 할 수 없다.")
    @Test
    fun checkPlayerCanNotMoreHit() {
        val cards = listOf(
            Card(CardType.DIAMONDS, CardNumber.ONE),
            Card(CardType.HEARTS, CardNumber.KING)
        ).toCards()
        val player = GamePlayer("조남재", cards)

        assertThat(player.canMoreCard()).isFalse()
    }

    @DisplayName(value = "Player가 21 미일 경우, hit 을 할 수 없다.")
    @Test
    fun checkPlayerCanMoreHit() {
        val cards = listOf(
            Card(CardType.DIAMONDS, CardNumber.QUEEN),
            Card(CardType.HEARTS, CardNumber.KING)
        ).toCards()
        val player = GamePlayer("조남재", cards)

        assertThat(player.canMoreCard()).isTrue()
    }
}
