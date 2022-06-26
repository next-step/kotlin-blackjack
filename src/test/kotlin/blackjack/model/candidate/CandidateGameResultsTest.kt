package blackjack.model.candidate

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("참가자 게임 결과 컬렉션 테스트")
class CandidateGameResultsTest {

    @Test
    fun `참가자 승패 결과 생성시 딜러가 존재하지 않으면 예외 발생`() {
        // given
        val candidate1 = Candidate.from("aiden1")
        val candidate2 = Candidate.from("aiden2")

        val candidates = Candidates(listOf(candidate1, candidate2))

        // when, then
        val exception = assertThrows<IllegalArgumentException> { CandidateGameResults.from(candidates) }
        assertThat(exception.message).isEqualTo("딜러가 존재하지 않습니다.")
    }

    @Test
    fun `딜러, 플레이어 게임 결과가 정상적으로 생성`() {
        // given
        val dealer1 = Dealer()
        dealer1.receiveCard(Card(CardSymbol.스페이드, CardNumber.THREE))

        val candidate1 = Candidate.from("aiden1")
        candidate1.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        val candidate2 = Candidate.from("aiden2")
        candidate2.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        val candidate3 = Candidate.from("aiden3")
        candidate3.receiveCard(Card(CardSymbol.하트, CardNumber.FOUR))

        val candidates = Candidates(listOf(dealer1, candidate1, candidate2, candidate3))

        // when
        val candidateGameResults = CandidateGameResults.from(candidates)

        // then
        val (resultOfDealer, resultOfPlayer1, resultOfPlayer2, resultOfPlayer3) = candidateGameResults.results

        assertAll(
            "candidate game results test",
            { assertThat(resultOfDealer.winCount).isEqualTo(1) },
            { assertThat(resultOfDealer.lostCount).isEqualTo(1) },

            { assertThat(resultOfPlayer1.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer1.lostCount).isEqualTo(1) },

            { assertThat(resultOfPlayer2.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer2.lostCount).isEqualTo(0) },

            { assertThat(resultOfPlayer3.winCount).isEqualTo(1) },
            { assertThat(resultOfPlayer3.lostCount).isEqualTo(0) },
        )
    }
}
