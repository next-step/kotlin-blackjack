package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PlayerTest {
    @Test
    @DisplayName("Player 가 계속해서 Rank SEVEN 만 뽑으면, 최대 3번 뽑을 수 있고 카드 합은 21이다")
    fun `player can draw up to 3 cards if all are Rank SEVEN, with a total sum of 21`() {
        val fakeDeck = Deck { Card(Rank.SEVEN, Suit.HEARTS) }
        val player =
            Player(
                name = "sara",
                betMoney = BetMoney(BigDecimal.ZERO),
                drawCard = { fakeDeck.draw() },
            )
        player.play(
            isDrawCard = { true },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.value.size).isEqualTo(3)
        assertThat(player.cardsSum).isEqualTo(21)
    }

    @Test
    @DisplayName("Player 가 카드를 뽑지 않는다면 기본으로 주어진 카드만 갖는다")
    fun `player keeps the default cards if no additional cards are drawn`() {
        val fakeDeck = Deck { Card(Rank.TWO, Suit.HEARTS) } // 기본으로 주어질 카드
        val player =
            Player(
                name = "sara",
                betMoney = BetMoney(BigDecimal.ZERO),
                drawCard = { fakeDeck.draw() },
            )
        player.play(
            // 카드를 추가로 뽑지 않는다
            isDrawCard = { false },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.value.size).isEqualTo(2)
        assertThat(player.cardsSum).isEqualTo(4)
    }
}
