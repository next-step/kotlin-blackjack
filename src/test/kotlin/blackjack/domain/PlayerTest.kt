package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Player 가 계속해서 Rank SEVEN 만 뽑으면, 최대 3번 뽑을 수 있고 카드 합은 21이다`() {
        val fakeDeck =
            object : Deck {
                override fun draw(): Card {
                    return Card(Rank.SEVEN, Suit.HEARTS) // King 만 리턴한다
                }
            }
        val player = Player("sara", fakeDeck)
        player.play(
            // 계속해서 King 만 뽑을때
            isDrawCard = { true },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.size).isEqualTo(3) // 최대 3개를 뽑을 수 있다
        assertThat(player.getCardSum()).isEqualTo(21)
    }

    @Test
    fun `Player 가 카드를 뽑지 않는다면 기본으로 주어진 카드만 갖는다`() {
        val fakeDeck =
            object : Deck {
                override fun draw(): Card {
                    return Card(Rank.TWO, Suit.HEARTS) // 기본으로 주어질 카드
                }
            }
        val player = Player("sara", fakeDeck)
        player.play(
            // 카드를 추가로 뽑지 않는다
            isDrawCard = { false },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.size).isEqualTo(2) // 카드는 기본적으로 2개가 주어진다
        assertThat(player.getCardSum()).isEqualTo(4)
    }
}
