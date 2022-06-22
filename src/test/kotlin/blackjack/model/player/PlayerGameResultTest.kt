package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("플레이어 게임 결과 테스트")
class PlayerGameResultTest {

    private lateinit var dealer: Dealer
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        dealer.receiveCard(Card(CardSymbol.하트, CardNumber.THREE))

        player1 = Player.from("aiden1")
        player1.receiveCard(Card(CardSymbol.하트, CardNumber.FOUR))

        player2 = Player.from("aiden2")
        player2.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))

        players = listOf(dealer, player1, player2)
    }

    @Test
    fun `딜러 승패 결과가 정상적으로 생성`() {
        // when
        val result = PlayerGameResult.ofDealer(dealer, players)

        // then
        assertAll(
            "dealer game result test",
            { assertThat(result.winCount).isEqualTo(1) },
            { assertThat(result.lostCount).isEqualTo(1) },
        )
    }

    @Test
    fun `플레이어 승패 결과가 정상적으로 생성`() {
        // when
        val resultOfPlayer1 = PlayerGameResult.ofPlayer(dealer, player1)
        val resultOfPlayer2 = PlayerGameResult.ofPlayer(dealer, player2)

        // then
        assertAll(
            "player game result test",
            { assertThat(resultOfPlayer1.winCount).isEqualTo(1) },
            { assertThat(resultOfPlayer1.lostCount).isEqualTo(0) },
            { assertThat(resultOfPlayer2.winCount).isEqualTo(0) },
            { assertThat(resultOfPlayer2.lostCount).isEqualTo(1) },
        )
    }
}
