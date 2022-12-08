package domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `사용자가 카드를 뽑아 보관합니다`() {
        val player = Player(PlayerName("chansoo"))

        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))

        player.cards.cards shouldContain Card(CardNumber.ACE, CardShape.SPACE)
    }

    @Test
    fun `최적의 점수를 반환합니다`() {
        val player = Player(PlayerName("chansoo"))

        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))
        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))
        player.takeCard(Card(CardNumber.TEN, CardShape.SPACE))

        player.choiceBestScore() shouldBe 12
    }
}
