package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardDeckTest {
    @Test
    fun `CardDeck이 생성된 후 서로 다른 52개의 카드를 갖게 된다`() {
        val result = mutableSetOf<Card>()

        val cardDeck: CardDeck = CardDeck()

        repeat(52) {
            val card: Card = cardDeck.pop()
            result.add(card)
        }

        assertThat(result.size).isEqualTo(52)
    }

    @Test
    fun `CardDeck이 생성된 후 53번 카드를 요청하면 예외를 발생한다`() {
        val cardDeck: CardDeck = CardDeck()

        repeat(52) {
            val card: Card = cardDeck.pop()
        }

        assertThrows<IllegalStateException>("카드가 동났습니다") {
            cardDeck.pop()
        }
    }
}
