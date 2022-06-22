package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
        Assertions.assertThat(exception.message).isEqualTo("딜러가 존재하지 않습니다.")
    }
}
