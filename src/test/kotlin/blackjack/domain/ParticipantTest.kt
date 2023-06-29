package blackjack.domain

import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult
import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantTest {

    private lateinit var player: Participant

    @BeforeEach
    fun setup() {
        player = Participant(
            name = "test",
            cards = Cards(
                listOf(
                    Card(rank = Rank.JACK, symbol = Symbol.SPADES),
                    Card(rank = Rank.FIVE, symbol = Symbol.HEARTS)
                )
            ),
            condition = Condition.PLAY
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 6, 7, 8])
    fun `자신의 카드 점수의 합과 상대방의 카드 점수의 합을 비교해 높으면 승을 반환`(value: Int) {

        val otherScore = Score(value = value)
        player.determineResult(otherScore) shouldBe MatchResult.WIN
    }

    @ParameterizedTest
    @ValueSource(ints = [17, 18, 19, 20, 21])
    fun `자신의 카드 점수의 합과 상대방의 카드 점수의 합을 비교해 낮다면 패를 반환`(value: Int) {

        val otherScore = Score(value = value)
        player.determineResult(otherScore) shouldBe MatchResult.LOSE
    }

    @Test
    fun `자신의 카드 점수의 합과 상대방의 카드 점수의 합을 비교해 같다면 무승부를 반환`() {

        val otherScore = Score(value = 15)
        player.determineResult(otherScore) shouldBe MatchResult.DRAW
    }
}
