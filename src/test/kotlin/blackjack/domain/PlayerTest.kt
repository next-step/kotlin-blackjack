package blackjack.domain

import blackjack.test.TestDeckGenerator
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class PlayerTest : ExpectSpec({

    expect("플레이어는 카드를 한 장 받을 수 있다.") {
        val dealer = Dealer(TestDeckGenerator.generate())
        val player = Player(Nickname("name"), dealer)

        player.receiveCard()

        player.cards.size shouldBe 1
    }
})
