package blackjack.dto

import blackjack.model.candidate.Candidates
import blackjack.model.candidate.Dealer
import blackjack.model.candidate.Player
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("참가자 게임 결과 컬렉션 테스트")
class CandidateGameResultsDtoTest {

    @Test
    fun `참가자 게임 결과 생성시 딜러가 존재하지 않으면 예외 발생`() {
        // given
        val player1 = Player.from("aiden1", 1)
        val player2 = Player.from("aiden2", 1)

        val candidates = Candidates(listOf(player1, player2))

        // when, then
        val exception = assertThrows<IllegalArgumentException> { CandidateGameResultsDto.from(candidates) }
        assertThat(exception.message).isEqualTo("딜러가 존재하지 않습니다.")
    }

    @Test
    fun `딜러, 플레이어 게임 결과가 정상적으로 생성`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(CardSymbol.스페이드, CardNumber.THREE))

        val player1 = Player.from("aiden1", 10000)
        player1.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        val player2 = Player.from("aiden2", 20000)
        player2.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        val player3 = Player.from("aiden3", 30000)
        player3.receiveCard(Card(CardSymbol.하트, CardNumber.FOUR))

        val candidates = Candidates(listOf(dealer, player1, player2, player3))

        // when
        val candidateGameResults = CandidateGameResultsDto.from(candidates)

        // then
        val (resultOfDealer, resultOfPlayer1, resultOfPlayer2, resultOfPlayer3) = candidateGameResults.results

        assertAll(
            "candidate game results test",
            { assertThat(resultOfDealer.profit).isEqualTo(-20000.0) },
            { assertThat(resultOfPlayer1.profit).isEqualTo(-10000.0) },
            { assertThat(resultOfPlayer2.profit).isEqualTo(0.0) },
            { assertThat(resultOfPlayer3.profit).isEqualTo(30000.0) },
        )
    }
}
