package blackjack.business.participants

import blackjack.business.card.Rank
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {

    @ParameterizedTest
    @CsvSource(value = ["ACE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10", "JACK,11", "QUEEN,12", "KING,13"])
    fun `Rank의 index로 Rank를 찾는다`(rank: Rank, score: Int) {
        // when, then
        Rank.from(score) shouldBe rank
    }
}
