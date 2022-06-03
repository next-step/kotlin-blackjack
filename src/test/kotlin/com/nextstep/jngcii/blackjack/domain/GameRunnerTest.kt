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
}
