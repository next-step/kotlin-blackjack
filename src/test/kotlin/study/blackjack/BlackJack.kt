package study.blackjack

import blackjack.InputView
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test

class BlackJack {
    @Test
    fun inputGamers() {
        val playersName = InputView.getPlayerNames()
        playersName shouldContainAll listOf("pobi", "jason")
    }
}

