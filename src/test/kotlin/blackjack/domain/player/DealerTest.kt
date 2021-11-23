package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.state.Ready
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 플레이어(GamePlayer)")
internal class DealerTest {

    @Test
    fun `디폴트 이름과 상태로 생성가능하다`() {
        val dealer: Player = Dealer()

        assertAll(
            { assertThat(dealer).isNotNull },
            { assertThat(dealer).isExactlyInstanceOf(Dealer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Dealer(playerState = Ready().draw(extraCard))

        val dealer = Dealer().draw(Deck.initialize { it }) { listOf(it.pop()) }
        assertThat(dealer).isEqualTo(expected)
    }
}
