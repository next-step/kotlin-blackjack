package domain

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `ī�� ������ ���� ī�尡 ��ȯ�˴ϴ�`(){
        val cardDeck = CardDeck()
        cardDeck.drawCard() shouldNotBe null
    }
}
