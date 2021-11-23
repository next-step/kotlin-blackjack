package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.state.Ready
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("게임 참가자(GamePlayer)")
internal class GamePlayerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = GamePlayer(Name("김우재"))

        assertAll(
            { AssertionsForClassTypes.assertThat(gamePlayer).isNotNull },
            { AssertionsForClassTypes.assertThat(gamePlayer).isExactlyInstanceOf(GamePlayer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = GamePlayer(Name("김우재"), Ready().draw(extraCard))

        val dealer = GamePlayer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        AssertionsForClassTypes.assertThat(dealer).isEqualTo(expected)
    }
}

