package blackjack.domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ValueSource(strings = ["pobi", "jason", "eden"])
    @ParameterizedTest
    fun `참가자는 이름을 가진다`(name: String) {
        Player(name).name shouldBe name
    }

    @Test
    fun `참가자는 카드를 받을 수 있다`() {
        val player = Player("pobi")
        val card = Card(CardNumber.FIVE, CardShape.CLOVER)
        player.cards.count() shouldBe 0
        player.hit(card)
        player.cards.count() shouldBe 1
        player.cards.list shouldContain card
    }
}
