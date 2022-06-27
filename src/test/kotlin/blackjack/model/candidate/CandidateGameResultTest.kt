package blackjack.model.candidate

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("참가자 게임 결과 테스트")
class CandidateGameResultTest {

    @Test
    fun `참가자 승패 결과가 정상적으로 생성`() {
        // given
        val dealer1 = Dealer()
        dealer1.receiveCard(Card(CardSymbol.스페이드, CardNumber.TWO))

        val dealer2 = Dealer()
        dealer2.receiveCard(Card(CardSymbol.스페이드, CardNumber.THREE))

        val player1 = Player.from("aiden1", 1)
        player1.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        val player2 = Player.from("aiden2", 1)
        player2.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        // when
        val resultOfPlayer1AndDealer1 = CandidateGameResult.of(player1, dealer1)
        val resultOfPlayer1AndDealer2 = CandidateGameResult.of(player1, dealer2)
        val resultOfPlayer2AndDealer1 = CandidateGameResult.of(player2, dealer1)
        val resultOfPlayer2AndDealer2 = CandidateGameResult.of(player2, dealer2)

        // then
        assertAll(
            "candidate game result test",
            { assertThat(resultOfPlayer1AndDealer1.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer1AndDealer1.lostCount).isEqualTo(0) },

            { assertThat(resultOfPlayer1AndDealer2.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer1AndDealer2.lostCount).isEqualTo(1) },

            { assertThat(resultOfPlayer2AndDealer1.winCount).isEqualTo(1) },
            { assertThat(resultOfPlayer2AndDealer1.lostCount).isEqualTo(0) },

            { assertThat(resultOfPlayer2AndDealer2.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer2AndDealer2.lostCount).isEqualTo(0) },
        )
    }
}
