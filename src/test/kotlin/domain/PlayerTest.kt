package domain

import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {

    @Test
    fun `사용자가 카드를 뽑아 보관합니다`() {
        val player = Player(PlayerName("chansoo"));

        player.takeCard(Card.ACE)

        player.cards.cards shouldContain Card.ACE
    }
}
