package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var firstCard: Card
    private lateinit var secondCard: Card
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player(name = "mark")
        firstCard = Card(CardScore.SEVEN, Suit.SPADE)
        secondCard = Card(CardScore.TEN, Suit.HEART)
    }

    @DisplayName("카드를 뽑을 때 1장씩 반환되고, 덱이 비어있으면 새로운 덱에서 카드를 뽑는다")
    @Test
    fun `카드 한 장 뽑기`() {
        // when
        player.draw(firstCard)

        // then
        assertThat(player.stateOfCards()).isEqualTo("7스페이드")
        assertThat(player.cardCount()).isEqualTo(1)
    }

    @Test
    fun `카드 점수 합계`() {
        // given
        player.draw(firstCard)
        player.draw(secondCard)

        // when
        val sum = player.score()

        // then
        assertThat(sum).isEqualTo(17)
    }
}
