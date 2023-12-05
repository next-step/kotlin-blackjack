package blackjack.domain

import fixtures.createBlackjackCards
import fixtures.createBustCards
import fixtures.createCard
import fixtures.createCards
import fixtures.createUnderBlackjackCards
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `주어진 카드 점수가 Blackjack인 경우 확인`() {
        // given
        val cards = createBlackjackCards()
        // when
        val isBlackjack = Score(cards).isBlackjack()
        // then
        assertThat(isBlackjack).isTrue()
    }

    @Test
    fun `주어진 카드 점수가 Blackjack이 아닌 경우 확인`() {
        // given
        val cards = createUnderBlackjackCards()
        // when
        val isBlackjack = Score(cards).isBlackjack()
        // then
        isBlackjack shouldBe false
    }

    @Test
    fun `주어진 카드 점수가 Bust인 경우 확인`() {
        // given
        val cards = createBustCards()
        // when
        val isBust = Score(cards).isBust()
        // then
        isBust shouldBe true
    }

    @Test
    fun `주어진 카드 점수와 Blackjack 점수와의 차이 계산`() {
        // given
        val cards = createCards(
            createCard(Suit.SPADES, Denomination.TEN),
            createCard(Suit.SPADES, Denomination.TEN),
            createCard(Suit.SPADES, Denomination.TEN),
        )
        // when
        val gap = Score(cards).gapFromBlackjack()
        // then
        gap shouldBe 9
    }
}
