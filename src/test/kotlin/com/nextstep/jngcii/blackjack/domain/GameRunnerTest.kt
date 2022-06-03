package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class GameRunnerTest {
    @Test
    fun `true를 입력받으면 유저에게 카드 할당하고 true 반환`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        assertThat(player.cards.size).isEqualTo(0)

        val result: Boolean = GameRunner.run(deck, player, true)

        assertThat(result).isTrue
        assertThat(player.cards.size).isEqualTo(1)
    }

    @Test
    fun `fase를 입력받으면 그냥 false 반환`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        assertThat(player.cards.size).isEqualTo(0)

        val result: Boolean = GameRunner.run(deck, player, false)

        assertThat(result).isFalse
        assertThat(player.cards.size).isEqualTo(0)
    }

    @Test
    fun `true를 전달받은 후 유저의 할당 총합이 21이 넘으면 false를 반환`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        player.addCard(Card(Card.Shape.SPADE, Card.SYMBOL.TEN))
        player.addCard(Card(Card.Shape.CLOVER, Card.SYMBOL.ACE))
        assertThat(player.cards.size).isEqualTo(2)
        assertThat(player.total).isEqualTo(21)

        val result: Boolean = GameRunner.run(deck, player, true)

        assertThat(result).isFalse
        assertThat(player.cards.size).isEqualTo(3)
    }
}
