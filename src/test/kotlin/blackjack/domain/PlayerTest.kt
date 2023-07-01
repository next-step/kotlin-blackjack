package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({

    test("플레이어는 자신의 이름과 카드 리스트를 가진다.") {
        // given
        val cards = Cards(listOf(Card(Rank.ACE, Suit.HEART), Card(Rank.JACK, Suit.SPADE)))
        val name = "song2"

        // when
        val actual = Player(name, cards)

        // then
        actual.name shouldBe name
        actual.cards shouldBe cards
    }
})
