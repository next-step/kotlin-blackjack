package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class PlayerTest {
    @EmptySource
    @ParameterizedTest
    fun `참가자는 이름은 빈 값이 될 수 없다`(name: String) {
        shouldThrow<IllegalArgumentException> {
            Player(name)
        }
    }

    @Test
    fun `참가자는 카드를 받을 수 있다`() {
        val player = Player("pobi")
        val card = Card.of(CardNumber.FIVE, CardShape.CLOVER)
        player.cards.count() shouldBe 0
        player.hit(card)
        player.cards.count() shouldBe 1
        player.cards.list shouldContain card
    }
}
