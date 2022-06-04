package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerBoardTest {
    @Test
    fun `각 플레이어보드는 유저 이름과 함께 초기화된다`() {
        val playerBoard = PlayerBoard("peter")

        assertThat(playerBoard.playerName).isEqualTo("peter")
    }

    @Test
    fun `첫 리스트는 비어있다`() {
        val cards: List<Card> = PlayerBoard("peter").cards

        assertThat(cards).isEmpty()
    }

    @Test
    fun `ready 호출 후 2장 할당 확인`() {
        val playerBoard = PlayerBoard("peter")
        playerBoard.ready(CardDeck())
        val cards: List<Card> = playerBoard.cards

        assertThat(cards.size).isEqualTo(2)
    }

    @Test
    fun `같은 카드가 들어오면 예외를 발생한다`() {
        val playerBoard = PlayerBoard("peter")

        val spadeTwo = Card(Card.Shape.SPADE, Card.SYMBOL.TWO)

        playerBoard.addCard(spadeTwo)

        assertThrows<IllegalArgumentException>("이미 존재하는 카드입니다") {
            playerBoard.addCard(spadeTwo)
        }
    }

    @Test
    fun `들어온 카드 확인`() {
        val playerBoard = PlayerBoard("peter")

        val cards = listOf(
            Card(Card.Shape.SPADE, Card.SYMBOL.TWO),
            Card(Card.Shape.CLOVER, Card.SYMBOL.TEN),
            Card(Card.Shape.HEART, Card.SYMBOL.ACE)
        )

        cards.forEach { playerBoard.addCard(it) }

        assertThat(playerBoard.cards).isEqualTo(cards)
    }

    @ParameterizedTest
    @MethodSource("cardsAndSum")
    fun `카드의 합 확인 테스트`(cards: List<Card>, sum: Int) {
        val playerBoard = PlayerBoard("peter")

        cards.forEach { playerBoard.addCard(it) }

        assertThat(playerBoard.total).isEqualTo(sum)
    }

    companion object {
        @JvmStatic
        fun cardsAndSum() = listOf(
            Arguments.of(
                listOf(
                    Card(Card.Shape.SPADE, Card.SYMBOL.TWO),
                    Card(Card.Shape.CLOVER, Card.SYMBOL.TEN),
                    Card(Card.Shape.HEART, Card.SYMBOL.ACE)
                ),
                23
            ),
            Arguments.of(
                listOf(
                    Card(Card.Shape.SPADE, Card.SYMBOL.TWO),
                    Card(Card.Shape.CLOVER, Card.SYMBOL.THREE),
                    Card(Card.Shape.HEART, Card.SYMBOL.FOUR),
                    Card(Card.Shape.HEART, Card.SYMBOL.FIVE),
                ),
                14
            ),
            Arguments.of(
                listOf(
                    Card(Card.Shape.SPADE, Card.SYMBOL.NINE),
                    Card(Card.Shape.CLOVER, Card.SYMBOL.KING),
                ),
                19
            ),
        )
    }
}
