package blackjack.domain.card

import blackjack.domain.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class HandsTest {
    @DisplayName("갖고 있는 카드 점수의 합 반환")
    @Test
    fun calculateScore() {
        val cards = mutableListOf(
            createCard(CardSymbol.FIVE.name, CardSuit.SPADE.name),
            createCard(CardSymbol.SEVEN.name, CardSuit.SPADE.name)
        )

        val actual = Hands(cards).calculateScore()

        assertThat(actual.score).isEqualTo(12)
    }

    @DisplayName("Ace를 갖고 있고 점수가 11점 이하인 경우 10 추가된 합 반환")
    @Test
    fun applyAceCondition() {
        val cards = mutableListOf(
            createCard(CardSymbol.ACE.name, CardSuit.SPADE.name),
            createCard(CardSymbol.SEVEN.name, CardSuit.SPADE.name)
        )

        val actual = Hands(cards).calculateScore()

        assertThat(actual.score).isEqualTo(18)
    }
}
