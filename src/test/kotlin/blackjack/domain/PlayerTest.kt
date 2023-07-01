package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({

    test("플레이어는 자신의 이름과 카드 리스트를 가진다.") {
        // given
        val cards = Cards(listOf(Card(Rank.Ace, Suit.HEART), Card(Rank.Jack, Suit.SPADE)))
        val name = "song2"

        // when
        val player = Player(name, cards)

        // then
        player.name shouldBe name
        player.cards shouldBe cards
    }
})
