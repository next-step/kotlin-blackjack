package blackjack

import blackjack.domain.Card
import blackjack.domain.GameCardsSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameCardsSetTest {
    private lateinit var gameCardsSet: GameCardsSet

    @BeforeEach
    fun setUp() {
        gameCardsSet = GameCardsSet
    }

    @Test
    fun `게임 카드 세트에서 카드를 뽑을 수 있다`() {
        val draw = GameCardsSet.drawRandomCard()
        assertThat(draw).isInstanceOf(Card::class.java)
    }

    @Test
    fun `게임 카드 세트에서 모든 카드를 뽑으면 null이 반환된다`() {
        while (true) {
            val draw = GameCardsSet.drawRandomCard() ?: break
        }

        val draw = GameCardsSet.drawRandomCard()
        assertNull(draw)
    }
}
