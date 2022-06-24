package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("플레이어 게임 결과 컬렉션 테스트")
class PlayerGameResultsTest {

    @Test
    fun `플레이어 승패 결과 생성시 딜러가 존재하지 않으면 예외 발생`() {
        // given
        val player1 = Player.from("aiden1")
        val player2 = Player.from("aiden2")

        val players = Players(listOf(player1, player2))

        // when, then
        val exception = assertThrows<IllegalArgumentException> { PlayerGameResults.from(players) }
        assertThat(exception.message).isEqualTo("딜러가 존재하지 않습니다.")
    }

    @Test
    fun `딜러, 플레이어 게임 결과가 정상적으로 생성`() {
        // given
        val dealer1 = Dealer()
        dealer1.receiveCard(Card(CardSymbol.스페이드, CardNumber.THREE))

        val player1 = Player.from("aiden1")
        player1.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        val player2 = Player.from("aiden2")
        player2.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        val player3 = Player.from("aiden3")
        player3.receiveCard(Card(CardSymbol.하트, CardNumber.FOUR))

        val players = Players(listOf(dealer1, player1, player2, player3))

        // when
        val playerGameResults = PlayerGameResults.from(players)

        // then
        val (resultOfDealer, resultOfPlayer1, resultOfPlayer2, resultOfPlayer3) = playerGameResults.results

        assertAll(
            "player game results test",
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
