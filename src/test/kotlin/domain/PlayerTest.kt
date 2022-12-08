package domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {

    @Test
    fun `����ڰ� ī�带 �̾� �����մϴ�`() {
        val player = Player(PlayerName("chansoo"));

        player.takeCard(Card.ACE)

        player.cards.cards shouldContain Card.ACE
    }
    
    @Test
    fun `������ ������ ��ȯ�մϴ�`(){
        val player = Player(PlayerName("chansoo"));

        player.takeCard(Card.ACE)
        player.takeCard(Card.ACE)
        player.takeCard(Card.TEN)

        player.choiceBestScore() shouldBe 12
    }
}
