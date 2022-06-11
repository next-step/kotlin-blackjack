package blackjack.model.player

import blackjack.dummy.toCardSet
import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import blackjack.model.card.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DealerHitDecisionMakerTest {

    private lateinit var dealerHitDecisionMaker: DealerHitDecisionMaker
    private lateinit var cardDistributor: CardDistributor

    @BeforeEach
    fun setUp() {
        this.dealerHitDecisionMaker = DealerHitDecisionMaker
        this.cardDistributor = DefaultCardDistributor()
    }

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
        val dealerHitDecisionMaker = this.dealerHitDecisionMaker

        // when
        val player = Player.Dealer("a")
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player, this.cardDistributor)).isTrue },
            { assertThat(player.canHit(this.cardDistributor)).isTrue }
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
        val dealerHitDecisionMaker = this.dealerHitDecisionMaker
        val cardDistributor = DefaultCardDistributor()

        // when
        val player = Player.Dealer("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player, cardDistributor)).isFalse },
            { assertThat(player.canHit(cardDistributor)).isFalse }
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
        val dealerHitDecisionMaker = this.dealerHitDecisionMaker

        // when
        val player = Player.Dealer("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player, cardDistributor)).isFalse },
            { assertThat(player.canHit(cardDistributor)).isFalse }
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
        val dealerHitDecisionMaker = this.dealerHitDecisionMaker

        // when
        val player = Player.Dealer("a", dealerHitDecisionMaker)
        cardSet.forEach { card -> player.addCard(card) }

        // then
        assertAll(
            { assertThat(finalScore).isEqualTo(expectFinalScore) },
            { assertThat(dealerHitDecisionMaker.shouldHit(player, cardDistributor)).isFalse },
            { assertThat(player.canHit(cardDistributor)).isFalse }
        )
    }
}
