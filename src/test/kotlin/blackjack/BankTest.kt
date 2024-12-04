package blackjack

import blackjack.InitialCardsTestFixtures.initial18Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BankTest : StringSpec({
    "은행은 딜러의 베팅 금액을 받는다" {
        val dealer = Dealer(initial18Cards)

        val sut = Bank()

        sut.bet(dealer, 0)

        sut.accounts[dealer] shouldBe 0
    }

    "은행은 플레이어의 베팅 금액을 받는다" {
        val player = Player("pobi", initial18Cards)

        val sut = Bank()

        sut.bet(player, 1)

        sut.accounts[player] shouldBe 1
    }

    "플레이어의 베팅 금액은 1원 이상이어야 한다" {
        val player = Player("pobi", initial18Cards)

        val sut = Bank()

        shouldThrow<IllegalArgumentException> { sut.bet(player, 0) }
    }
})
