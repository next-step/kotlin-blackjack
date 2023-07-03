package blackjack.domain

import blackjack.fixture.CustomCardDeck
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.PlayerFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe



class RoundTest : BehaviorSpec({
    given("라운드") {
        val cardDeck = CustomCardDeck(mutableListOf())
        val dealer: Dealer = Dealer.of(PlayerName.from("딜러"), cardDeck)
        `when`("초기 상태는") {
            val round = Round(dealer, PlayerFixture.of(HEART_KING, HEART_SEVEN))
            then("NOT_STARTED 이다.") {
                round.status shouldBe RoundStatus.NOT_STARTED
            }
        }
        `when`("멈추면") {
            val round = Round(dealer, PlayerFixture.of(HEART_KING, HEART_SEVEN))
            round.stop()
            then("FINISH 이다.") {
                round.status shouldBe RoundStatus.FINISH
            }
        }

    }
})
