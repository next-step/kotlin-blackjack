package domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `����ڰ� ī�带 �̾� �����մϴ�`() {
        val player = Player(PlayerName("chansoo"))

        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))

        player.cards.cards shouldContain Card(CardNumber.ACE, CardShape.SPACE)
    }

    @Test
    fun `������ ������ ��ȯ�մϴ�`() {
        val player = Player(PlayerName("chansoo"))

        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))
        player.takeCard(Card(CardNumber.ACE, CardShape.SPACE))
        player.takeCard(Card(CardNumber.TEN, CardShape.SPACE))

        player.choiceBestScore() shouldBe 12
    }
}
