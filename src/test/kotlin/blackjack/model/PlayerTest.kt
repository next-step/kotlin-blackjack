package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `게임 참가 시 2장의 카드 받기`() {
        val player = Player("moshi").apply {
            requestDeck(Deck.pop())
            requestDeck(Deck.pop())
        }

        assertThat(player.myReceivedDeck.size).isEqualTo(2)
    }

    @Test
    fun `점수 계산하기`() {
        val player = Player("moshi").apply {
            requestDeck(Deck(Card.Kinds.TWO, Card.Shape.HEART))
            requestDeck(Deck(Card.Kinds.EIGHT, Card.Shape.SPADE))
            requestDeck(Deck(Card.Kinds.ACE, Card.Shape.CLOVER))
        }

        assertThat(player.calculateRank()).isEqualTo(21)
    }
}
