package blackjack.domain.state

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardFixture.SPADES_QUEEN
import blackjack.domain.card.CardFixture.SPADES_TEN
import blackjack.domain.card.CardFixture.SPADES_TWO
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

    test("hands score must be null on bust") {
        val hands = Hands(listOf(SPADES_TEN, SPADES_QUEEN, SPADES_TWO))

        Bust(hands).score shouldBe null
    }
})
