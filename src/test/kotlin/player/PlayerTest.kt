package player

import card.CardTest.Companion.CLOVER_ACE
import card.CardTest.Companion.HEART_ACE
import card.CardTest.Companion.HEART_JACK
import card.CardTest.Companion.HEART_QUEEN
import card.CardTest.Companion.SPADE_JACK
import card.CardTest.Companion.SPADE_KING
import card.CardTest.Companion.SPADE_QUEEN
import card.CardTest.Companion.SPADE_TWO
import card.Cards
import card.Hand
import card.TwoCards
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf

class PlayerTest : FunSpec({
    context("Start.addTwoCards") {
        forAll(
            row(TwoCards(SPADE_QUEEN, SPADE_TWO)),
            row(TwoCards(SPADE_KING, HEART_ACE)),
        ) { input ->
            test("두장 받으면 OnGoing Player가 되어야 합니다.") {
                val startPlayer = Player.Start(Name("jeff"), Hand(Cards()))
                val actual = startPlayer.addTwoCard(input)
                actual should beInstanceOf<Player.OnGoing>()
            }
        }
    }

    context("OnGoing.addCard") {
        forAll(
            row(SPADE_KING),
            row(HEART_ACE),
        ) { input ->
            test("Bust가 아니면 OnGoing으로 유지되어야합니다.") {
                val onGoingPlayer = Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(SPADE_TWO, CLOVER_ACE))))
                val actual = onGoingPlayer.addCard(input)
                actual should beInstanceOf<Player.OnGoing>()
            }
        }

        forAll(
            row(SPADE_KING),
            row(SPADE_QUEEN),
            row(SPADE_JACK),
        ) { input ->
            test("21이 넘어가면  Bust로 변경되어야합니다.") {
                val onGoingPlayer = Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(HEART_JACK, HEART_QUEEN))))
                val actual = onGoingPlayer.addCard(input)
                actual should beInstanceOf<Player.Bust>()
            }
        }
    }

    context("on.toStay") {
        test("플레이어가 더이상 카드를 뽑지 않으면 stay 상태로 변경됩니다.") {
            val onGoingPlayer = Player.OnGoing(Name("jeff"), Hand(Cards(linkedSetOf(HEART_JACK, HEART_QUEEN))))
            val actual = onGoingPlayer.toStay()
            actual should beInstanceOf<Player.Stay>()
        }
    }
})
