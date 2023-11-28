package blackjack.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayingCardTest {
    @Test
    fun `카드는 중복없이 52장으로 구성된다`() {
        PlayingCard().cards.size shouldBe 52
    }

    @Test
    fun `카드덱에서 한장의 카드를 랜덤으로 뽑을 수 있다`() {
        val playingCard = PlayingCard()
        val card = playingCard.draw()
        card shouldNotBe null
    }

    @Test
    fun `카드덱에서 모든 카드를 뽑은 경우 IllegalArgumentException 이 발생한다`() {
        val playingCard = PlayingCard()
        repeat(52) {
            playingCard.draw()
        }
        assertThrows<IllegalArgumentException> { playingCard.draw() }
    }
}
