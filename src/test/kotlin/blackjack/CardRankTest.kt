package blackjack

import blackjack.domain.CardRank
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardRankTest {

    @DisplayName("카드의 숫자는 1(Ace)부터 King까지 있다")
    @Test
    fun cardRank() {
        val actual = CardRank.values()

        actual.size shouldBe 13
        actual.shouldContainAll(
            listOf(
                CardRank.ACE, CardRank.TWO, CardRank.THREE,
                CardRank.FOUR, CardRank.FIVE, CardRank.SIX,
                CardRank.SEVEN, CardRank.EIGHT, CardRank.NINE, CardRank.TEN,
                CardRank.JACK, CardRank.QUEEN, CardRank.KING
            )
        )
    }
}
