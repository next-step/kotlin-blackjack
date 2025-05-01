package blackjack.domain.state

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.player.Hands
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BustTest : FunSpec({
    test("addCard throws exception if busted") {
        shouldThrow<IllegalStateException> {
            Bust(Hands()).addCard(SPADES_ACE)
        }
    }

    test("canContinue returns false on bust") {
        Bust(Hands()).canContinue shouldBe false
    }
})
