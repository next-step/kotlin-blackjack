package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Player 가 계속해서 Rank SEVEN 만 뽑으면, 최대 3번 뽑을 수 있고 카드 합은 21이다`() {
        val fakeDeck = Deck { Card(Rank.SEVEN, Suit.HEARTS) } // King 만 리턴한다
        val player = Player("sara", drawCard = { fakeDeck.draw() })
        player.play(
            // 계속해서 King 만 뽑을때
            isDrawCard = { true },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.value.size).isEqualTo(3) // 최대 3개를 뽑을 수 있다
        assertThat(player.cardsSum).isEqualTo(21)
    }

    @Test
    fun `Player 가 카드를 뽑지 않는다면 기본으로 주어진 카드만 갖는다`() {
        val fakeDeck = Deck { Card(Rank.TWO, Suit.HEARTS) } // 기본으로 주어질 카드
        val player = Player("sara", drawCard = { fakeDeck.draw() })
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
