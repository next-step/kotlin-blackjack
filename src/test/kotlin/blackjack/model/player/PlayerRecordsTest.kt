package blackjack.model.player

import blackjack.dummy.toCardSet
import blackjack.model.CardDistributor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerRecordsTest {

    @Test
    fun `승패계산테스트`() {

        // given
        val alwaysHitDecisionMaker = object : HitDecisionMaker {
            override fun shouldHit(player: Player, cardDistributor: CardDistributor) = true
        }

        val dealer = Player.Dealer("딜러").apply {
            "JS,5S".toCardSet().forEach(this::addCard) // 15점 1승 2패
        }

        val playerA = Player.Guest("A", alwaysHitDecisionMaker).apply {
            "JS,6S".toCardSet().forEach(this::addCard) // 16점 승
        }
        val playerB = Player.Guest("B", alwaysHitDecisionMaker).apply {
            "JS,7S".toCardSet().forEach(this::addCard) // 17점 승
        }
        val playerC = Player.Guest("C", alwaysHitDecisionMaker).apply {
            "JS,4S".toCardSet().forEach(this::addCard) // 14점 패
        }

        val guests = Players(listOf(playerA, playerB, playerC))

        // when
        val playerRecords = PlayerRecords.of(dealer, guests)

        // then
        val expectedDealerRecord = PlayerRecord.DealerRecord(dealer, win = 1, lose = 2, draw = 0)
        assertThat(playerRecords.find { it.player is Player.Dealer }).isEqualTo(expectedDealerRecord)
    }
}
