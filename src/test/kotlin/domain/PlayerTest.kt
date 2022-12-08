package domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {

    @Test
    fun `사용자가 카드를 뽑아 보관합니다`() {
        val player = Player(PlayerName("chansoo"));

        player.takeCard(Card.ACE)

        player.cards.cards shouldContain Card.ACE
    }
    
    @Test
    fun `최적의 점수를 반환합니다`(){
        val player = Player(PlayerName("chansoo"));

        player.takeCard(Card.ACE)
        player.takeCard(Card.ACE)
        player.takeCard(Card.TEN)

        player.choiceBestScore() shouldBe 12
    }
}
