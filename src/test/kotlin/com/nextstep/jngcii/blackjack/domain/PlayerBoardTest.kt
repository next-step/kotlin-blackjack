package com.nextstep.jngcii.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerBoardTest {
    @Test
    fun `첫 리스트는 비어있다`() {
        val cards: List<Card> = PlayerBoard().cards

        assertThat(cards).isEmpty()
    }
}
