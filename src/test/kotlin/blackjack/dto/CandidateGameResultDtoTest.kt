package blackjack.dto

import blackjack.model.candidate.Dealer
import blackjack.model.candidate.Player
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("참가자 게임 결과 테스트")
class CandidateGameResultDtoTest {

    @Test
    fun `플레이어의 카드 점수가 21을 초과할 경우 베팅 금액을 모두 잃음`() {
        // given
        val dealer = Dealer()

        // when
        val player = Player.from("aiden", 10000)
        player.receiveCard(Card(CardSymbol.하트, CardNumber.TEN))
        player.receiveCard(Card(CardSymbol.클로버, CardNumber.TEN))
        player.receiveCard(Card(CardSymbol.다이아, CardNumber.TWO))

        // then
        assertThat(CandidateGameResultDto.of(player, dealer).profit).isEqualTo(-10000.0)
    }

    @Test
    fun `처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 일점오배를 받음`() {
        // given
        val dealer = Dealer()

        // when
        val player = Player.from("aiden", 10000)
        player.receiveCard(Card(CardSymbol.하트, CardNumber.TEN))
        player.receiveCard(Card(CardSymbol.클로버, CardNumber.ACE))

        // then
        assertThat(CandidateGameResultDto.of(player, dealer).profit).isEqualTo(15000.0)
    }

    @Test
    fun `딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받음`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(CardSymbol.하트, CardNumber.TEN))
        dealer.receiveCard(Card(CardSymbol.클로버, CardNumber.ACE))

        // when
        val player = Player.from("aiden", 10000)
        player.receiveCard(Card(CardSymbol.다이아, CardNumber.TEN))
        player.receiveCard(Card(CardSymbol.스페이드, CardNumber.ACE))

        // then
        assertThat(CandidateGameResultDto.of(player, dealer).profit).isEqualTo(0.0)
    }

    @Test
    fun `참가자 게임 결과가 정상적으로 생성`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(CardSymbol.스페이드, CardNumber.THREE))

        val player1 = Player.from("aiden1", 10000)
        player1.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        val player2 = Player.from("aiden2", 20000)
        player2.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        val player3 = Player.from("aiden3", 30000)
        player3.receiveCard(Card(CardSymbol.하트, CardNumber.FOUR))

        // when
        val resultOfPlayer1 = CandidateGameResultDto.of(player1, dealer)
        val resultOfPlayer2 = CandidateGameResultDto.of(player2, dealer)
        val resultOfPlayer3 = CandidateGameResultDto.of(player3, dealer)

        // then
        assertAll(
            "candidate game result test",
            { assertThat(resultOfPlayer1.profit).isEqualTo(-10000.0) },
            { assertThat(resultOfPlayer2.profit).isEqualTo(0.0) },
            { assertThat(resultOfPlayer3.profit).isEqualTo(30000.0) },
        )
    }
}
