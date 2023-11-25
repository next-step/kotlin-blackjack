package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameResult
import blackjack.domain.player.Participant
import blackjack.domain.rule.TestScoringRule
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultsTest {

    @Test
    fun `딜러와 참가자 리스트를 받으면, 전체 참가자의 GameResult 리스트를 반환 - 딜러 결과, 1승 1패`() {
        val dealer = Dealer(TestScoringRule(20))
        val p1 = Participant("p1", TestScoringRule(21))
        val p2 = Participant("p2", TestScoringRule(17))
        val participants = listOf(p1, p2)

        val gameResults = GameResults.results(dealer, participants)

        gameResults.size shouldBe 2
        gameResults[p1] shouldBe GameResult.WIN
        gameResults[p2] shouldBe GameResult.LOSE
    }

    @Test
    fun `딜러와 참가자 리스트를 받으면, 전체 참가자의 GameResult 리스트를 반환 - 딜러 결과, 2패`() {
        val dealer = Dealer(TestScoringRule(22))
        val p1 = Participant("p1", TestScoringRule(21))
        val p2 = Participant("p2", TestScoringRule(17))
        val participants = listOf(p1, p2)

        val gameResults = GameResults.results(dealer, participants)

        gameResults.size shouldBe 2
        gameResults[p1] shouldBe GameResult.WIN
        gameResults[p2] shouldBe GameResult.WIN
    }

    @Test
    fun `딜러와 참가자 리스트를 받으면, 전체 참가자의 GameResult 리스트를 반환 - 딜러 결과, 1승 1패 1무`() {
        val dealer = Dealer(TestScoringRule(20))
        val p1 = Participant("p1", TestScoringRule(21))
        val p2 = Participant("p2", TestScoringRule(17))
        val p3 = Participant("p3", TestScoringRule(20))
        val participants = listOf(p1, p2, p3)

        val gameResults = GameResults.results(dealer, participants)

        gameResults.size shouldBe 3
        gameResults[p1] shouldBe GameResult.WIN
        gameResults[p2] shouldBe GameResult.LOSE
        gameResults[p3] shouldBe GameResult.TIE
    }
}