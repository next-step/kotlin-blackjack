package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ScoreTest {

    @Test
    @DisplayName("카드 [9, 10]를 가진 선수는 블랙잭 21을 넘지않음")
    fun `Competitors with cards (9, 10) do not exceed blackjack 21`() {
        val cardList = listOf(
            Card(CardNumber.NINE, CardType.CLOVER),
            Card(CardNumber.TEEN, CardType.CLOVER),
        )
        val cards = Cards(cardList)
        val player = Player("홍길동", cards)
        val isOver = Score.isBust(player)

        assertThat(isOver).isFalse
    }

    @Test
    @DisplayName("카드 [10, 10, 10]를 가진 선수는 블랙잭 21을 넘음")
    fun `Players with cards (10, 10, 10) exceed Black Jack 21`() {
        val cardList = listOf(
            Card(CardNumber.TEEN, CardType.CLOVER),
            Card(CardNumber.TEEN, CardType.CLOVER),
            Card(CardNumber.TEEN, CardType.CLOVER)
        )
        val cards = Cards(cardList)
        val player = Player("홍길동", cards)
        val isOver = Score.isBust(player)

        assertThat(isOver).isTrue
    }

    @Test
    @DisplayName("카드 [ACE, ACE, ACE]를 가진 선수는 최종 점수는 23")
    fun `(ACE, ACE, ACE) The player with the card has a final score of 23`() {
        val cardList = listOf(
            Card(CardNumber.ACE, CardType.CLOVER),
            Card(CardNumber.ACE, CardType.CLOVER),
            Card(CardNumber.ACE, CardType.CLOVER)
        )
        val cards = Cards(cardList)
        val player = Player("홍길동", cards)
        val finalScore = Score.getFinalScore(player)

        assertThat(finalScore).isEqualTo(23)
    }
}
