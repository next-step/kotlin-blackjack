package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerBoardTest {
    @Test
    fun `첫 리스트는 비어있다`() {
        val cards: List<Card> = PlayerBoard().cards

        assertThat(cards).isEmpty()
    }

    @Test
    fun `같은 카드가 들어오면 예외를 발생한다`() {
        val playerBoard = PlayerBoard()

        val spadeTwo = Card(Card.Shape.SPADE, Card.SYMBOL.TWO)

        playerBoard.addCard(spadeTwo)

        assertThrows<IllegalArgumentException>("이미 존재하는 카드입니다") {
            playerBoard.addCard(spadeTwo)
        }
    }

    @Test
    fun `들어온 카드 확인`() {
        val playerBoard = PlayerBoard()

        val cards = listOf(
            Card(Card.Shape.SPADE, Card.SYMBOL.TWO),
            Card(Card.Shape.CLOVER, Card.SYMBOL.TEN),
            Card(Card.Shape.HEART, Card.SYMBOL.ACE)
        )

        cards.forEach { playerBoard.addCard(it) }

        assertThat(playerBoard.cards).isEqualTo(cards)
    }
}
