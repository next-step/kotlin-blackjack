package blackjack.model.player

import blackjack.dummy.toCardSet
import blackjack.model.card.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DealerHitDecisionMakerTest {

    @ParameterizedTest
    @CsvSource(
        "'8S,8D',16",
        "'2H,2C',4",
        "'8S,7C',15"
    )
    fun `카드 2장이 16점 이하일때 hit`(cardListString: String, expectFinalScore: Int) {
        val cardSet = cardListString.toCardSet()
        val finalScore = State.of(cardSet).finalScore

        // given
        val dealerHitDecisionMaker = DealerHitDecisionMaker()

        // when
        val player = Player.Guest("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player)).isTrue },
            { assertThat(player.canHit).isTrue }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'8S,9D',17",
        "'JH,QC',20",
        "'9S,9C',18"
    )
    fun `카드 2장이 17점 이상일때 stay`(cardListString: String, expectFinalScore: Int) {

        val cardSet = cardListString.toCardSet()
        val finalScore = State.of(cardSet).finalScore

        // given
        val dealerHitDecisionMaker = DealerHitDecisionMaker()

        // when
        val player = Player.Guest("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player)).isFalse },
            { assertThat(player.canHit).isFalse }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'2S,2D,2H',6",
        "'7H,7C,2C',16",
        "'8S,3C,4C',15"
    )
    fun `카드 3장일때 점수상관 없이 stay 16정 이하`(cardListString: String, expectFinalScore: Int) {
        val cardSet = cardListString.toCardSet()
        val finalScore = State.of(cardSet).finalScore

        // given
        val dealerHitDecisionMaker = DealerHitDecisionMaker()

        // when
        val player = Player.Guest("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player)).isFalse },
            { assertThat(player.canHit).isFalse }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'JS,8D,2H',20",
        "'7H,7C,4C',18",
        "'8S,3C,6C',17"
    )
    fun `카드 3장일때 점수상관 없이 stay 16정 이상`(cardListString: String, expectFinalScore: Int) {
        val cardSet = cardListString.toCardSet()
        val finalScore = State.of(cardSet).finalScore

        // given
        val dealerHitDecisionMaker = DealerHitDecisionMaker()

        // when
        val player = Player.Guest("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player)).isFalse },
            { assertThat(player.canHit).isFalse }
        )
    }
}
