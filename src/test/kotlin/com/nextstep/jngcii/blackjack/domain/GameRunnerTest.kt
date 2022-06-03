package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class GameRunnerTest {
    @Test
    fun `true를 입력받으면 21이 넘기 전까지 카드추가`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        assertThat(player.cards.size).isEqualTo(0)

        GameRunner.run(deck, player, { true }) {}

        assertThat(player.cards.size).isNotEqualTo(0)
    }

    @Test
    fun `false를 입력받으면 카드 추가 안함`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        assertThat(player.cards.size).isEqualTo(0)

        GameRunner.run(deck, player, { false }) {}

        assertThat(player.cards.size).isEqualTo(0)
    }

    @Test
    fun `true를 전달받은 후 바로 유저의 할당 총합이 21이 넘으면 카드 한번만 더 추가됨`() {
        val deck = CardDeck()
        val player = PlayerBoard("peter")

        player.addCard(Card(Card.Shape.SPADE, Card.SYMBOL.TEN))
        player.addCard(Card(Card.Shape.CLOVER, Card.SYMBOL.ACE))
        assertThat(player.cards.size).isEqualTo(2)
        assertThat(player.total).isEqualTo(21)

        GameRunner.run(deck, player, { true }) {}

        assertThat(player.cards.size).isEqualTo(3)
    }
}
